package MokouMod.cards.mku_unc;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;

public class HeatCyclone extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            HeatCyclone.class.getSimpleName(),
            COSTS[2],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 18;
    private static final int UPG_BLOCK = 4;
    private static final int RESONANCE = 1;
    public HeatCyclone() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(RESONANCE);
        setBurst(true);
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
        doDef(this.block);
        atb(new AdvancePhaseAction(this.magicNumber));
        if(this.overheated) {
            doDef(this.block);
        }
    }
}