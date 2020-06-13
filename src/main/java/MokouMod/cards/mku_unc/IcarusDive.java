package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.interfaces.onOtherExhaustSubscriber;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.doVfx;

import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static Utilities.squeenyUtils.*;
public class IcarusDive extends abs_mku_card implements onOtherExhaustSubscriber {
    private final static CardInfo cardInfo = new CardInfo(
            IcarusDive.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public final static String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;
    private static final int NORMAL_UPG = 3;
    public boolean enhanceable;
    public IcarusDive() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.enhanceable = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.enhanceable = false;
        atb(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        doDmg(m, this.damage);
        if(this.overheated){
            atb(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
            doDmg(m, this.damage);
        }
    }
    @Override
    public void onOtherExhaust(AbstractCard c) {
        for (AbstractCard card : p().hand.group) {
            if (card.equals(this)) {
                if ((this).enhanceable) {
                    this.baseDamage = this.baseDamage + NORMAL_UPG;
                } else { (this).enhanceable = true; }
            }
        }
    }
}