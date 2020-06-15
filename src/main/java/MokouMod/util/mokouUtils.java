package MokouMod.util;

import MokouMod.MokouMod;
import MokouMod.actions.AdvancePhaseAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.combat.BurstMechanics;
import MokouMod.powers.ResonatingAuraPower;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.logging.Logger;

import static MokouMod.MokouMod.runAnimation;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.p;
public class mokouUtils {
    /** Mokou General Utils */
    //Checks for Burst without incrementing Resonance - Used for cards.
    public static boolean anonymouscheckBurst() {
        return BurstMechanics.PlayerBurstField.isBurst.get(p()); }
    // Returns a random color, used for VFX.
    public static Color getRandomFireColor() {
        int i = MathUtils.random(3);
        switch (i) {
            case 0: return Color.ORANGE;
            case 1: return Color.YELLOW;
            default: return Color.RED;
        }
    }
    public static Color getRandomBlueFireColor() {
        int i = MathUtils.random(3);
        switch (i) {
            case 0: return Color.BLUE;
            case 1: return Color.NAVY;
            default: return Color.ROYAL;
        }
    }
    // Handles Mokou's Animations.
    public static void animationHandler(AbstractCard c) {
        if (c instanceof abs_mku_card && ((abs_mku_card) c).overheated) { runAnimation("Spell");
        } else {
            switch (c.type) {
                case ATTACK:
                    switch (c.rarity) {
                        case COMMON:
                            //runAnimation("Attack_L");
                            break;
                        case UNCOMMON:
                            //runAnimation("Attack_M");
                            break;
                        case RARE:
                            //runAnimation("Attack_H");
                            break;
                        default: break;
                    }
                    break;
                case SKILL:
                    //runAnimation("Skill");
                    break;
                case POWER:
                    //runAnimation("Spell");
                    //runAnimation("Power");
                    break;
                default: break;
            }
        }
    }
}

