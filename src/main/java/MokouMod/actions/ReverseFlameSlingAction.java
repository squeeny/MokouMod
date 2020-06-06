package MokouMod.actions;

import MokouMod.util.mokouUtils;
import MokouMod.vfx.combat.unique.FlameSlingEffect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static Utilities.squeenyUtils.doVfx;


public class ReverseFlameSlingAction extends AbstractGameAction {

    public ReverseFlameSlingAction(AbstractCreature m, AbstractPlayer p) {
        this.actionType = ActionType.WAIT;
        doVfx(new FlameSlingEffect(m.drawX, m.drawY+(m.hb_h/2f), p.drawX, p.drawY+(p.hb_h/2f)), true);
    }

    public void update() {

        this.isDone = true;
    }
}