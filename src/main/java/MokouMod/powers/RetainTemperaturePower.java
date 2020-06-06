package MokouMod.powers;

import MokouMod.MokouMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RetainTemperaturePower extends AbstractPower {
    public static final String POWER_ID = MokouMod.makeID("RetainTemperaturePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public RetainTemperaturePower(AbstractCreature owner, int amount) {
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
    public void updateDescription() { this.description = this.amount == 1 ? DESCRIPTIONS[0] : String.format(DESCRIPTIONS[1], this.amount); }
}