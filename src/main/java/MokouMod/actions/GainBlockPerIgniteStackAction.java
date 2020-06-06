package MokouMod.actions;

import MokouMod.powers.IgnitePower;
import MokouMod.util.mokouUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;

public class GainBlockPerIgniteStackAction extends AbstractGameAction {

    private float startingDuration;
    public GainBlockPerIgniteStackAction(){
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    @Override
    public void update() {
        AbstractPlayer p = p();
        if(this.duration == startingDuration) {
            if (p.hasPower(IgnitePower.POWER_ID)) { att(new GainBlockAction(p, p.getPower(IgnitePower.POWER_ID).amount)); }
            tickDuration();
        }
        else{
            this.isDone = true;
        }
    }
}