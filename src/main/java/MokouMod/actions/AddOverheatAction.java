package MokouMod.actions;

import MokouMod.MokouMod;
import MokouMod.cardmods.OverheatText;
import MokouMod.cards.mku_abs.abs_mku_card;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.CardModifierPatches.CardModifierFields.cardModifiers;

public class AddOverheatAction extends AbstractGameAction {

    public AddOverheatAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard card : p().exhaustPile.group){
                if(card instanceof abs_mku_card) {
                    CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(card.cardID);
                    cardModifiers.get(card).removeIf(AbstractCardModifier -> AbstractCardModifier instanceof OverheatText);
                    CardModifierManager.addModifier(card, new OverheatText(cardStrings.EXTENDED_DESCRIPTION[0]));
                }
            }
            for (AbstractCard card : p().drawPile.group){
                if(card instanceof abs_mku_card) {
                    CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(card.cardID);
                    cardModifiers.get(card).removeIf(AbstractCardModifier -> AbstractCardModifier instanceof OverheatText);
                    CardModifierManager.addModifier(card, new OverheatText(cardStrings.EXTENDED_DESCRIPTION[0]));
                }
            }
            for (AbstractCard card : p().discardPile.group){
                if(card instanceof abs_mku_card) {
                    CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(card.cardID);
                    cardModifiers.get(card).removeIf(AbstractCardModifier -> AbstractCardModifier instanceof OverheatText);
                    CardModifierManager.addModifier(card, new OverheatText(cardStrings.EXTENDED_DESCRIPTION[0]));
                }
            }
            for (AbstractCard card : p().hand.group){
                if(card instanceof abs_mku_card) {
                    CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(card.cardID);
                    cardModifiers.get(card).removeIf(AbstractCardModifier -> AbstractCardModifier instanceof OverheatText);
                    CardModifierManager.addModifier(card, new OverheatText(cardStrings.EXTENDED_DESCRIPTION[0]));
                }
            }
            MokouMod.tackybypass = true;
            tickDuration();
        }
        else{ this.isDone = true; }
    }
}