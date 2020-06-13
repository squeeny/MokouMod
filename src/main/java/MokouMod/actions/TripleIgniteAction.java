package MokouMod.actions;

import MokouMod.powers.IgnitePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static Utilities.squeenyUtils.doPow;
import static Utilities.squeenyUtils.p;

public class TripleIgniteAction extends AbstractGameAction {
    public TripleIgniteAction(AbstractCreature target) {
        this.target = target;
    }

    public void update() {
        if (this.target != null && this.target.hasPower(IgnitePower.POWER_ID)) {
            doPow(p(), new IgnitePower(this.target, this.target.getPower(IgnitePower.POWER_ID).amount * 2), true);
        }
        this.isDone = true;
    }
}