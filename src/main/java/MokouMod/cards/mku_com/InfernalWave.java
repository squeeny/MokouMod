package MokouMod.cards.mku_com;

import MokouMod.MokouMod;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;

import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.*;
public class InfernalWave extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            InfernalWave.class.getSimpleName(),
            COSTS[0],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = MokouMod.makeID(cardInfo.cardName);
    private static final int DAMAGE = 3;
    private static final int UPG_DMG = 2;
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 2;
    public InfernalWave() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DMG);
        setBlock(BLOCK, UPG_BLOCK);
        damage = baseDamage = DAMAGE;
        block = baseBlock = BLOCK;
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        doVfx(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F);
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.NONE);
        doDef(this.block);
        if(this.overheated){
            doDmg(m, this.damage, AbstractGameAction.AttackEffect.NONE);
            doDef(this.block);

        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) { return false; }
        if (mokouUtils.anonymouscheckBurst()) { canUse = true; }
        else { canUse = false; }
        return canUse;
    }
}