package MokouMod.actions;

import MokouMod.powers.IgnitePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static Utilities.squeenyUtils.getAliveMonsters;
import static Utilities.squeenyUtils.getPowerAmount;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class ExtinguishAction extends AbstractGameAction {

    public int ignite;

    private float startingDuration;

    private AbstractPlayer p;

    public ExtinguishAction(int ignite) {
        this.ignite = ignite;
        this.p = p;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.startingDuration = this.duration;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
    }

    public void update() {
        if (this.duration == startDuration) {

            for(AbstractMonster mo: getAliveMonsters()){
                if(mo.hasPower(IgnitePower.POWER_ID) && getPowerAmount(mo, IgnitePower.POWER_ID) >= this.ignite){
                    doVfx(new WeightyImpactEffect(mo.hb.cX, mo.hb.cY), true);
                    att(new InstantKillAction(mo));
                }
            }
            tickDuration();
        }
        else { this.isDone = true; }
    }
}
