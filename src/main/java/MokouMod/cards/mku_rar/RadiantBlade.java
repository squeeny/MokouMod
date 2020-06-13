package MokouMod.cards.mku_rar;

import MokouMod.actions.GainIgniteForRemovedBlockAction;
import MokouMod.actions.RadiantBladeAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
public class RadiantBlade extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            RadiantBlade.class.getSimpleName(),
            COSTS[0],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 25;
    private static final int UPG_DMG = 5;
    public RadiantBlade() {
        super(cardInfo,false);
        setDamage(DMG, UPG_DMG);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(this.overheated ? new GainIgniteForRemovedBlockAction(m, p) : new RemoveAllBlockAction(m, p));
        atb(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        atb(new RadiantBladeAction(m, new DamageInfo(p, this.baseDamage, this.damageTypeForTurn)));
    }
}
