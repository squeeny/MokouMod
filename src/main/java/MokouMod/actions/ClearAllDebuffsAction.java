package MokouMod.actions;

import MokouMod.util.mokouUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Utilities.squeenyUtils.att;

public class ClearAllDebuffsAction extends AbstractGameAction {

    public ClearAllDebuffsAction(AbstractCreature target) { this.target = target; }

    public void update() {
        for (AbstractPower power : target.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF && !(power instanceof InvisiblePower)) {
                att(new RemoveSpecificPowerAction(target, target, power));
            }
        }
        isDone = true;
    }
}
