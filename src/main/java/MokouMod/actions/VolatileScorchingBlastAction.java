package MokouMod.actions;


import MokouMod.powers.SilencedPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;

public class VolatileScorchingBlastAction extends AbstractGameAction {
    private float startingDuration;
    private DamageInfo info;
    public VolatileScorchingBlastAction(AbstractCreature target, DamageInfo info) {
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.target = target;
        this.info = info;
    }
    public void update() {
        if (this.duration == this.startingDuration) {
            this.info.applyPowers(this.info.owner, this.target);
            this.target.damage(this.info);
            if (!((AbstractMonster) this.target).isDying && this.target.currentHealth > 0 && !this.target.hasPower("Minion")) { doPow(p(), new SilencedPower(p(), 3)); }
        }
        tickDuration();
    }
}