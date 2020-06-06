package MokouMod.actions;

import MokouMod.powers.IgnitePower;
import MokouMod.util.mokouUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;

public class ConvertIgniteVigorAction extends AbstractGameAction {

    private float startingDuration;
    public ConvertIgniteVigorAction(){
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    @Override
    public void update() {
        AbstractPlayer p = p();
        if(this.duration == startingDuration) {
            if (p.hasPower(IgnitePower.POWER_ID)) {
                doPow(p, new VigorPower(p, p.getPower(IgnitePower.POWER_ID).amount), true);
                att(new ReducePowerAction(p, p, IgnitePower.POWER_ID, p.getPower(IgnitePower.POWER_ID).amount));
            }
            tickDuration();
        }
        else{
            this.isDone = true;
        }
    }
}