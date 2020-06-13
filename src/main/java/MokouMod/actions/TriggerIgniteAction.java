package MokouMod.actions;

import MokouMod.interfaces.onIgniteLoseHPSubscriber;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

import static Utilities.squeenyUtils.getAliveMonsters;

public class TriggerIgniteAction extends AbstractGameAction {

    public TriggerIgniteAction() { }

    public void update() {
        if(this.duration == startDuration) {
            for (AbstractMonster mo : getAliveMonsters()) {
                for (AbstractPower p : mo.powers) {
                    if (p instanceof onIgniteLoseHPSubscriber) { ((onIgniteLoseHPSubscriber) p).triggerIgnite(); }
                }
            }
        }
        tickDuration();
    }
}