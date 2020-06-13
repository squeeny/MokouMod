package MokouMod.powers;


import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Utilities.squeenyUtils.atb;

public class MokouFlightPower extends AbstractPower {
    public static final String POWER_ID = MokouFlightPower.class.getSimpleName();
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int storedAmount;
    public MokouFlightPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.storedAmount = amount;
        updateDescription();
        loadRegion("flight");
        this.priority = 50;
    }
    public void playApplyPowerSfx() { CardCrawlGame.sound.play("POWER_FLIGHT", 0.05F); }
    public void updateDescription() {
        this.description = this.amount == 1 ? String.format(DESCRIPTIONS[0], this.amount) : String.format(DESCRIPTIONS[1], this.amount); }
    public void atStartOfTurn() {
        this.amount = this.storedAmount;
        updateDescription();
    }
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) { return calculateDamageTakenAmount(damage, type); }
    private float calculateDamageTakenAmount(float damage, DamageInfo.DamageType type) {
        if (type != DamageInfo.DamageType.HP_LOSS && type != DamageInfo.DamageType.THORNS) { return damage / 2.0F; }
        return damage;
    }
    public int onAttacked(DamageInfo info, int damageAmount) {
        Boolean willLive = Boolean.valueOf((calculateDamageTakenAmount(damageAmount, info.type) < this.owner.currentHealth));
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0 && willLive.booleanValue()) {
            flash();
            atb(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
        return damageAmount;
    }
}

