package MokouMod.actions;


import MokouMod.powers.IgnitePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;

public class DoubleIgniteAction extends AbstractGameAction {
    public DoubleIgniteAction(AbstractCreature target) {
        this.target = target;
    }

    public void update() {
        if (this.target != null && this.target.hasPower(IgnitePower.POWER_ID)) {
            doPow(p(), new IgnitePower(this.target, this.target.getPower(IgnitePower.POWER_ID).amount), true);
        }
        this.isDone = true;
    }
}