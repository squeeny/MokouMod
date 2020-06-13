package MokouMod.powers;

import MokouMod.MokouMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PhoenixFormPower extends AbstractPower
{
    public static final String POWER_ID = MokouMod.makeID(PhoenixFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public PhoenixFormPower(AbstractCreature owner)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        amount = -1;
        type = PowerType.BUFF;
        updateDescription();
        loadRegion("flameBarrier");
    }
    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}