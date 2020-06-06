package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.atb;
public class BurningDraft extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            BurningDraft.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF

    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DRAW = 2;
    private static final int ENERGY = 1;
    private static final int UPG_ENERGY = 1;
    public BurningDraft() {
        super(cardInfo, true);
        setMagic(DRAW);
        setMokouMagic(ENERGY, UPG_ENERGY);
        setExhaust(true);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        atb(new DrawCardAction(this.magicNumber));
        atb(new GainEnergyAction(this.mokouSecondMagicNumber));
        if(this.overheated){
            atb(new DrawCardAction(this.magicNumber));

        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) { return false; }
        if (mokouUtils.anonymouscheckBurst()) { canUse = true; }
        else { canUse = false; }
        return canUse;
    }
}