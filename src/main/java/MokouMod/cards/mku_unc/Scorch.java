package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import MokouMod.vfx.combat.FlameBurstEffect;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class Scorch extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Scorch.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 2;
    private static final int UPG_DMG = 1;
    private static final int HIT_COUNT = 2;
    private static final int UPG_HIT_COUNT = 1;
    private static final int ENERGY = 1;
    public Scorch() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(HIT_COUNT, UPG_HIT_COUNT);
        setMokouMagic(ENERGY);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) { doDmg(m, this.damage, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL); }
        if (mokouUtils.anonymouscheckBurst()) {
            doVfx(new FlameBurstEffect(p.hb.cX, p.hb.cY, 30));
            atb(new GainEnergyAction(this.mokouSecondMagicNumber));
        }
        if(this.overheated){
            doDmg(m, this.damage, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            atb(new GainEnergyAction(this.mokouSecondMagicNumber));
        }
    }
}