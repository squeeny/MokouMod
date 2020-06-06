package MokouMod.cards.mku_unc;

import MokouMod.actions.ProfanedFlameAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doDmg;
public class FusionFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            FusionFlare.class.getSimpleName(),
            COSTS[3],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 20;
    private static final int UPG_DMG = 5;
    public FusionFlare() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
        if(AbstractDungeon.actionManager.lastCard.name.toLowerCase().contains("flare")){ doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE); }
        if(this.overheated){ atb(new ProfanedFlameAction(this.damage, true, false)); }
    }
    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actionManager.lastCard.name.toLowerCase().contains("flare")){ this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy(); }
        else{ this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy(); }
    }
}