package MokouMod.actions;


import MokouMod.patches.combat.ResonanceMechanics;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Utilities.squeenyUtils.*;

public class GeothermalAction extends AbstractGameAction {
    private float startingDuration;
    private DamageInfo info;
    public GeothermalAction(AbstractCreature target, DamageInfo info) {
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.target = target;
        this.info = info;
    }
    public void update() {
        if (this.duration == this.startingDuration) {
            this.info.applyPowers(this.info.owner, this.target);
            this.target.damage(this.info);
            if (!((AbstractMonster) this.target).isDying && this.target.currentHealth > 0 && !this.target.hasPower("Minion")) { }
            else {
                ResonanceMechanics.geothermalResonance.set(p(), true);
                ResonanceMechanics.geothermalResonanceAuth.set(p(), true);
            }
        }
        tickDuration();
    }
}