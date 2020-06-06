package MokouMod.powers;

import MokouMod.MokouMod;
import MokouMod.cards.mku_bas.Flare;
import com.megacrit.cardcrawl.actions.watcher.TriggerMarksAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class ResonatingBlastPower extends AbstractPower
{
    public static final String POWER_ID = MokouMod.makeID("ResonatingBlastPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean justApplied = false;

    public ResonatingBlastPower(AbstractCreature owner, int turns)
    {
        this(owner, turns, false);
    }

    public ResonatingBlastPower(AbstractCreature owner, int turns, boolean isSourceMonster)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        amount = turns;
        if (isSourceMonster) {
            justApplied = true;
        }
        type = PowerType.BUFF;
        isTurnBased = true;
        updateDescription();
        loadRegion("flameBarrier");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurnPostDraw()
    {
        addToBot(new TriggerMarksAction(new Flare()));
    }

}