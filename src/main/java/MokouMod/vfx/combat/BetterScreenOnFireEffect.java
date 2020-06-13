package MokouMod.vfx.combat;

import MokouMod.powers.SpontaneousHumanCombustionPower;
import MokouMod.vfx.general.BetterGiantFireEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

import static Utilities.squeenyUtils.*;
public class BetterScreenOnFireEffect extends AbstractGameEffect {
    private float timer = 0.0F;
    private static final float INTERVAL = 0.05F;
    private float flameDuration;
    private String soundKey;

    public BetterScreenOnFireEffect(float duration, float flameDuration, String soundKey) {
        this.flameDuration = flameDuration;
        this.soundKey = soundKey;
        this.duration = duration;
    }
    public void update() {
        if (this.duration == this.startingDuration) {
            CardCrawlGame.sound.play(soundKey);
            if(p().hasPower(SpontaneousHumanCombustionPower.POWER_ID)) { AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.TEAL)); }
            else{ AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.FIREBRICK)); }
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        this.timer -= Gdx.graphics.getDeltaTime();
        if (this.timer < 0.0F) {
            AbstractDungeon.effectsQueue.add(new BetterGiantFireEffect(flameDuration));
            AbstractDungeon.effectsQueue.add(new BetterGiantFireEffect(flameDuration));
            AbstractDungeon.effectsQueue.add(new BetterGiantFireEffect(flameDuration));
            AbstractDungeon.effectsQueue.add(new BetterGiantFireEffect(flameDuration));
            AbstractDungeon.effectsQueue.add(new BetterGiantFireEffect(flameDuration));
            AbstractDungeon.effectsQueue.add(new BetterGiantFireEffect(flameDuration));
            AbstractDungeon.effectsQueue.add(new BetterGiantFireEffect(flameDuration));
            AbstractDungeon.effectsQueue.add(new BetterGiantFireEffect(flameDuration));
            this.timer = INTERVAL;
        }
        if (this.duration < 0.0F) { this.isDone = true; }
    }
    public void render(SpriteBatch sb) {}
    public void dispose() {}
}

