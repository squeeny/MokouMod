package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.getTurnBurstAmount;
import static Utilities.squeenyUtils.doVfx;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class IcarusDive extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            IcarusDive.class.getSimpleName(),
            COSTS[4],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public final static String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 4;
    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 1;
    public IcarusDive() {
        super(cardInfo, false);
        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
        if (CardCrawlGame.dungeon != null)
            configureCostsOnNewCard();
    }
    public void configureCostsOnNewCard() {
        updateCost(-getTurnBurstAmount());
    }
    public void triggerOnCardPlayed(AbstractCard c) {
        int diff = this.cost - this.costForTurn;
        int tmpCost = cardInfo.cardCost - getTurnBurstAmount();
        if (tmpCost < 0) {
            tmpCost = 0;
        }
        if (tmpCost != this.cost) {
            this.isCostModified = true;
            this.cost = tmpCost;
            this.costForTurn = this.cost - diff;
            if (this.costForTurn < 0) {
                this.costForTurn = 0;
            }
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        float yOffset = (m.hb_h/2.0f)*0.33f;
        float mtop = m.drawY+(m.hb_h/2.0f);
        for (int i = 0; i < magicNumber; i++) {
            float tmp = yOffset * i;
            doVfx(new FlashAtkImgEffect(m.drawX, mtop - tmp, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
            atb(new SFXAction("BLUNT_FAST"));
            doDmg(m, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE);
        }
        if(this.overheated){
            atb(new GainEnergyAction(getTurnBurstAmount() - 1));
            atb(new ExhaustSpecificCardAction(this, p().hand, true));

        }
    }
}