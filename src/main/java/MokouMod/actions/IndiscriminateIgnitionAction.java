package MokouMod.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;

public class IndiscriminateIgnitionAction extends AbstractGameAction {
    private DamageInfo info;
    private int numTimes;

    public IndiscriminateIgnitionAction(AbstractCreature target, DamageInfo info, int numTimes) {

        this.info = info;
        this.target = target;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;

        if (Settings.FAST_MODE) {
            this.startDuration = 0.05F;

        } else {
            this.startDuration = 0.2F;

        }

        this.attackEffect = AbstractGameAction.AttackEffect.FIRE;

        this.duration = this.startDuration;

        this.numTimes = numTimes;

    }


    public void update() {

        if (this.target == null) {

            this.isDone = true;

        } else if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {

            AbstractDungeon.actionManager.clearPostCombatActions();

            this.isDone = true;

        } else {

            this.duration -= Gdx.graphics.getDeltaTime();

            if (this.duration < 0.0F) {

                if (this.target.currentHealth > 0) {

                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect, false));

                    this.target.tint.color = Color.RED.cpy();

                    this.target.tint.changeColor(Color.WHITE.cpy());

                    this.info.applyPowers(this.info.owner, this.target);

                    this.target.damage(this.info);

                    if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {

                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p(), p(), new StrengthPower(p(), 3), 3));

                    }
                    if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {

                        this.numTimes--;

                        AbstractDungeon.actionManager.addToTop(new IndiscriminateIgnitionAction(AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng), this.info, this.numTimes));

                    }


                    if (Settings.FAST_MODE) {

                        AbstractDungeon.actionManager.addToTop(new WaitAction(0.05F));

                    } else {

                        AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));

                    }

                }
                this.isDone = true;

            }

        }

    }
}


