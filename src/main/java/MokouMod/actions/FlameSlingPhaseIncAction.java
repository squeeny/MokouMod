package MokouMod.actions;

import MokouMod.util.mokouUtils;
import MokouMod.vfx.combat.unique.FlameSlingEffect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;

import static Utilities.squeenyUtils.doVfx;
import static Utilities.squeenyUtils.p;

public class FlameSlingPhaseIncAction extends AbstractGameAction {
    private boolean changeAlignment;

    public FlameSlingPhaseIncAction()
    {
        this.actionType = ActionType.WAIT;
    }


    @Override
    public void update() {

        int CELESTIAL_BODY_SIZE = 400;
        float CELESTIAL_BODY_OFFSET = CELESTIAL_BODY_SIZE / 2.0f;
        float CELESTIAL_BODY_X = (Settings.WIDTH / 2.0f) - CELESTIAL_BODY_OFFSET;
        float CELESTIAL_BODY_Y = Settings.HEIGHT - (CELESTIAL_BODY_OFFSET + 140.0f);

        doVfx(new FlameSlingEffect(p().drawX, p().drawY+(p().hb_h/2f), CELESTIAL_BODY_X + CELESTIAL_BODY_OFFSET, CELESTIAL_BODY_Y + CELESTIAL_BODY_OFFSET), true);

        this.isDone = true;
    }
}