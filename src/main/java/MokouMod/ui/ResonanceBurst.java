package MokouMod.ui;

import MokouMod.interfaces.onGainOverheatSubscriber;
import MokouMod.patches.combat.ResonanceMechanics;
import MokouMod.powers.SpontaneousHumanCombustionPower;
import MokouMod.powers.OverheatPower;
import MokouMod.powers.PhoenixFormPower;
import MokouMod.twitch.SlayTheRelicsIntegration;
import MokouMod.vfx.general.BlueInflameEffect;
import MokouMod.vfx.general.CalmFireEffect;
import MokouMod.vfx.general.CasualFlameParticleEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;

import static MokouMod.MokouMod.makeID;
import static MokouMod.MokouMod.tackybypass;
import static Utilities.squeenyUtils.*;
public class ResonanceBurst {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("ResonanceBurst"));
    private static final String[] TEXT = uiStrings.TEXT;
    private static final float TEXT_X = Settings.WIDTH / 2.0f;
    private static final float TEXT_Y = Settings.HEIGHT - 225.0F * Settings.scale;
    private static final int POSITION_SIZE = 400;
    private static final float POSITION_OFFSET = POSITION_SIZE / 2.0f;
    private static final float POSITION_X = (Settings.WIDTH / 2.0f) - POSITION_OFFSET;
    private static final float POSITION_Y = Settings.HEIGHT - (POSITION_OFFSET + 140.0f);
    private int roomLastRendered;
    private Color renderColor;
    private Hitbox hitbox;
    private ArrayList<PowerTip> tips;
    private PowerTip tooltip;
    private static final float CD = 0.05f;
    private ArrayList<AbstractGameEffect> flameEffects = new ArrayList<>();
    private ArrayList<AbstractGameEffect> baseEffects = new ArrayList<>();
    private int targetCount;
    private float calmFireCooldown = CD;

    public ResonanceBurst()
    {
        tips = new ArrayList<>();
        roomLastRendered = -1;
        float hitboxSize = 60 * Settings.scale;
        float hitboxOffset = hitboxSize / 2.0f;
        hitbox = new Hitbox(TEXT_X - hitboxOffset, TEXT_Y - hitboxOffset, hitboxSize, hitboxSize);
        renderColor = new Color(1.0f, 1.0f, 1.0f, 0.0f);
        tooltip = new PowerTip(TEXT[0], TEXT[1]);
        tips.add(tooltip);
        updateTooltip();
    }
    public void reset() {
        renderColor.a = 0.0f;
        updateTooltip();
    }
    public void hide()
    {
        renderColor.a = 0.0f;
    }
    public void updateTooltip() { if (p() != null) { tooltip.body = String.format(TEXT[1], ResonanceMechanics.resonanceburstPhase.get(p()), ResonanceMechanics.maxResonanceBurstPhase.get(p())); } }
    public void render(SpriteBatch sb, boolean fading) {
        if (renderColor.a > 0.0f || !fading) {
            hitbox.update();
            if (hitbox.hovered) { TipHelper.queuePowerTips(TEXT_X - 150 * Settings.scale, TEXT_Y - 80.0F * Settings.scale, this.tips); }
            if (tackybypass && ResonanceMechanics.resonanceburstPhase.get(p()) == ResonanceMechanics.maxResonanceBurstPhase.get(p())) {
                tackybypass = false;
                doVfx(new BlueInflameEffect(p()));
                doPow(p(), new OverheatPower(p(), 1));
                for(AbstractPower p : p().powers){
                    if(p instanceof onGainOverheatSubscriber){ ((onGainOverheatSubscriber) p).onGainOverheat();}
                }
                int x = ResonanceMechanics.resonanceburstPhase.get(p());
                if(p().hasPower(PhoenixFormPower.POWER_ID)){ x = x / 2; }
                else { x = 0; }
                ResonanceMechanics.resonanceburstPhase.set(p(), x);
                tackybypass = true;
                updateTooltip();
            }
            if (AbstractDungeon.floorNum != roomLastRendered) {
                roomLastRendered = AbstractDungeon.floorNum;
                renderColor.a = 0.0f;
            } else if (fading) {
                if (renderColor.a > 0.0f) { renderColor.a -= Gdx.graphics.getDeltaTime() * 0.5f; }
                if (renderColor.a < 0.0f) { renderColor.a = 0.0f; }
            } else if (renderColor.a < 1.0f) { renderColor.a += Gdx.graphics.getDeltaTime() * 0.5f;
                if (renderColor.a > 1.0f) { renderColor.a = 1.0f; }
            }
            sb.setColor(renderColor);
            for (AbstractGameEffect e : baseEffects) { e.render(sb); }
            for (AbstractGameEffect e : flameEffects) { e.render(sb); }
            for (AbstractGameEffect e : baseEffects) { e.update(); }
            for (AbstractGameEffect e : flameEffects) { e.update(); }
            targetCount = ResonanceMechanics.resonanceburstPhase.get(p()) / 2;
            baseEffects.removeIf((e) -> e.isDone);
            flameEffects.removeIf((e) -> e.isDone);
            calmFireCooldown -= Gdx.graphics.getRawDeltaTime();
            int max = Math.min(targetCount / 4, targetCount - flameEffects.size());
            for (int i = 0; i < max; i++) { flameEffects.add(new CasualFlameParticleEffect(POSITION_X + POSITION_OFFSET, POSITION_Y + POSITION_OFFSET - 30F )); }
            if (calmFireCooldown < 0) {
                calmFireCooldown += CD;
                if(p().hasPower(SpontaneousHumanCombustionPower.POWER_ID)) { baseEffects.add(new CalmFireEffect(POSITION_X + POSITION_OFFSET, POSITION_Y + POSITION_OFFSET, Color.TEAL)); }
                else { baseEffects.add(new CalmFireEffect(POSITION_X + POSITION_OFFSET, POSITION_Y + POSITION_OFFSET, Color.ORANGE)); }
            }
            FontHelper.renderFontCentered(sb, FontHelper.topPanelAmountFont, Integer.toString(ResonanceMechanics.resonanceburstPhase.get(p())), TEXT_X, TEXT_Y, renderColor);
            SlayTheRelicsIntegration.renderTipHitbox(hitbox, tips);
        }
    }

}
