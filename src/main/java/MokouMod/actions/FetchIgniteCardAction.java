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

public class FetchIgniteCardAction extends AbstractGameAction {

    public FetchIgniteCardAction(int amount) {
        this.amount = amount > 0 ? amount : 1;
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }
    public static AbstractCard fetchignitecard() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        CardGroup igniteCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        CardLibrary.getAllCards().stream().filter(c ->
                (c.hasTag(CardENUMs.IGNITE)) && c instanceof abs_mku_card).forEach(c -> igniteCards.group.add(c.makeCopy()));
        for(AbstractCard c: igniteCards.group){
            System.out.println(c + " [IGN]");
        }
        stanceChoices.addAll(igniteCards.group);
        return stanceChoices.get(cardRandomRng.random(stanceChoices.size() - 1));
    }
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            for(int i = 0; i < amount; i++){ stanceChoices.add(fetchignitecard()); }
            for (AbstractCard c : stanceChoices) {
                c.costForTurn = 0;
                player.hand.addToTop(c.makeStatEquivalentCopy());
            }
            tickDuration();
        }
        this.isDone = true;
    }
}