package MokouMod.cards.mku_bas;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.*;

public class HeatSpark extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            HeatSpark.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 6;
    private static final int UPG_DMG = 3;
    private static final int IGNITE = 3;
    private static final int UPG_IGNITE = 2;
    private static final int ENERGY = 1;
    public HeatSpark() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(IGNITE, UPG_IGNITE);
        setMokouMagic(ENERGY);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if(anonymouscheckBurst()){
            this.triggeredBurst = true;
            doPow(m, new IgnitePower(m, this.magicNumber)); }
        if(this.overheated){
            doPow(m, new IgnitePower(m, this.magicNumber));
            atb(new GainEnergyAction(this.magicNumber));
        }
    }
}