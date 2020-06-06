package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.*;

public class ScorchingColumn extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ScorchingColumn.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 7;
    private static final int UPG_DMG = 3;
    private static final int ENERGY = 1;
    public ScorchingColumn() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(ENERGY);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        int IgniteChecker = 0;
        for(AbstractMonster mo: getAliveMonsters()){
            if(mo.hasPower(IgnitePower.POWER_ID)){ ++IgniteChecker; }
        }
        if(IgniteChecker > 0){
            atb(new GainEnergyAction(this.magicNumber));
            if(this.overheated){
                doAllDmg(this.damage, AbstractGameAction.AttackEffect.FIRE, false);

            }
        }
        doDmg(m, this.damage);
    }
}

