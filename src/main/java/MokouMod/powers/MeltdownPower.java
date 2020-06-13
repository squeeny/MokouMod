package MokouMod.powers;

import MokouMod.MokouMod;
import MokouMod.interfaces.onLoseOverheatSubscriber;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Utilities.squeenyUtils.doDef;

public class MeltdownPower extends AbstractPower implements onLoseOverheatSubscriber {
    public static final String POWER_ID = MokouMod.makeID(MeltdownPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public MeltdownPower(AbstractCreature owner, int amount)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = PowerType.BUFF;
        updateDescription();
        loadRegion("flameBarrier");
    }
    @Override
    public void updateDescription() { this.description = String.format(DESCRIPTIONS[0], this.amount); }
    @Override
    public void onLoseOverheat() { doDef(this.amount); }
}