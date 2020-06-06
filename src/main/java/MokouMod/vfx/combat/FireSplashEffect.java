package MokouMod.vfx.combat;

import MokouMod.util.mokouUtils;
import MokouMod.vfx.general.RedFireBurstParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;

import java.util.Random;

public class FireSplashEffect extends AbstractGameEffect {
    private AbstractCreature c;
    private int amount;
    private static Random rng = new Random();

    public FireSplashEffect(AbstractCreature c, int amount) {
        this.c = c;
        this.amount = amount;
    }
    public void update() {
        float xOff;
        for (int i = 0; i < amount; ++i) {
            xOff = ((c.hb_w) * (float) rng.nextGaussian())*0.25f;
            if(MathUtils.randomBoolean()) { xOff = -xOff; }
            AbstractDungeon.effectsQueue.add(new RedFireBurstParticleEffect(c.drawX + xOff, c.drawY + MathUtils.random(c.hb_h/2f)));
            AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(c.drawX, c.drawY, mokouUtils.getRandomFireColor()));
        }
        this.isDone = true;
    }
    public void render(SpriteBatch sb) { }
    public void dispose() { }
}
