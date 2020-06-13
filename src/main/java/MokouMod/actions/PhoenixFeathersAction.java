package MokouMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import static Utilities.squeenyUtils.att;
import static Utilities.squeenyUtils.p;

public class PhoenixFeathersAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    private static final String[] TEXT = uiStrings.TEXT;
    private int exhaustAmount;
    public PhoenixFeathersAction(int exhaustAmount) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.exhaustAmount = exhaustAmount;
    }
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (p().hand.size() == 0) {
                this.isDone = true;
                return;
            }
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.exhaustAmount, true, true);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            int amount = 0;
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                amount++;
                p().hand.moveToExhaustPile(c);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            if (amount > 0) { att(new GainEnergyAction(amount)); }
        }
        tickDuration();
    }
}