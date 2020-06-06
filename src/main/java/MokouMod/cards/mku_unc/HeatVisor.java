package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.HeatVisorPower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doPow;

public class HeatVisor extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            HeatVisor.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int POWER_AMOUNT = 1;
    public HeatVisor() {
        super(cardInfo, false);
        setInnate(false, true);
        setMagic(POWER_AMOUNT);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new HeatVisorPower(p, this.magicNumber));
        if(this.overheated){
            doPow(p, new HeatVisorPower(p, this.magicNumber));

        }
    }
}