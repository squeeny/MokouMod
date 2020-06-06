package MokouMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.Iterator;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;

public class DiscardPileToHandAction extends AbstractGameAction {
    private ArrayList<AbstractCard> discard = new ArrayList<>();

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;
    private boolean israndom;
    private boolean istype;
    private AbstractCard.CardType type;

    public DiscardPileToHandAction(int amount, boolean random, boolean istype, AbstractCard.CardType type) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = p();
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
        this.israndom = random;
        this.istype = istype;
        this.type = type;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            Iterator<AbstractCard> c = this.p.discardPile.group.iterator();

            while (c.hasNext()) {
                AbstractCard card = c.next();

                if (!this.istype) {
                    tmp.addToRandomSpot(card);
                    continue;
                }
                if (this.type != AbstractCard.CardType.CURSE) {
                    if (card.type == this.type)
                        tmp.addToRandomSpot(card);
                    continue;
                }
                if (this.type == AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.STATUS) {
                    tmp.addToRandomSpot(card);
                }
            }

            if (this.p.discardPile.isEmpty()) {
                this.p.discardPile.group.addAll(this.discard);
                this.discard.clear();
                this.isDone = true;
            } else {
                if (tmp.size() == 0) {
                    this.isDone = true;
                    return;
                }

                if (this.israndom) {

                    for (int i = 0; i < this.amount; i++) {

                        if (!tmp.isEmpty()) {
                            tmp.shuffle();
                            AbstractCard card = tmp.getBottomCard();
                            tmp.removeCard(card);
                            if (this.p.hand.size() == 10) {
                                this.p.createHandIsFullDialog();
                            } else {
                                this.p.hand.addToHand(card);
                                this.p.discardPile.removeCard(card);

                                card.lighten(false);
                                card.unhover();

                                this.p.hand.refreshHandLayout();
                            }
                        }
                    }

                    this.isDone = true;
                    return;
                }

                if (this.duration == 0.5F) {

                    AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[1], false);
                    tickDuration();
                }

                else if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {

                    Iterator<AbstractCard> var4 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                    while (var4.hasNext()) {

                        if (this.p.hand.size() == 10) {
                            this.p.createHandIsFullDialog();
                            continue;
                        }
                        AbstractCard card = var4.next();
                        this.p.hand.addToHand(card);
                        this.p.discardPile.removeCard(card);
                        card.lighten(false);
                        card.unhover();
                        this.p.hand.refreshHandLayout();
                    }

                    CardCrawlGame.dungeon.checkForPactAchievement();
                    AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                    AbstractDungeon.gridSelectScreen.selectedCards.clear();
                }
            }

            this.isDone = true;
        }

        tickDuration();
    }



}
