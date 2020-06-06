package MokouMod.patches.combat;

import MokouMod.mechanics.ImmortalityManager;
import MokouMod.ui.ExhaustionPanel;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.ui.panels.ExhaustPanel;
import javassist.CtBehavior;

public class ExhaustionMechanics {
    @SpirePatch(clz = AbstractPlayer.class, method = SpirePatch.CLASS)
    public static class ExhaustionFields {
        public static SpireField<ExhaustionPanel> panel = new SpireField<>(ExhaustionPanel::new);
    }

    @SpirePatch(clz = OverlayMenu.class, method ="update")
    public static class PanelUpdate {
        @SpireInsertPatch(locator = Locator.class)
        public static void patch(OverlayMenu __instance) {
            ImmortalityManager.getExhaustionPanel().update();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(OverlayMenu.class, "updateBlackScreen");
                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }

    @SpirePatch(clz = OverlayMenu.class, method ="render")
    public static class PanelRender {
        @SpireInsertPatch(locator = Locator.class)
        public static void patch(OverlayMenu __instance, SpriteBatch sb) {
            ImmortalityManager.getExhaustionPanel().render(sb);
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ExhaustPanel.class, "render");
                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }

    @SpirePatch(clz = OverlayMenu.class, method ="showCombatPanels")
    public static class ShowPanel {
        @SpirePrefixPatch
        public static void patch(OverlayMenu __instance) {
            ImmortalityManager.getExhaustionPanel().show();
        }
    }

    @SpirePatch(clz = OverlayMenu.class, method ="hideCombatPanels")
    public static class HidePanel {
        @SpirePrefixPatch
        public static void patch(OverlayMenu __instance) {
            ImmortalityManager.getExhaustionPanel().hide();
        }
    }
}