package MokouMod.vfx.combat.unique;

import MokouMod.vfx.general.BlueInflameEffect;
import MokouMod.vfx.general.ExplosionSmallWorkAround;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ReviveEffect extends AbstractGameEffect {
    private float x, y;
    private AbstractCreature owner;
    public ReviveEffect(AbstractCreature c) {
        this.x = c.hb.cX;
        this.y = c.hb.cY;
        owner = c;
    }
    @Override
    public void update() {
        AbstractDungeon.effectsQueue.add(new BlueInflameEffect(owner));
        for (int i = 0; i < 5; ++i) { AbstractDungeon.effectsQueue.add(new ExplosionSmallWorkAround(x +( MathUtils.random(0, 200)-100), y+( MathUtils.random(0, 200)-100))); }
        this.isDone = true;
    }
    @Override
    public void render(SpriteBatch sb) { }
    @Override
    public void dispose() { }
}
