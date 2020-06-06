package MokouMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class ExhaustPileToDiscardAction extends AbstractGameAction {
    private AbstractPlayer p;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhumeAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public ExhaustPileToDiscardAction() {
        this.p = p();
        setValues(this.p, p(), this.amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {

        if (this.duration == Settings.ACTION_DUR_FAST) {

            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            Iterator<AbstractCard> var5 = this.p.exhaustPile.group.iterator();

            while (var5.hasNext()) {
                AbstractCard card = var5.next();
                tmp.addToRandomSpot(card);
            }

        } else {
            if (this.p.exhaustPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            while (!this.p.exhaustPile.isEmpty()) {
                AbstractCard c = this.p.exhaustPile.getTopCard();
                this.p.exhaustPile.moveToDiscardPile(c);
                this.p.exhaustPile.removeCard(c);
                c.lighten(false);
                c.unhover();
                c.fadingOut = false;
            }
            this.isDone = true;
        }
        tickDuration();
    }



}
