package MokouMod.actions;

import MokouMod.util.mokouUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Utilities.squeenyUtils.p;

public class FireBlastAction extends AbstractGameAction {
    private AbstractMonster targetMonster;
    private boolean upgraded;
    private ArrayList<AbstractCard> list = new ArrayList<>();
    AbstractPlayer p;

    public FireBlastAction(AbstractMonster m, boolean upgraded) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.targetMonster = m;
        this.upgraded = upgraded;
        this.p = p();
        AbstractCard tmp = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.POWER).makeCopy();
        if (upgraded) {
            tmp.upgrade();
        }
        this.list.add(tmp);
    }

    public void update() {
        if (this.list.isEmpty()) {
            this.isDone = true;
        } else {
            if (this.targetMonster != null && mokouUtils.anonymouscheckBurst()) {
                AbstractCard card = this.list.get(0);

                p().limbo.group.add(card);

                card.current_x = Settings.WIDTH / 2.0F;

                card.current_y = Settings.HEIGHT / 2.0F;

                card.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;

                card.target_y = Settings.HEIGHT / 2.0F;

                card.freeToPlayOnce = true;

                card.purgeOnUse = true;

                card.targetAngle = 0.0F;

                card.drawScale = 0.12F;

                card.applyPowers();

                AbstractDungeon.actionManager.currentAction = null;

                AbstractDungeon.actionManager.addToTop(this);

                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(card, this.targetMonster));

                if (!Settings.FAST_MODE) { AbstractDungeon.actionManager.addToTop( new WaitAction(Settings.ACTION_DUR_MED)); }
                else { AbstractDungeon.actionManager.addToTop( new WaitAction(Settings.ACTION_DUR_FASTER)); }
            }
            this.isDone = true;
        }
    }
}