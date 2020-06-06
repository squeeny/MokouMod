package MokouMod.actions;

import MokouMod.patches.cards.CardENUMs;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class FetchIgniteCardAction extends AbstractGameAction {


    public FetchIgniteCardAction(int amount) {
        this.amount = amount;
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public static AbstractCard FetchIgniteCard() {

        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();

        stanceChoices.addAll(srcCommonCardPool.group);
        stanceChoices.addAll(srcUncommonCardPool.group);
        stanceChoices.addAll(srcRareCardPool.group);

        stanceChoices.removeIf(c -> (!c.hasTag(CardENUMs.IGNITE)));

        return stanceChoices.get(cardRandomRng.random(stanceChoices.size() - 1));

    }

    public void update() {

        if (duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();

            for(int i = 0; i < amount; i += 1){
                stanceChoices.add(FetchIgniteCard());
            }

            if(stanceChoices.size() > 0) {
                for (AbstractCard c : stanceChoices) {
                    player.hand.addToTop(c.makeCopy());
                }
                tickDuration();
            }

            return;
        }
        else {
            this.isDone = true;
        }
    }

}