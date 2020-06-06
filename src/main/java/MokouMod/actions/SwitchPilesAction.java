package MokouMod.actions;

import MokouMod.util.mokouUtils;
import MokouMod.vfx.combat.FlameBurstEffect;
import MokouMod.vfx.combat.MoveImageWithSparkleEffect;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.util.ArrayList;

import static Utilities.squeenyUtils.p;

public class SwitchPilesAction extends AbstractGameAction {

    public SwitchPilesAction() {
        super();
    }

    public void update() {
        if (!p().drawPile.isEmpty()) {
            AbstractDungeon.effectsQueue.add(new MoveImageWithSparkleEffect(CardGroup.DRAW_PILE_X,
                    CardGroup.DRAW_PILE_Y,
                    CardGroup.DISCARD_PILE_X,
                    CardGroup.DISCARD_PILE_Y,
                    ImageMaster.DECK_BTN_BASE,
                    Color.ORANGE.cpy(),
                    "POWER_TIME_WARP",
                    new FlameBurstEffect(CardGroup.DISCARD_PILE_X, CardGroup.DISCARD_PILE_Y, 20)
            ));
        }
        if (!p().discardPile.isEmpty()) {
            AbstractDungeon.effectsQueue.add(new MoveImageWithSparkleEffect(CardGroup.DISCARD_PILE_X,
                    CardGroup.DISCARD_PILE_Y,
                    CardGroup.DRAW_PILE_X,
                    CardGroup.DRAW_PILE_Y,
                    ImageMaster.DECK_BTN_BASE,
                    Color.BLUE.cpy(),
                    "POWER_TIME_WARP",
                    new FlameBurstEffect(CardGroup.DRAW_PILE_X, CardGroup.DRAW_PILE_Y, 20)
            ));
        }
        ArrayList<AbstractCard> tmp = p().discardPile.group;
        p().discardPile.group = p().drawPile.group;
        p().drawPile.group = tmp;
        isDone = true;
    }
}
