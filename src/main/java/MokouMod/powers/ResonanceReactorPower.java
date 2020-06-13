package MokouMod.powers;

import MokouMod.MokouMod;
import MokouMod.actions.AdvancePhaseAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Utilities.squeenyUtils.*;
public class ResonanceReactorPower extends AbstractPower {
   public static final String POWER_ID = MokouMod.makeID(ResonanceReactorPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
     public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public ResonanceReactorPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.isTurnBased = false;
        loadRegion("flameBarrier");
        canGoNegative = false;
    }
    public void updateDescription() { this.description = String.format(DESCRIPTIONS[0], this.amount); }
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){ atb(new AdvancePhaseAction(this.amount)); }
    }
}
