package MokouMod.actions;

import basemod.BaseMod;
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
public class ExhaustPileToHandAction extends AbstractGameAction {
    private ArrayList<AbstractCard> exhumes = new ArrayList<>();
    private AbstractCard Card2;

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhumeAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;
    private final boolean upgrade;
    private static int val;

    public ExhaustPileToHandAction(boolean upgrade, int val) {
        this.upgrade = upgrade;
        this.p = p();
        setValues(this.p, p(), this.amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        ExhaustPileToHandAction.val = val;
    }

    public void update() {
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator<AbstractCard> var5 = this.p.exhaustPile.group.iterator();

        while (var5.hasNext()) {
            AbstractCard card = var5.next();
            tmp.addToRandomSpot(card);
        }

        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (p().hand.size() == 10) {
                p().createHandIsFullDialog();
                this.isDone = true;
            } else if (tmp.isEmpty()) {
                this.isDone = true;
            } else {
                if (tmp.size() <= val) {

                    for (int i = 0; i < tmp.size(); i++) {

                        if (this.p.hand.size() == BaseMod.MAX_HAND_SIZE) {

                            this.p.createHandIsFullDialog();
                        } else {

                            AbstractCard card = tmp.getTopCard();

                            card.unfadeOut();

                            this.p.hand.addToHand(card);

                            if (p().hasPower("Corruption") && card.type == AbstractCard.CardType.SKILL) {

                                card.setCostForTurn(-9);
                            }

                            this.p.exhaustPile.removeCard(card);

                            tmp.removeCard(card);

                            if (this.upgrade && card.canUpgrade()) {
                                card.upgrade();
                            }

                            card.unhover();
                            card.fadingOut = false;
                        }
                    }

                    this.isDone = true;
                } else {

                    Iterator<AbstractCard> c = tmp.group.iterator();

                    while (c.hasNext()) {
                        AbstractCard Card2 = c.next();
                        Card2.stopGlowing();
                        Card2.unhover();
                        Card2.unfadeOut();
                    }

                    AbstractDungeon.gridSelectScreen.open(tmp, val, TEXT[0], false);
                    tickDuration();
                }
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                Iterator<AbstractCard> c;
                for (c = AbstractDungeon.gridSelectScreen.selectedCards.iterator(); c.hasNext(); Card2.unhover()) {

                    AbstractCard Card2 = c.next();

                    if (this.p.hand.size() == 10) {

                        this.p.createHandIsFullDialog();
                    } else {

                        this.p.hand.addToHand(Card2);
                        if (p().hasPower("Corruption") && Card2.type == AbstractCard.CardType.SKILL) {
                            Card2.setCostForTurn(-9);
                        }

                        this.p.exhaustPile.removeCard(Card2);

                        tmp.removeCard(Card2);

                        if (this.upgrade && Card2.canUpgrade()) {
                            Card2.upgrade();
                        }
                    }
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();

                this.p.hand.refreshHandLayout();

                this.p.exhaustPile.group.addAll(this.exhumes);

                this.exhumes.clear();

                for (c = this.p.exhaustPile.group.iterator(); c.hasNext(); Card2.target_y = 0.0F) {

                    AbstractCard Card2 = c.next();
                    Card2.unhover();
                    Card2.target_x = CardGroup.DISCARD_PILE_X;
                }
            }

            tickDuration();
        }

    }


}

