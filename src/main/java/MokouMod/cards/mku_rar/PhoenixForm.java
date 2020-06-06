package MokouMod.cards.mku_rar;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.actions.FlameSlingPhaseIncAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.characters.MKU;
import MokouMod.patches.general.ResonanceBurstPhaseValue;
import MokouMod.powers.PhoenixFormPower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doPow;
public class PhoenixForm extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            PhoenixForm.class.getSimpleName(),
            COSTS[3],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int RESONANCE = 3;
    private static final int UPG_RESONANCE = 2;

    public PhoenixForm() {
        super(cardInfo, false);
        setCostUpgrade(COSTS[2]);
        setMokouMagic(RESONANCE, UPG_RESONANCE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p instanceof MKU) { atb(new FlameSlingPhaseIncAction()); }
        if(!p.hasPower(PhoenixFormPower.POWER_ID)){ doPow(p, new PhoenixFormPower(p, -1)); }
        atb(new AdvancePhaseAction(ResonanceBurstPhaseValue.maxResonanceBurstPhase.get(p)));
        if(this.overheated){
            if(p instanceof MKU) { atb(new FlameSlingPhaseIncAction()); }
            atb(new AdvancePhaseAction(mokouSecondMagicNumber));
        }
    }
}
