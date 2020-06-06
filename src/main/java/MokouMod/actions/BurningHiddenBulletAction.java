package MokouMod.actions;

import MokouMod.util.mokouUtils;
import MokouMod.vfx.combat.BlueHemokinesisEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Utilities.squeenyUtils.p;


public class BurningHiddenBulletAction extends AbstractGameAction {
    private float scale;
    private DamageInfo info;
    private float x;

    public BurningHiddenBulletAction(AbstractCreature target, DamageInfo info, int amount) {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        if (Settings.FAST_MODE) { this.startDuration = 0.05F;
        } else { this.startDuration = 0.2F; }
        this.duration = this.startDuration;
        this.amount = amount;
        this.scale = 0.12F;
        this.x = p().hb.cX;
        this.y = p().hb.cY;
        this.tX = this.target.hb.cX;
        this.tY = this.target.hb.cY;
    }
    private float y;
    private float tX;
    private float tY;
    public void update() {
        if (this.target == null) { this.isDone = true; }
        if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
        } else {
            if (this.duration == this.startDuration) { AbstractDungeon.effectsQueue.add(new BlueHemokinesisEffect(this.x + MathUtils.random(60, -60F) * Settings.scale, this.y + MathUtils.random(60, -60F) * Settings.scale, this.tX, this.tY)); }
            this.duration -= Gdx.graphics.getDeltaTime();
            if (this.duration < 0.0F) {
                if (this.target.currentHealth > 0) {
                    this.info.applyPowers(this.info.owner, this.target);
                    this.target.damage(this.info);
                    if (this.amount > 1 && !((AbstractMonster) this.target).isDying && this.target.currentHealth > 0) {
                        this.amount--;
                        AbstractDungeon.actionManager.addToTop(new BurningHiddenBulletAction(this.target, this.info, this.amount));
                    }
                    if (Settings.FAST_MODE) { AbstractDungeon.actionManager.addToTop( new WaitAction(0.1F));
                    } else { AbstractDungeon.actionManager.addToTop( new WaitAction(0.35F)); }
                }
                this.isDone = true;
            }
        }
    }
}

