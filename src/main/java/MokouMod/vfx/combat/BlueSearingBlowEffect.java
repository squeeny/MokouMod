package MokouMod.vfx.combat;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class BlueSearingBlowEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private int timesUpgraded;

    public BlueSearingBlowEffect(float x, float y, int timesUpgraded) {
        this.x = x;
        this.y = y;
        this.timesUpgraded = timesUpgraded;
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, true);
    }

    public void update() {
        CardCrawlGame.sound.playA("ATTACK_FIRE", 0.3F);
        CardCrawlGame.sound.playA("ATTACK_HEAVY", -0.3F);
        float dst = 180.0F + (float)this.timesUpgraded * 3.0F;
        AbstractDungeon.effectsQueue.add(new BlueFireballEffect(this.x - dst * Settings.scale, this.y, this.x + dst * Settings.scale, this.y - 50.0F * Settings.scale, this.timesUpgraded));
        this.isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
