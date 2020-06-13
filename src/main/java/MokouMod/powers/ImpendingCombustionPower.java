package MokouMod.powers;

import MokouMod.MokouMod;
import MokouMod.vfx.general.BlueInflameEffect;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

import static Utilities.squeenyUtils.*;
public class ImpendingCombustionPower extends AbstractPower {

    public static final String POWER_ID = MokouMod.makeID(ImpendingCombustionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ImpendingCombustionPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = AbstractPower.PowerType.BUFF;
        updateDescription();
        isTurnBased = false;
        loadRegion("flameBarrier");
        canGoNegative = false;
    }

    @Override
    public void updateDescription() { this.description = this.amount == 1 ? DESCRIPTIONS[0] : String.format(DESCRIPTIONS[1], this.amount); }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractPlayer p = p();
        atb(new VFXAction(new BorderLongFlashEffect(Color.TEAL, true)));
        doPow(this.owner, new SpontaneousHumanCombustionPower(this.owner, 1));
        doVfx(new BlueInflameEffect(p));
        atb(new ReducePowerAction(this.owner, this.owner, this, 1));
    }

}
