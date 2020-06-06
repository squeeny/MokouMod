package MokouMod.powers;

import MokouMod.MokouMod;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class SilencedPower extends AbstractPower implements OnReceivePowerPower
{
    public static final String POWER_ID = MokouMod.makeID("SilencedPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean justApplied = false;

    public SilencedPower(AbstractCreature owner, int turns)
    {
        this(owner, turns, false);
    }

    public SilencedPower(AbstractCreature owner, int turns, boolean isSourceMonster)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        amount = turns;
        if (isSourceMonster) {
            justApplied = true;
        }
        type = PowerType.DEBUFF;
        isTurnBased = true;
        updateDescription();
        loadRegion("flameBarrier");
    }
    @Override
    public void updateDescription() { this.description = this.amount == 1 ? String.format(DESCRIPTIONS[0], this.amount) : String.format(DESCRIPTIONS[1], this.amount); }
    @Override
    public void atEndOfRound()
    {
        if (justApplied) {
            justApplied = false;
            return;
        }
        if (amount == 0) { atb(new RemoveSpecificPowerAction(owner, owner, this));
        } else { atb(new ReducePowerAction(owner, owner, this, 1)); }
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source)
    {
        if (power.type == PowerType.BUFF && source == owner) {
            flash();
            return false;
        }
        return true;
    }
}