package MokouMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class ForestOfTheLostAction extends AbstractGameAction {

    public ForestOfTheLostAction(int amount) {

        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.amount = amount;

    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard card : p().exhaustPile.group) {
                if (card.costForTurn > 0) { card.modifyCostForCombat(-this.amount); }
                card.exhaust = true;
            }
            for (AbstractCard card : p().drawPile.group) {
                if (card.costForTurn > 0) { card.modifyCostForCombat(-this.amount); }
                card.exhaust = true;
            }
            for (AbstractCard card : p().discardPile.group) {
                if (card.costForTurn > 0) { card.modifyCostForCombat(-this.amount); }
                card.exhaust = true;
            }
            for (AbstractCard card : p().hand.group) {
                if (card.costForTurn > 0) { card.modifyCostForCombat(-this.amount); }
                card.exhaust = true;
            }
            tickDuration();
        } else { this.isDone = true; }
    }

}

