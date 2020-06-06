package MokouMod.cards.mku_rar;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.ExplosiveFlamePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class ExplosiveFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ExplosiveFlare.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    private static final int CARDS = 5;
    public ExplosiveFlare() {
        super(cardInfo, false);
        setMagic(DAMAGE, UPG_DAMAGE);
        setMokouMagic(CARDS);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p(), new ExplosiveFlamePower(p(), this.magicNumber));
        if(this.overheated){
            for(AbstractMonster mo: getAliveMonsters()){
                atb(new LoseHPAction(mo, mo, this.magicNumber));
            }
        }
    }
}
