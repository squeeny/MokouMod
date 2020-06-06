package MokouMod.actions;

import MokouMod.patches.cards.CardENUMs;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class FetchBurstCardAction extends AbstractGameAction {

    public FetchBurstCardAction(int amount) {
        this.amount = amount;
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public static AbstractCard fetchburstcard() {

        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();

        stanceChoices.addAll(srcCommonCardPool.group);
        stanceChoices.addAll(srcUncommonCardPool.group);
        stanceChoices.addAll(srcRareCardPool.group);

        stanceChoices.removeIf(c -> (!c.hasTag(CardENUMs.BURST)));

        return stanceChoices.get(cardRandomRng.random(stanceChoices.size() - 1));

    }

    public void update() {

        if (duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();

            for(int i = 0; i < amount; i += 1){
                stanceChoices.add(fetchburstcard());
            }

            for (AbstractCard c : stanceChoices) {
                player.hand.addToTop(c.makeCopy());
            }
            if(stanceChoices.size() > 0){ tickDuration(); }
            return;
        }
        else {
            this.isDone = true;

        }
    }

}