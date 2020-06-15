package MokouMod.cards.mku_unc;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.cards.CardENUMs;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
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
    private static final int RESONANCE = 2;
    private static final int UPG_RESONANCE = 1;
    public HeatCyclone() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(RESONANCE, UPG_RESONANCE);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(anonymouscheckBurst() || this.overheated) {
            this.triggeredBurst = true;
            doDef(this.block);
            atb(new AdvancePhaseAction(this.magicNumber));
        }
    }
    @Override
    public void triggerOnGlowCheck() {
        if ((this.hasTag(CardENUMs.BURST) && anonymouscheckBurst()) || this.overheated) { glowColor = GOLD_BORDER_GLOW_COLOR;
        } else { glowColor = BLUE_BORDER_GLOW_COLOR; }
    }
}