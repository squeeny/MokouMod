package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.combat.BurstMechanics;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
import static com.megacrit.cardcrawl.relics.CharonsAshes.DMG;

public class Combust extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Combust.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 7;
    private static final int UPG_DMG = 3;
    public Combust() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(IgnitePower.POWER_ID)) { doVfx(new WeightyImpactEffect(m.hb.cX, m.hb.cY)); }
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.NONE);
    }
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if(mo.hasPower(IgnitePower.POWER_ID)){
            this.damage = (int) (this.damage * 1.5F);
            if(this.overheated){ this.damage = (int) (this.damage * 1.5F); }
            isDamageModified = true;
        }
    }
}
