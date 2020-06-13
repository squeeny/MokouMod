package MokouMod.powers;

import MokouMod.MokouMod;
import MokouMod.vfx.general.StraightFireParticle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Random;

import static Utilities.squeenyUtils.*;
public class SpontaneousHumanCombustionPower extends AbstractPower {
    public static final String POWER_ID = MokouMod.makeID(SpontaneousHumanCombustionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static Random rng = new Random();
    private static final float INTERVAL = 0.05f;
    private float particleTimer;
    public SpontaneousHumanCombustionPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = AbstractPower.PowerType.BUFF;
        updateDescription();
        isTurnBased = true;
        loadRegion("flameBarrier");
    }
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
       if (type == DamageInfo.DamageType.NORMAL) { damage += damage; }
       return damage;
    }
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){ atb(new ReducePowerAction(this.owner, this.owner, this, 1)); }
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
    @Override
    public void updateParticles() {
        this.particleTimer -= Gdx.graphics.getRawDeltaTime();
        if (this.particleTimer < 0.0F) {

            float xOff = ((owner.hb_w) * (float) rng.nextGaussian())*0.25f;
            if(MathUtils.randomBoolean()) {
                xOff = -xOff;
            }

            AbstractDungeon.effectList.add(new StraightFireParticle(owner.drawX + xOff, owner.drawY + MathUtils.random(owner.hb_h / 2f), 75f, 0));
            this.particleTimer = INTERVAL * 1.5F;
        }
    }
    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        particleTimer = 0.4f;
        updateDescription();
    }
    @Override
    public void stackPower(int i) {
        super.stackPower(i);
        updateDescription();
    }
}