package MokouMod.actions;

import MokouMod.MokouMod;
import MokouMod.cards.mku_abs.abs_mku_card;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class LoseOverheatAction extends AbstractGameAction {

    public LoseOverheatAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard card : p().exhaustPile.group){
                if(card instanceof abs_mku_card) {
                    CardModifierManager.removeModifiersById(card, MokouMod.makeID("Overheat"), false);
                }
            }
            for (AbstractCard card : p().drawPile.group){
                if(card instanceof abs_mku_card) {
                    CardModifierManager.removeModifiersById(card, MokouMod.makeID("Overheat"), false);
                }
            }
            for (AbstractCard card : p().discardPile.group){
                if(card instanceof abs_mku_card) {
                    CardModifierManager.removeModifiersById(card, MokouMod.makeID("Overheat"), false);
                }
            }
            for (AbstractCard card : p().hand.group){
                if(card instanceof abs_mku_card) {
                    CardModifierManager.removeModifiersById(card, MokouMod.makeID("Overheat"), false);
                }
            }
            MokouMod.tackybypass = true;
            tickDuration();
        }
        else{ this.isDone = true; }
    }
}