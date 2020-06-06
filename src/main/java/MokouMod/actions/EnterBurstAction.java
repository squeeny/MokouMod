package MokouMod.actions;

import MokouMod.cards.mku_rar.SearingBlow;
import MokouMod.patches.combat.BurstMechanics;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;

public class EnterBurstAction extends AbstractGameAction {
    private float startingDuration;
    public EnterBurstAction() {
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }
    public void update() {
        if(this.startingDuration == this.duration) {
            for(AbstractCard card : p().hand.group){
                if (card instanceof SearingBlow){
                    card.upgrade();
                }
            }
            BurstMechanics.PlayerBurstField.isBurst.set(p(), true);
            tickDuration();
        }
        else{
            AbstractDungeon.actionManager.addToTop(new CheckBurstAction());
            this.isDone = true;
        }
    }
}
