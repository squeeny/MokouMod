package MokouMod.patches.general;

import MokouMod.mechanics.ImmortalityManager;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

public class Immortality {
    @SpirePatch(clz = AbstractPlayer.class, method = SpirePatch.CLASS)
    public static class ImmortalityFields {
        public static SpireField<Integer> exhaustion = new SpireField<>(() -> 3);
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "damage", paramtypez = {DamageInfo.class})
    public static class ImmortalDeathPrevention {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn Insert(AbstractPlayer __instance, DamageInfo info) {

            if (ImmortalityManager.deathLogic()) {
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }


        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "deathScreen");
                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }


}
