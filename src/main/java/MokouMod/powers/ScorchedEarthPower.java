package MokouMod.powers;

import MokouMod.MokouMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Utilities.squeenyUtils.*;

public class ScorchedEarthPower extends AbstractPower {

    public static final String POWER_ID = MokouMod.makeID("ScorchedEarthPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ScorchedEarthPower(AbstractCreature owner, int amount) {
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
    public void atStartOfTurnPostDraw() {
        for(AbstractMonster mo : getAliveMonsters()){
            if(isAttackIntent(mo.intent)){ doPow(mo, new IgnitePower(mo, this.amount)); }
        }
    }

    @Override
    public void updateDescription() { this.description = String.format(DESCRIPTIONS[0], this.amount); }

}