package MokouMod.actions;

import MokouMod.patches.cards.CardENUMs;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

import static MokouMod.MokouMod.burstCards;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class FetchBurstCardAction extends AbstractGameAction {

    public FetchBurstCardAction(int amount) {
        this.amount = amount > 0 ? amount : 1;
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }
    public static AbstractCard fetchburstcard() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.addAll(burstCards.group);
        return stanceChoices.get(cardRandomRng.random(stanceChoices.size() - 1));
    }
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            for(int i = 0; i < amount; i++){ stanceChoices.add(fetchburstcard()); }
            for (AbstractCard c : stanceChoices) {
                c.costForTurn = 0;
                player.hand.addToTop(c.makeStatEquivalentCopy());
            }
            tickDuration();
        }
        this.isDone = true;
    }
}