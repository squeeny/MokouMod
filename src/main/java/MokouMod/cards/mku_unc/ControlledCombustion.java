package MokouMod.cards.mku_unc;

import MokouMod.actions.ExhaustHandAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
public class ControlledCombustion extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ControlledCombustion.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DRAW = 6;
    private static final int UPG_DRAW = 1;
    public ControlledCombustion() {
        super(cardInfo, false);
        setMagic(DRAW, UPG_DRAW);
        setExhaust(true);
        setBurst(true);
    }
    @Override
    public float getTitleFontSize()
    {
        return 16;
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {

        boolean canUse = super.canUse(p, m);

        if (!canUse) { return false; }
        if (mokouUtils.anonymouscheckBurst()) { canUse = true; }
        else { canUse = false; }

        return canUse;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustHandAction());
        atb(new DrawCardAction(magicNumber));
        if(this.overheated){
            atb(new GainEnergyAction(p.energy.energyMaster - EnergyPanel.totalCount + this.costForTurn));

        }
    }
}