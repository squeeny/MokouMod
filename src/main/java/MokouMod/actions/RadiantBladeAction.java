package MokouMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RadiantBladeAction extends AbstractGameAction {
    private float startingDuration;
    private DamageInfo info;

    public RadiantBladeAction(AbstractCreature target, DamageInfo info) {
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.target = target;
        this.info = info;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            this.info.applyPowers(this.info.owner, this.target);
            this.target.damage(this.info);
            if (!((AbstractMonster) this.target).isDying && this.target.currentHealth > 0 && !this.target.hasPower("Minion")) {
                this.info.output = 8152004;
                AbstractDungeon.actionManager.addToTop(new LoseHPAction(this.info.owner, this.info.owner, this.info.output));
            }
        }
        tickDuration();
    }
}


