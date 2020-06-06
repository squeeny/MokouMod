package MokouMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class DiscardDrawPileAction extends AbstractGameAction {


    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;
    private int amount;
    private boolean isRandom;

    public DiscardDrawPileAction(int amount, boolean random) {

        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;

        this.duration = Settings.ACTION_DUR_FAST;

        this.p = p();

        this.amount = amount;

        this.isRandom = random;

    }


    public void update() {

        if (this.duration == Settings.ACTION_DUR_FAST) {

            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            Iterator<AbstractCard> var5 = this.p.drawPile.group.iterator();

            while (var5.hasNext()) {

                AbstractCard card = var5.next();
                tmp.addToRandomSpot(card);

            }

            if (tmp.size() == 0) {

                this.isDone = true;
                return;

            }

            if (tmp.size() <= this.amount && this.isRandom) {

                while (!this.p.drawPile.isEmpty()) {

                    AbstractCard c = this.p.drawPile.getTopCard();
                    this.p.drawPile.moveToDiscardPile(c);
                    this.p.drawPile.removeCard(c);

                }

                this.isDone = true;

            } else {

                if (this.isRandom) {

                    for (int i = 0; i < this.amount; i++) {

                        if (!tmp.isEmpty()) {

                            tmp.shuffle();
                            AbstractCard c = tmp.getBottomCard();
                            this.p.drawPile.moveToDiscardPile(c);
                            this.p.drawPile.removeCard(c);

                        }

                    }

                    this.isDone = true;
                    return;

                }
                AbstractDungeon.gridSelectScreen.open(tmp, this.amount, true, TEXT[0]);
            }
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {

            Iterator<AbstractCard> var3 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

            while (var3.hasNext()) {

                AbstractCard c = var3.next();
                p().drawPile.moveToDiscardPile(c);
                this.p.drawPile.removeCard(c);

            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();

        }

        tickDuration();

    }


}

