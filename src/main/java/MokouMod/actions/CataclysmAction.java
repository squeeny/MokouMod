package MokouMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;

public class CataclysmAction extends AbstractGameAction {
    private DamageInfo info;
    private float startingDuration;
    private boolean overheat;
    public CataclysmAction(AbstractCreature target, DamageInfo info, boolean overheat) {

        this.info = info;
        setValues(target, info);

        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.attackEffect = AbstractGameAction.AttackEffect.FIRE;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.overheat = overheat;

    }

    public void update() {

        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for(AbstractCard card : p().discardPile.group){ tmp.addToRandomSpot(card); }
        if (this.duration == this.startingDuration) {
            int count = tmp.size();
            if (count > 0) {
                int i;
                for (i = 0; i < count; i++) {
                    att(new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.FIRE));
                    if(this.overheat){ att(new GainBlockAction(p(), 3)); }
                    AbstractCard card = tmp.getNCardFromTop(i);
                    p().discardPile.moveToExhaustPile(card);
                    p().discardPile.applyPowers();
                    CardCrawlGame.dungeon.checkForPactAchievement();
                }
            }
            tickDuration();
        }
        else{ this.isDone = true; }
    }
}