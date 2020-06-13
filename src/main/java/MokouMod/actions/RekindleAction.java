package MokouMod.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import static Utilities.squeenyUtils.*;

public class RekindleAction extends AbstractGameAction {
    private boolean upgraded;
    private boolean overheated;
    public RekindleAction(boolean upgraded, boolean overheated){
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
        this.overheated = overheated;
    }
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int div = 5;
            if (upgraded) { div--; }
            int count = p().exhaustPile.size();
            if (count == 0) { tickDuration(); }
            if (count > 0) {
                for (AbstractCard card : p().exhaustPile.group) {
                    if (p().hasPower("Corruption") && (card.type == AbstractCard.CardType.SKILL)) { card.setCostForTurn(-9); }
                    p().exhaustPile.moveToDiscardPile(card); }
                p().discardPile.shuffle();
            }
            int total = count / div;
            att(new DrawCardAction(total));
            if (this.overheated) { att(new AdvancePhaseAction(total)); }
            att(new GainEnergyAction(total));
            tickDuration();
        }
    }
}