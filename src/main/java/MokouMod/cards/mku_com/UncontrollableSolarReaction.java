package MokouMod.cards.mku_com;

import MokouMod.actions.IgneousBlowAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.*;

public class UncontrollableSolarReaction extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            UncontrollableSolarReaction.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 14;
    private static final int UPG_DMG = 4;
    public UncontrollableSolarReaction() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(this.overheated){ atb(new IgneousBlowAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn))); }
        else{
            doVfx(new ClashEffect(m.hb.cX, m.hb.cY), 0.1F);
            doDmg(m, this.damage, AbstractGameAction.AttackEffect.NONE);
        }
    }
    @Override
    public void triggerOnGlowCheck() {
        if (canUse(null, null)) { this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy(); }
        else { this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy(); }
    }
    @Override
    public float getTitleFontSize()
    {
        return 16;
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) { return false; }
        for (AbstractMonster mo : getAliveMonsters()) {
            if (!mo.hasPower(IgnitePower.POWER_ID)) {
                canUse = false;
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[1];
            }
        }
        return canUse;
    }
}