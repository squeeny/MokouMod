package MokouMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;

import static MokouMod.util.mokouUtils.checkBurst;

public class CheckBurstAction extends AbstractGameAction {
    private float startingDuration;
    public CheckBurstAction() {
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }
    public void update() {
        if(this.startingDuration == this.duration) {
            checkBurst();
            tickDuration();
        }
        else { this.isDone = true; }
    }
}