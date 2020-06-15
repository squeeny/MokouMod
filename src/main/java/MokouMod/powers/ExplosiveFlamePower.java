package MokouMod.powers;

import MokouMod.MokouMod;
import MokouMod.util.mokouUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.p;

public class ExplosiveFlamePower extends AbstractPower {

    public static final String POWER_ID = MokouMod.makeID(ExplosiveFlamePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int damage;

    public ExplosiveFlamePower(AbstractCreature owner, int damage) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        amount = 5;
        this.damage = damage;
        type = PowerType.BUFF;
        isTurnBased = true;

        updateDescription();
        loadRegion("flameBarrier");
        canGoNegative = false;
    }

    public void updateDescription() { this.description = this.amount == 1 ? String.format(DESCRIPTIONS[0], this.amount, this.damage) : String.format(DESCRIPTIONS[1], this.amount, this.damage); }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.damage += stackAmount;
        updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.amount--;
        if (this.amount == 0) {
            flash();
            this.amount = 5;
            atb(new DamageAllEnemiesAction(p(), DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        updateDescription();
    }

    public void atStartOfTurn() {
        this.amount = 5;
        updateDescription();
    }

}
