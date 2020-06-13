package MokouMod.actions;

import MokouMod.patches.cards.CardENUMs;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import static Utilities.squeenyUtils.p;

public class EnhanceBurstAction extends AbstractGameAction {

    public EnhanceBurstAction(int amount) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : p().hand.group) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    if(c.hasTag(CardENUMs.BURST)) {
                        c.baseDamage += this.amount;
                        c.applyPowers();
                        c.flash();
                    }
                }
            }
            for (AbstractCard c : p().drawPile.group) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    if(c.hasTag(CardENUMs.BURST)) {
                        c.baseDamage += this.amount;
                        c.applyPowers();
                        c.flash();
                    }
                }
            }
            for (AbstractCard c : p().discardPile.group) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    if(c.hasTag(CardENUMs.BURST)) {
                        c.baseDamage += this.amount;
                        c.applyPowers();
                        c.flash();
                    }
                }
            }
            for (AbstractCard c : p().exhaustPile.group) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    if(c.hasTag(CardENUMs.BURST)) {
                        c.baseDamage += this.amount;
                        c.applyPowers();
                        c.flash();
                    }
                }
            }
        }
        tickDuration();
    }
}