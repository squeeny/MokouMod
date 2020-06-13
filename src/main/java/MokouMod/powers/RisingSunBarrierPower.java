package MokouMod.powers;

import MokouMod.MokouMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Utilities.squeenyUtils.*;


public class RisingSunBarrierPower extends AbstractPower {

    public static final String POWER_ID = MokouMod.makeID(RisingSunBarrierPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RisingSunBarrierPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = AbstractPower.PowerType.BUFF;
        updateDescription();
        isTurnBased = false;
        loadRegion("flameBarrier");
    }

    @Override
    public void updateDescription() {
        this.description =  String.format(DESCRIPTIONS[0], this.amount);
    }
    public void onCardDraw(AbstractCard card) { doDef(this.amount); }
    public void onExhaust(AbstractCard card){ doDef(this.amount); }
}