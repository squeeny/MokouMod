package MokouMod.cards.mku_com;

import MokouMod.MokouMod;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.cards.CardENUMs;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;

import static MokouMod.util.mokouUtils.animationHandler;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.*;
public class InfernalWave extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            InfernalWave.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = MokouMod.makeID(cardInfo.cardName);
    private static final int DAMAGE = 10;
    private static final int UPG_DMG = 3;
    private static final int ENERGY = 1;
    private static final int COST_DECREASE = 1;
    public InfernalWave() {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DMG);
        setMagic(ENERGY);
        setMokouMagic(COST_DECREASE);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doVfx(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F);
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.NONE);
        if (this.upgraded) { atb(new GainEnergyAction(this.magicNumber)); }
        if (anonymouscheckBurst()) { this.cost = this.cost - this.mokouSecondMagicNumber; }
        if(this.overheated){ doDef(this.damage); }
    }
}