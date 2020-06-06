package MokouMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.core.Settings;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class ExhaustHandAction extends AbstractGameAction {

    private float startingDuration;

    public ExhaustHandAction() {
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    public void update() {
        int count = p().hand.size();
        int i;
        for (i = 0; i < count; ++i) {
            if (Settings.FAST_MODE) { att(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            } else { att(new ExhaustAction(1, true, true)); }
        }
        this.isDone = true;
    }
}
