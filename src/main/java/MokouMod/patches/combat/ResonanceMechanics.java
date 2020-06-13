package MokouMod.patches.combat;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.cards.CardENUMs;
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

import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.*;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = SpirePatch.CLASS
)
public class ResonanceMechanics {
    public static SpireField<Integer> resonanceburstPhase = new SpireField<>(()->0);
    public static SpireField<Integer> maxResonanceBurstPhase = new SpireField<>(()->10);
    public static SpireField<Integer> resonanceTurnAmount = new SpireField<>(() -> 0);
    public static SpireField<Boolean> geothermalResonance = new SpireField<>(()-> false);
    public static SpireField<Boolean> geothermalResonanceAuth = new SpireField<>(()-> false);

    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfTurnRelics")
    public static class TurnEndResonanceReset {
        @SpirePrefixPatch
        public static void patch(AbstractPlayer __instance) { ResonanceMechanics.resonanceTurnAmount.set(p(), 0); }
    }

    @SpirePatch(clz = UseCardAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, AbstractCreature.class})
    public static class CaptureResonanceActivation {
        @SpireInsertPatch(locator = ResonanceMechanics.CaptureResonanceActivation.Locator.class)
        public static void patch(UseCardAction __instance, AbstractCard c, AbstractCreature target) {
            if((anonymouscheckBurst() && c.hasTag(CardENUMs.BURST))){
                System.out.println(c + " is in burst and has a burst tag");
                atb(new AdvancePhaseAction()); }
            else if(p().hasPower(TrailblazerPower.POWER_ID)){
                System.out.println(c + " : Trailblazer, resolves regardless");
                atb(new AdvancePhaseAction()); }
            else if(!anonymouscheckBurst() && c instanceof abs_mku_card){
                if(((abs_mku_card) c).overheated && ((abs_mku_card) c).profaned ){
                    System.out.println(c + " overheated and profaned, not in burst");
                    atb(new AdvancePhaseAction()); }
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