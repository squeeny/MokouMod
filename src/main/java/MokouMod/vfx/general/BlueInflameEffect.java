package MokouMod.vfx.general;

import MokouMod.powers.SpontaneousHumanCombustionPower;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ExhaustEmberEffect;
import com.megacrit.cardcrawl.vfx.combat.FlameParticleEffect;

import static Utilities.squeenyUtils.*;
public class BlueInflameEffect extends AbstractGameEffect {
    float x;
    float y;

    public BlueInflameEffect(AbstractCreature creature) {
        this.x = creature.hb.cX;
        this.y = creature.hb.cY;
    }
    public void update() {
        CardCrawlGame.sound.play("ATTACK_FIRE");
        int i;
        if (p().hasPower(SpontaneousHumanCombustionPower.POWER_ID))
            for (i = 0; i < 75; i++) { AbstractDungeon.effectsQueue.add(new BlueFlameParticleEffect(this.x, this.y)); }
        else {
            for (i = 0; i < 75; i++) { AbstractDungeon.effectsQueue.add(new FlameParticleEffect(this.x, this.y)); }
        }
        for (i = 0; i < 20; i++) { AbstractDungeon.effectsQueue.add(new ExhaustEmberEffect(this.x, this.y)); }
        this.isDone = true;
    }
    public void render(SpriteBatch sb) {}
    public void dispose() {}
}