package MokouMod.actions;

import MokouMod.powers.IgnitePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static Utilities.squeenyUtils.doPow;

public class GainIgniteForRemovedBlockAction extends AbstractGameAction {
    public GainIgniteForRemovedBlockAction(AbstractCreature target, AbstractCreature source) {
        this.setValues(target, source, this.amount);
        this.actionType = ActionType.BLOCK;
        this.duration = 0.25F;
        this.source = source;
    }
    public void update() {
        if (!this.target.isDying && !this.target.isDead && this.duration == 0.25F && this.target.currentBlock > 0) {
            doPow(this.source, new IgnitePower(this.source, this.target.currentBlock));
            this.target.loseBlock();
        }
        this.tickDuration();
    }
}
