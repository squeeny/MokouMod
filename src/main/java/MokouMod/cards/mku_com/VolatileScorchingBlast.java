package MokouMod.cards.mku_com;

import MokouMod.actions.VolatileScorchingBlastAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.*;

public class VolatileScorchingBlast extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            VolatileScorchingBlast.class.getSimpleName(),
            COSTS[0],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 12;
    private static final int UPG_DMG = 4;
    public VolatileScorchingBlast() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        doVfx(new WeightyImpactEffect(m.hb.cX, m.hb.cY));
        atb(new VolatileScorchingBlastAction(m, new DamageInfo(p, this.baseDamage, this.damageTypeForTurn)));
        if(this.overheated){
            doVfx(new WeightyImpactEffect(m.hb.cX, m.hb.cY));
            doDmg(m, this.damage);
            doPow(p(), new NoDrawPower(p()));

        }
    }
}
