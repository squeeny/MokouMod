
package MokouMod.vfx.general;

import MokouMod.powers.SpontaneousHumanCombustionPower;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static Utilities.squeenyUtils.*;
public class BlueFastSmokeParticle extends AbstractGameEffect {
    private float x;
    private float y;
    private float vX;
    private float scale = 0.01F;
    private float targetScale;
    private static AtlasRegion img;

    public BlueFastSmokeParticle(float x, float y) {
        if (img == null) { img = ImageMaster.EXHAUST_L; }
        this.targetScale = MathUtils.random(0.3F, 0.6F) * Settings.scale;
        if(p().hasPower(SpontaneousHumanCombustionPower.POWER_ID)){ this.color = new Color(MathUtils.random(0.5F, 0.8F), MathUtils.random(0.8F, 1.0F), 1.0F, 1.0F);}
        else { this.color = new Color(1.0F, MathUtils.random(0.8F, 1.0F), MathUtils.random(0.5F, 0.8F), 1.0F); }
        this.x = x - (float)img.packedWidth / 2.0F;
        this.y = y - (float)img.packedHeight / 2.0F;
        this.rotation = MathUtils.random(360.0F);
        this.duration = 0.6F;
    }
    public void update() {
        Color var10000;
        if(p().hasPower(SpontaneousHumanCombustionPower.POWER_ID)) {
            if (this.color.r > 0.1F) {
                var10000 = this.color;
                var10000.b -= Gdx.graphics.getDeltaTime() * 4.0F;
                var10000 = this.color;
                var10000.g -= Gdx.graphics.getDeltaTime() * 3.0F;
                var10000 = this.color;
                var10000.r -= Gdx.graphics.getDeltaTime() * 0.5F;
            } else if (this.color.g > 0.1F) {
                var10000 = this.color;
                var10000.g -= Gdx.graphics.getDeltaTime() * 4.0F;
            } else if (this.color.b > 0.1F) {
                var10000 = this.color;
                var10000.b -= Gdx.graphics.getDeltaTime() * 4.0F;
            }
        } else{
            if (this.color.b > 0.1F) {
                var10000 = this.color;
                var10000.b -= Gdx.graphics.getDeltaTime() * 4.0F;
                var10000 = this.color;
                var10000.g -= Gdx.graphics.getDeltaTime() * 3.0F;
                var10000 = this.color;
                var10000.r -= Gdx.graphics.getDeltaTime() * 0.5F;
            } else if (this.color.g > 0.1F) {
                var10000 = this.color;
                var10000.g -= Gdx.graphics.getDeltaTime() * 4.0F;
            } else if (this.color.r > 0.1F) {
                var10000 = this.color;
                var10000.r -= Gdx.graphics.getDeltaTime() * 4.0F;
            }
        }
        if (this.color.b < 0.0F) { this.color.b = 0.0F; }
        if (this.color.g < 0.0F) { this.color.g = 0.0F; }
        if (this.color.r < 0.0F) { this.color.r = 0.0F; }
        this.scale = Interpolation.swingIn.apply(this.targetScale, 0.1F, this.duration / 0.6F);
        this.rotation += this.vX * 2.0F * Gdx.graphics.getDeltaTime();
        this.color.a = this.duration;
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) { this.isDone = true; }
    }
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(img, this.x, this.y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale, this.scale, this.rotation);
    }
    public void dispose() { }
}
