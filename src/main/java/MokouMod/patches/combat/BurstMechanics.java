package MokouMod.patches.combat;

import MokouMod.cards.mku_rar.SearingBlow;
import MokouMod.powers.TrailblazerPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class BurstMechanics {

    @SpirePatch(clz = AbstractPlayer.class, method = SpirePatch.CLASS)
    public static class PlayerBurstField {
        public static SpireField<Boolean> isBurst = new SpireField<>(() -> false);
        public static SpireField<Integer> turnBurstAmount = new SpireField<>(() -> 0);
    }


    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfTurnRelics")
    public static class TurnEndBurstReset {
        @SpirePrefixPatch
        public static void patch(AbstractPlayer __instance) {
            //PlayerBurstField.isBurst.set(mokouUtils.p(), false);
            BurstMechanics.PlayerBurstField.turnBurstAmount.set(p(), 0);
        }
    }

    @SpirePatch(clz = UseCardAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, AbstractCreature.class})
    public static class CaptureBurstActivation {
        @SpireInsertPatch(locator = Locator.class)
        public static void patch(UseCardAction __instance, AbstractCard c, AbstractCreature target) {

            if(p().hasPower(TrailblazerPower.POWER_ID)){
                PlayerBurstField.isBurst.set(p(), true);
                for(AbstractCard card : p().hand.group){
                    if (card instanceof SearingBlow && ((SearingBlow) card).upgradeable){
                        card.upgrade();
                    }
                    else if (card instanceof SearingBlow && !((SearingBlow) card).upgradeable){
                        ((SearingBlow) card).upgradeable = true;
                    }
                }
            }
            else {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    PlayerBurstField.isBurst.set(p(), true);
                    for(AbstractCard card : p().hand.group){
                        if (card instanceof SearingBlow && ((SearingBlow) card).upgradeable){
                            card.upgrade();
                        }
                        else if (card instanceof SearingBlow && !((SearingBlow) card).upgradeable){
                            ((SearingBlow) card).upgradeable = true;
                        }
                    }
                } else {
                    PlayerBurstField.isBurst.set(p(), false);
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "powers");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }
}
