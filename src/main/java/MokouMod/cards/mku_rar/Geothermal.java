package MokouMod.cards.mku_rar;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;

public class Geothermal extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Geothermal.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 10;
    private static final int UPG_DMG = 5;
    private static final int DRAW = 3;
    private static final int ENERGY = 1;
    public Geothermal() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(DRAW);
        setMokouMagic(ENERGY);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
        atb(new AdvancePhaseAction(this.magicNumber));
        if(this.overheated) {
            atb(new DrawCardAction(this.magicNumber));
            atb(new GainEnergyAction(this.mokouSecondMagicNumber));
        }
    }
    @Override
    public float getTitleFontSize() { return 14; }
}