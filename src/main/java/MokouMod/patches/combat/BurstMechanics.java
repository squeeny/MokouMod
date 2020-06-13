package MokouMod.patches.combat;

import MokouMod.cards.mku_rar.SearingBlow;
import MokouMod.interfaces.onBurstSubscriber;
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
    }
    @SpirePatch(clz = UseCardAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, AbstractCreature.class})
    public static class CaptureBurstActivation {
        @SpireInsertPatch(locator = Locator.class)
        public static void patch(UseCardAction __instance, AbstractCard c, AbstractCreature target) {
            if (c.type == AbstractCard.CardType.ATTACK) { PlayerBurstField.isBurst.set(p(), true);
            } else { PlayerBurstField.isBurst.set(p(), false); }
        }
        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "powers");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }
}
