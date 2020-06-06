package MokouMod.cards.mku_rar;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.actions.FlameSlingPhaseIncAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.characters.MKU;
import MokouMod.patches.general.ResonanceBurstPhaseValue;
import MokouMod.powers.ResonatingAuraPower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doPow;
public class ResonatingAura extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ResonatingAura.class.getSimpleName(),
            COSTS[2],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    public static final int RESONANCE = 1;
    public ResonatingAura() {
        super(cardInfo, false);
        setMagic(RESONANCE);
        setIgnite(true);
        setInnate(false, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new ResonatingAuraPower(p, this.magicNumber));
        if(this.overheated) {
            if (p instanceof MKU) { atb(new FlameSlingPhaseIncAction()); }
            atb(new AdvancePhaseAction(ResonanceBurstPhaseValue.maxResonanceBurstPhase.get(p)));
        }
    }
}