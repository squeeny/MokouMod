package MokouMod.actions;

import MokouMod.cardmods.ProfanedFlameMod;
import MokouMod.cards.mku_rar.ProfanedFlare;
import MokouMod.patches.cards.CardENUMs;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;

import static Utilities.squeenyUtils.*;
public class ProfanedFlameAction extends AbstractGameAction {

    private int damage;
    private boolean upgraded;
    private boolean overheat;
    public ProfanedFlameAction(int  damage, boolean upgraded, boolean overheat) {

        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.damage = damage;
        this.upgraded = upgraded;
        this.overheat = overheat;
    }

    public void update() {

        if (this.duration == Settings.ACTION_DUR_FAST) {

            CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ProfanedFlare.ID);

            for(AbstractCard var5: p().exhaustPile.group){
                if (var5.hasTag(CardENUMs.FLARE)) {
                    CardModifierManager.addModifier(var5, new ProfanedFlameMod(this.damage, this.overheat, this.overheat ? 1 : 0, cardStrings.EXTENDED_DESCRIPTION[1]));
                }
            }
            for(AbstractCard var5: p().drawPile.group){
                if (var5.hasTag(CardENUMs.FLARE)) {
                    CardModifierManager.addModifier(var5, new ProfanedFlameMod(this.damage, this.overheat, this.overheat ? 1 : 0, cardStrings.EXTENDED_DESCRIPTION[1]));
                }
            }
            for(AbstractCard var5: p().discardPile.group){
                if (var5.hasTag(CardENUMs.FLARE)) {
                    CardModifierManager.addModifier(var5, new ProfanedFlameMod(this.damage, this.overheat, this.overheat ? 1 : 0, cardStrings.EXTENDED_DESCRIPTION[1]));
                }
            }
            for(AbstractCard var5: p().hand.group){
                if (var5.hasTag(CardENUMs.FLARE)) {
                    CardModifierManager.addModifier(var5, new ProfanedFlameMod(this.damage, this.overheat, this.overheat ? 1 : 0, cardStrings.EXTENDED_DESCRIPTION[1]));
                }
            }
            tickDuration();
        }
        this.isDone = true;
    }
}
