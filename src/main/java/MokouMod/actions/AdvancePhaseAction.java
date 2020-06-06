package MokouMod.actions;

import MokouMod.MokouMod;
import MokouMod.characters.MKU;
import MokouMod.interfaces.onGainResonanceSubscriber;
import MokouMod.patches.general.ResonanceBurstPhaseValue;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;

public class AdvancePhaseAction extends AbstractGameAction {

    private boolean resonatingAura;
    public AdvancePhaseAction() { this(1, true); }
    public AdvancePhaseAction(int amount) { this(amount, true); }
    public AdvancePhaseAction(int amount, boolean resonatingAura)
    {
        this.amount = amount;
        this.actionType = ActionType.SPECIAL;
        this.resonatingAura = resonatingAura;
    }

    @Override
    public void update() {
        int newValue = ResonanceBurstPhaseValue.resonanceburstPhase.get(p()) + this.amount;
        if (newValue < 0) { newValue = 0; }
        if (newValue > ResonanceBurstPhaseValue.maxResonanceBurstPhase.get(p())) { newValue = ResonanceBurstPhaseValue.maxResonanceBurstPhase.get(p()); }
        ResonanceBurstPhaseValue.resonanceburstPhase.set(p(), newValue);
        MokouMod.resonanceBurst.updateTooltip();
        if(ResonanceBurstPhaseValue.resonanceburstPhase.get(p()) != ResonanceBurstPhaseValue.maxResonanceBurstPhase.get(p())) {
            for (int i = 0; i <= this.amount; i += 1) {
                if(p() instanceof MKU) { att(new FlameSlingPhaseIncAction()); }
            }
        }
        for(AbstractPower p : p().powers){
            if(resonatingAura){ ((onGainResonanceSubscriber) p).onGainResonance(); }
        }
        this.isDone = true;
    }
}