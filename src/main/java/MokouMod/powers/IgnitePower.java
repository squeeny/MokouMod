package MokouMod.powers;

import MokouMod.MokouMod;
import MokouMod.interfaces.onEnemyGainIgniteSubscriber;
import MokouMod.interfaces.onIgniteLoseHPSubscriber;
import MokouMod.vfx.general.StraightFireParticle;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.Random;

import static Utilities.squeenyUtils.*;

public class IgnitePower extends AbstractPower implements CloneablePowerInterface,
        onIgniteLoseHPSubscriber {
    public static final String POWER_ID = MokouMod.makeID(IgnitePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static Random rng = new Random();
    public static Color tintCol = Color.ORANGE.cpy().add(0.1f, 0.3f, 0.3f, 1.0f);
    private static final float INTERVAL = 0.05f;
    private float particleTimer;
    public IgnitePower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = this.owner == p() ? PowerType.BUFF : PowerType.DEBUFF;
        updateDescription();
        isTurnBased = true;
        loadRegion("explosive");
        canGoNegative = false;
    }
    @Override
    public void updateDescription() {
        this.description = this.owner == p() ? String.format(DESCRIPTIONS[1], this.amount) : String.format(DESCRIPTIONS[0], this.amount);
    }
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (this.owner == p() && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS) {
            flash();
            System.out.println("player");
            att(new ReducePowerAction(this.owner, this.owner, this, this.amount));
            doPow(p(), new VigorPower(p(), this.amount), false);
        } else if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && damageAmount > 0) {
            flash();
            System.out.println("enemy");
            att(new ReducePowerAction(this.owner, this.owner, this, this.amount));
            doPow(p(), new VigorPower(p(), this.amount), false);
        }
        return damageAmount;
    }
    @Override
    public void updateParticles() {
        this.particleTimer -= Gdx.graphics.getRawDeltaTime();
        if (this.particleTimer < 0.0F) {
            float xOff = ((owner.hb_w) * (float) rng.nextGaussian())*0.25f;
            if(MathUtils.randomBoolean()) { xOff = -xOff; }
            AbstractDungeon.effectList.add(new StraightFireParticle(owner.drawX + xOff, owner.drawY + MathUtils.random(owner.hb_h/2f), 75f, 1));
            if (this.owner == p() && p().hasPower(SpontaneousHumanCombustionPower.POWER_ID)) { this.particleTimer = 1.0F; }
            if (this.amount > 0 && this.amount <= 1) { this.particleTimer = INTERVAL * 1.5F; }
            else if (this.amount > 1 && this.amount <= 3) { this.particleTimer = INTERVAL; }
            else if (this.amount > 3) { this.particleTimer = (INTERVAL / 4) * 3; }
            else { this.particleTimer = 1.0F; }
        }
    }

    @SpirePatch(clz = AbstractMonster.class, method = "render")
    public static class ChangeColorOfAffectedMonsterPls {
        @SpirePrefixPatch
        public static void patch(AbstractMonster __instance, SpriteBatch sb) {
            if (__instance.hasPower(IgnitePower.POWER_ID)) { __instance.tint.color.mul(IgnitePower.tintCol); }
        }
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        particleTimer = 0.4f;
        for(AbstractCard c: p().hand.group){
            if (c instanceof onEnemyGainIgniteSubscriber){ ((onEnemyGainIgniteSubscriber) c).onEnemyGainIgnite(); }
        }
    }

    @Override
    public void stackPower(int i) {
        super.stackPower(i);
        for(AbstractCard c: p().hand.group){
            if (c instanceof onEnemyGainIgniteSubscriber){ ((onEnemyGainIgniteSubscriber) c).onEnemyGainIgnite(); }
        }
        updateDescription();
    }

    @Override
    public AbstractPower makeCopy() {
        return new IgnitePower(owner, amount);
    }

    @Override
    public void triggerIgnite() { atb(new LoseHPAction(this.owner, this.owner, this.amount, AbstractGameAction.AttackEffect.FIRE)); }
}
