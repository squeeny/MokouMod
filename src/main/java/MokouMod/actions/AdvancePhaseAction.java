package MokouMod.actions;

import MokouMod.MokouMod;
import MokouMod.characters.MKU;
import MokouMod.interfaces.onGainResonanceSubscriber;
import MokouMod.patches.combat.ResonanceMechanics;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;

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
        int newValue = ResonanceMechanics.resonanceburstPhase.get(p()) + this.amount;
        if (newValue < 0) { newValue = 0; }
        if (newValue > ResonanceMechanics.maxResonanceBurstPhase.get(p())) { newValue = ResonanceMechanics.maxResonanceBurstPhase.get(p()); }
        ResonanceMechanics.resonanceburstPhase.set(p(), newValue);
        MokouMod.resonanceBurst.updateTooltip();
        if(ResonanceMechanics.resonanceburstPhase.get(p()) != ResonanceMechanics.maxResonanceBurstPhase.get(p())) {
            if(p() instanceof MKU) { att(new FlameSlingPhaseIncAction()); }
        }
        ResonanceMechanics.resonanceTurnAmount.set(p(), ResonanceMechanics.resonanceTurnAmount.get(p()) + this.amount);
        for(AbstractPower p : p().powers){
            if(resonatingAura && p instanceof onGainResonanceSubscriber){ ((onGainResonanceSubscriber) p).onGainResonance(); }
        }
        for(AbstractCard c : p().hand.group){
            if(c instanceof onGainResonanceSubscriber){ ((onGainResonanceSubscriber) c).onGainResonance(); }
        }
        this.isDone = true;
    }
}