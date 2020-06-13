package MokouMod.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Utilities.squeenyUtils.p;

public class StokeAction extends AbstractGameAction {

    public StokeAction(int amount) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : p().hand.group) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    c.baseDamage += this.amount;
                    c.applyPowers();
                    c.flash();
                }
            }
        }
        tickDuration();
    }
}