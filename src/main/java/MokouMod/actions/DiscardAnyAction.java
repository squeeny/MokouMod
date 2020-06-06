package MokouMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class DiscardAnyAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;
    private AbstractPlayer p;
    public static int numDiscarded;

    public DiscardAnyAction(AbstractCreature target, AbstractCreature source, int amount) {

        this.p = (AbstractPlayer) target;

        setValues(target, source, amount);

        this.actionType = AbstractGameAction.ActionType.DISCARD;

        this.duration = DURATION;

    }

    public void update() {

        if (this.duration == DURATION) {

            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {

                this.isDone = true;

                return;

            }

            if (this.p.hand.size() == 0) {

                this.isDone = true;
                return;

            }

            if (this.amount > 0) {

                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, true, true);

                p().hand.applyPowers();

                tickDuration();

                return;

            }

            numDiscarded = this.amount;

        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {

            Iterator<AbstractCard> var4 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while (var4.hasNext()) {

                AbstractCard c = var4.next();

                this.p.hand.moveToDiscardPile(c);

                c.triggerOnManualDiscard();

            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;

        }

        tickDuration();

    }


}


