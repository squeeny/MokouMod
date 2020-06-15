package MokouMod.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

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
            if (count == 0) { this.isDone = true; }
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            if (count > 0) {
                int total = count / div;
                System.out.println(total);
                for(int i = 0; i < total; i++){
                    att(new DrawCardAction(1));
                    if (this.overheated) { att(new AdvancePhaseAction()); }
                    att(new GainEnergyAction(1));
                }
                for (AbstractCard card : p().exhaustPile.group) {
                    if (p().hasPower("Corruption") && (card.type == AbstractCard.CardType.SKILL)) { card.setCostForTurn(-9); }
                    stanceChoices.add(card); }
                if(!stanceChoices.isEmpty()) {
                    for(AbstractCard c: stanceChoices) {
                        att(new MakeTempCardInDiscardAction(c, 1));
                        //p().discardPile.addToRandomSpot(c.makeStatEquivalentCopy());
                        p().exhaustPile.removeCard(c);
                    }
                }
            }
            tickDuration();
        }
        this.isDone = true;
    }
}