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

public class HeartOfFirePower extends AbstractPower {

    public static final String POWER_ID = MokouMod.makeID(HeartOfFirePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HeartOfFirePower(AbstractPlayer owner) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        type = AbstractPower.PowerType.BUFF;
        updateDescription();
        isTurnBased = false;
        loadRegion("flameBarrier");
    }

    @Override
    public void updateDescription() { this.description = DESCRIPTIONS[0]; }
}