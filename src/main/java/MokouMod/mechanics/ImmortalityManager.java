package MokouMod.mechanics;

import MokouMod.actions.ClearAllDebuffsAction;
import MokouMod.patches.combat.ExhaustionMechanics;
import MokouMod.patches.general.Immortality;
import MokouMod.ui.ExhaustionPanel;
import MokouMod.vfx.combat.BetterScreenOnFireEffect;
import MokouMod.vfx.combat.unique.ReviveEffect;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static MokouMod.characters.MKU.Enums.MKU;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class ImmortalityManager {
    public static final int MAX_DEATH_COUNT = 0;
    public static boolean isImmortal() {
        return p().chosenClass == MKU;
    }
    public static boolean deathLogic() {
        if(isImmortal()) {
            if (getExhaustion() > MAX_DEATH_COUNT) {
                p().isDead = false;
                addExhaustion(-1);
                atb(new ClearAllDebuffsAction(p()));
                p().currentHealth = MathUtils.floor(p().maxHealth / 4);
                p().healthBarUpdatedEvent();
                if(getExhaustion() != 0) { doVfx(new ReviveEffect(p())); }
                else{
                    doVfx(new BetterScreenOnFireEffect(1.5f, 1.5f, "ATTACK_FLAME_BARRIER"));
                    doVfx(new ReviveEffect(p()));
                }
                return true;
            }
        }
        return false;
    }
    public static ExhaustionPanel getExhaustionPanel() { return ExhaustionMechanics.ExhaustionFields.panel.get(p()); }
    public static int getExhaustion() {
        if(p() != null) { return Immortality.ImmortalityFields.exhaustion.get(p()); }
        return 0;
    }
    public static void setExhaustion(int newVal) {
        if(Immortality.ImmortalityFields.exhaustion.get(p()) != null) { Immortality.ImmortalityFields.exhaustion.set(p(), newVal); }
    }
    public static void addExhaustion(int addVal) {
        if(p() != null) {
            Immortality.ImmortalityFields.exhaustion.set(p(), Immortality.ImmortalityFields.exhaustion.get(p()) + addVal);
            ExhaustionPanel.updateTooltip();
        }
    }
    public static boolean shouldRenderExhaustion() { return CardCrawlGame.isInARun() && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && isImmortal(); }
}
