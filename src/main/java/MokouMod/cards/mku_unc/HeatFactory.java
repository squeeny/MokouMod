package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.atb;

public class HeatFactory extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            HeatFactory.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int UPG_COST = 0;
    private static final int LOSEHP = 5;

    public HeatFactory() {
        super(cardInfo, false);
        setCostUpgrade(UPG_COST);
        setMagic(LOSEHP);
        setExhaust(true);
        setBurst(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(anonymouscheckBurst()) {
            atb(new LoseHPAction(p, p, this.magicNumber));
            atb(new GainEnergyAction(p.energy.energyMaster - EnergyPanel.totalCount + this.costForTurn));
        }
        if(this.overheated) { atb(new ExpertiseAction(p, BaseMod.MAX_HAND_SIZE)); }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) { return false; }
        if (anonymouscheckBurst()) { canUse = true; }
        else { canUse = false; }
        return canUse;
    }
}
