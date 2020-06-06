package MokouMod.actions;

import MokouMod.cards.mku_com.Overheat;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.Settings;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;

public class OverheatCardAction extends AbstractGameAction {
    private float startingDuration;
    public OverheatCardAction(){
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }
    @Override
    public void update() {
        if(p().drawPile.size() > Overheat.DRAWPILE){ att(new LoseHPAction(p(), p(), 8152004)); }
        this.isDone = true;
    }
}
