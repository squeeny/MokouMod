package MokouMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class ExhaustTopCardAction extends AbstractGameAction { private boolean exhaustCards;

    public ExhaustTopCardAction(AbstractCreature target) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.source = p();
        this.target = target;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (p().drawPile.size() + p().discardPile.size() == 0) {
                this.isDone = true;
                return;
            }
            if (p().drawPile.isEmpty()) {
                addToTop(new ExhaustTopCardAction(this.target));
                addToTop(new EmptyDeckShuffleAction());
                this.isDone = true;
                return;
            }
            if (!p().drawPile.isEmpty()) {

                AbstractCard card = p().drawPile.getTopCard();

                card.current_y = -200.0F * Settings.scale;
                card.target_x = Settings.WIDTH / 2.0F + 200.0F * Settings.scale;
                card.target_y = Settings.HEIGHT / 2.0F;
                card.targetAngle = 0.0F;
                card.lighten(false);
                card.drawScale = 0.12F;
                card.targetDrawScale = 0.75F;
                AbstractDungeon.actionManager.addToTop( new WaitAction(0.4F));
                p().drawPile.moveToExhaustPile(card);
                p().drawPile.applyPowers();

                if (!Settings.FAST_MODE) {
                    addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                } else {
                    addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
                }
            }
            this.isDone = true;
        }
    }
}

