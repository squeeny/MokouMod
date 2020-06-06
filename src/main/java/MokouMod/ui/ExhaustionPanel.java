package MokouMod.ui;

import MokouMod.powers.BlueFlarePower;
import MokouMod.twitch.SlayTheRelicsIntegration;
import Utilities.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;

import java.util.ArrayList;

import static MokouMod.MokouMod.makeID;
import static MokouMod.mechanics.ImmortalityManager.getExhaustion;
import static MokouMod.mechanics.ImmortalityManager.shouldRenderExhaustion;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class ExhaustionPanel extends AbstractPanel {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("ExhaustionPanel"));
    private static final Color ENERGY_TEXT_COLOR = new Color(1.0F, 1.0F, 0.86F, 1.0F);
    public static float fontScale = 1.0f;
    public static final float FONT_POP_SCALE = 2.0F;
    private Hitbox tipHitbox;
    private float energyVfxAngle;
    private float energyVfxScale;
    private Color energyVfxColor;
    public static float energyVfxTimer = 0.0f;
    public static final float ENERGY_VFX_TIME = 3.0F;
    public static final float ENERGY_VFX_HALF_TIME = ENERGY_VFX_TIME/2.0f;
    private static final float VFX_ROTATE_SPEED = -30.0F;
    private static final float COOLDOWN_AMT = 1F;
    private float cooldown = COOLDOWN_AMT;
    public static String image;
    public static Texture tex = OrbVfx("orange");
    private static ArrayList<PowerTip> tips;
    private static PowerTip tooltip;

    public ExhaustionPanel() {
        super(96.0F * Settings.scale, 320.0F * Settings.scale, Settings.WIDTH + 480.0F * Settings.scale, Settings.HEIGHT / 2.0f, 12.0F * Settings.scale, -12.0F * Settings.scale, tex, false);
        tipHitbox = new Hitbox(0, 0, 120.0F * Settings.scale, 120.0F * Settings.scale);
        energyVfxAngle = 0.0F;
        energyVfxScale = Settings.scale;
        energyVfxColor = Color.WHITE.cpy();
        tips = new ArrayList<>();
        tooltip = new PowerTip(uiStrings.TEXT[0], (uiStrings.TEXT[1] + uiStrings.TEXT[2] + getExhaustion()));
        tips.add(tooltip);
        updateTooltip();
    }
    public static Texture OrbVfx(String phase) {
        String OrbVfxChecker = phase.toLowerCase();
        switch (OrbVfxChecker) {
            case "blue":
                image = "CapriCoreResources/images/MokouMod/char/mokou/orb/bvfx.png";
                break;
            default:
                image ="CapriCoreResources/images/MokouMod/char/mokou/orb/vfx.png";
                break;
        }return TextureLoader.getTexture(image);
    }
    public void update() {
        this.updateVfx();
        if (fontScale != 1.0F) { fontScale = MathHelper.scaleLerpSnap(fontScale, FONT_POP_SCALE); }
        tipHitbox.update();
        if (tipHitbox.hovered && !AbstractDungeon.isScreenUp) { AbstractDungeon.overlayMenu.hoveredTip = true; }
    }
    private void updateVfx() {
        try {
            if (p().hasPower(BlueFlarePower.POWER_ID)) { tex = OrbVfx("blue");
            } else { tex = OrbVfx("orange"); }
        }
        catch(Exception e) { tex = OrbVfx("orange"); }
        if (!isHidden && shouldRenderExhaustion()) {
            cooldown -= Gdx.graphics.getRawDeltaTime();
            if (cooldown < 0.0f) {
                cooldown = (10f / (getExhaustion() + 1));
                energyVfxTimer = ENERGY_VFX_TIME;
            }
            if (energyVfxTimer != 0.0F) {
                energyVfxColor.a = Interpolation.exp10In.apply(0.5F, 0.0F, 1.0F - energyVfxTimer / ENERGY_VFX_TIME);
                energyVfxAngle += Gdx.graphics.getRawDeltaTime() * VFX_ROTATE_SPEED;
                if(energyVfxTimer > ENERGY_VFX_HALF_TIME) { energyVfxScale = Settings.scale * Interpolation.exp10In.apply(1.0F, 0.1F, 1.0F - (1.0f - (energyVfxTimer - ENERGY_VFX_HALF_TIME) / ENERGY_VFX_HALF_TIME));
                } else { energyVfxScale = Settings.scale * Interpolation.exp10In.apply(1.0F, 0.1F, 1.0F - energyVfxTimer / ENERGY_VFX_HALF_TIME); }
                energyVfxTimer -= Gdx.graphics.getRawDeltaTime();
                if (energyVfxTimer < 0.0F) {
                    energyVfxTimer = 0.0F;
                    energyVfxColor.a = 0.0F;
                }
            }
        }
    }
    public void render(SpriteBatch sb) {
        if (!isHidden && shouldRenderExhaustion()) {
            tipHitbox.move(current_x, current_y);
            renderVfx(sb);
            String text = Integer.toString(getExhaustion());
            FontHelper.renderFontCentered(sb, FontHelper.energyNumFontRed, text, current_x, current_y, ENERGY_TEXT_COLOR);
            tipHitbox.render(sb);
            if (tipHitbox.hovered && !AbstractDungeon.isScreenUp) { TipHelper.renderGenericTip(current_x + ((tex.getWidth() / 2.0f) * Settings.scale), current_y + ((tex.getHeight() / 2.0f) * Settings.scale), uiStrings.TEXT[0], uiStrings.TEXT[1] + uiStrings.TEXT[2] + getExhaustion()); }
            SlayTheRelicsIntegration.renderTipHitbox(tipHitbox, tips);
        }
    }
    private void renderVfx(SpriteBatch sb) {
        if (energyVfxTimer != 0.0F) {
            sb.setBlendFunction(770, 1);
            sb.setColor(this.energyVfxColor);
            sb.draw(tex, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.energyVfxScale, this.energyVfxScale, -this.energyVfxAngle + 50.0F, 0, 0, 256, 256, true, false);
            sb.draw(tex, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.energyVfxScale, this.energyVfxScale, this.energyVfxAngle, 0, 0, 256, 256, false, false);
            sb.setBlendFunction(770, 771);
        }
    }
    public static void updateTooltip()
    {
        if (p() != null) {
            tooltip.body = uiStrings.TEXT[1] + uiStrings.TEXT[2] + getExhaustion();
            tips.clear();
            tips.add(tooltip);
        }
    }
}