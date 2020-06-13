package MokouMod.actions;

import MokouMod.powers.IgnitePower;
import MokouMod.util.mokouUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import static Utilities.squeenyUtils.atb;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class EruptionAction extends AbstractGameAction {

    public int ignite;
    private boolean freeToPlayOnce = false;
    private int energyOnUse = -1;
    private DamageInfo.DamageType damageType;
    private AbstractPlayer p;
    public int[] multiDamage;

    public EruptionAction(AbstractPlayer p, int[] multiDamage, DamageInfo.DamageType damageType, boolean freeToPlayOnce, int energyOnUse, int ignite) {
        this.multiDamage = multiDamage;
        this.damageType = damageType;
        this.ignite = ignite;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) { effect = this.energyOnUse; }
        if (this.p.hasRelic("Chemical X")) {
            effect += ChemicalX.BOOST;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                if (i == 0) {
                    atb(new SFXAction("ATTACK_WHIRLWIND"));
                    doVfx(new WhirlwindEffect(), 0.0F);
                }
                atb(new SFXAction("ATTACK_HEAVY"));
                doVfx(new CleaveEffect());
                atb(new DamageAllEnemiesAction(this.p, this.multiDamage, this.damageType, AbstractGameAction.AttackEffect.NONE, true));
                atb(new ApplyPowerAction(p, p, new IgnitePower(p, ignite), ignite, true, AbstractGameAction.AttackEffect.NONE));
            }
            if (!this.freeToPlayOnce) { this.p.energy.use(EnergyPanel.totalCount); }
        }
        this.isDone = true;
    }
}
