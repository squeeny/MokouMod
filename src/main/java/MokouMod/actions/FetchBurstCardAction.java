package MokouMod.actions;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.cards.CardENUMs;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class FetchBurstCardAction extends AbstractGameAction {

    public FetchBurstCardAction(int amount) {
        this.amount = amount > 0 ? amount : 1;
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }
    public static AbstractCard fetchburstcard() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        CardGroup burstCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        CardLibrary.getAllCards().stream().filter(c ->
                (c.hasTag(CardENUMs.BURST)) && c instanceof abs_mku_card).forEach(c -> burstCards.group.add(c.makeCopy()));
        for(AbstractCard c: burstCards.group){
            System.out.println(c + " [BURST]");
        }
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