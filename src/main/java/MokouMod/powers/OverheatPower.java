package MokouMod.powers;

import MokouMod.MokouMod;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.interfaces.onIgniteLoseHPSubscriber;
import MokouMod.interfaces.onLoseOverheatSubscriber;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class OverheatPower extends AbstractPower {

    public static final String POWER_ID = MokouMod.makeID(OverheatPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public OverheatPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = AbstractPower.PowerType.BUFF;
        updateDescription();
        isTurnBased = true;
        loadRegion("flameBarrier");
    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof abs_mku_card) {
            if (this.amount == 1) {
                atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                for (AbstractPower p: p().powers){ if (p instanceof onLoseOverheatSubscriber){ ((onLoseOverheatSubscriber) p).onLoseOverheat(); } }
            } else { atb(new ReducePowerAction(this.owner, this.owner, this, 1)); }
        }
    }
    @Override
    public void updateDescription() {
        if (this.amount == 1) { this.description = DESCRIPTIONS[0];
        } else { this.description =  String.format(DESCRIPTIONS[1], this.amount); }
    }
}