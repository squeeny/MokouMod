package MokouMod.cards.mku_com;


import MokouMod.actions.OverheatCardAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.p;
public class Overheat extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Overheat.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.NONE
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DRAW = 5;
    public static int DRAWPILE;
    public Overheat() {
        super(cardInfo, false);
        setMagic(DRAW);
        setExhaust(true);
        setRetain(false, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        DRAWPILE = p().drawPile.size();
        atb(new DrawCardAction(p, this.magicNumber));
        atb(new OverheatCardAction());
        if(this.overheated){
            atb(new GainEnergyAction(2));

        }
    }
}