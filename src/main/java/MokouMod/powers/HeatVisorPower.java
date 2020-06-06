package MokouMod.powers;

import MokouMod.MokouMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class HeatVisorPower extends AbstractPower {
   public static final String POWER_ID = MokouMod.makeID("HeatVisorPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
     public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public HeatVisorPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.isTurnBased = false;
        loadRegion("flameBarrier");
    }
    public void onExhaust(AbstractCard card) {
        if (!(AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
            flash();
            for (int i = 0; i < this.amount; i++) {
                AbstractCard.CardRarity rarity = card.rarity;
                AbstractCard c = AbstractDungeon.getCard(rarity, AbstractDungeon.cardRandomRng).makeCopy();
                if (card.upgraded) { c.upgrade(); }
                atb(new MakeTempCardInDiscardAction(c, 1));
            }
        }
    }
    public void updateDescription() { this.description = this.amount == 1 ? String.format(DESCRIPTIONS[0], this.amount) : String.format(DESCRIPTIONS[1], this.amount); }
}
