package MokouMod.powers;

import MokouMod.MokouMod;
import MokouMod.actions.AdvancePhaseAction;
import MokouMod.interfaces.onGainResonanceSubscriber;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class ResonatingAuraPower extends AbstractPower implements onGainResonanceSubscriber
{
    public static final String POWER_ID = MokouMod.makeID("ResonatingAuraPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ResonatingAuraPower(AbstractCreature owner, int amount)
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
    public void updateDescription()
    {
        this.description =  String.format(DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void onGainResonance() { for(int i = 0; i < this.amount; ++i) { atb(new AdvancePhaseAction(1, false)); } }
}