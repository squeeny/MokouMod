package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.*;
public class IgnitionFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            IgnitionFlare.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 4;
    public IgnitionFlare() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        for(AbstractMonster mo: getAliveMonsters()){
            if(mo.hasPower(IgnitePower.POWER_ID)){ doDef(this.block); }
        }
        if(this.overheated){
            for(AbstractMonster mo: getAliveMonsters()){
                if(mo.hasPower(IgnitePower.POWER_ID)){ atb(new GainEnergyAction(1)); }
            }

        }
    }
}
