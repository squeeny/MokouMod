package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import MokouMod.powers.ScorchedEarthPower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doPow;
import static Utilities.squeenyUtils.getAliveMonsters;
public class ScorchedEarth extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ScorchedEarth.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int IGNITE = 2;
    private static final int UPG_IGNITE = 1;
    public ScorchedEarth() {
        super(cardInfo, false);
        setMagic(IGNITE, UPG_IGNITE);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new ScorchedEarthPower(p, this.magicNumber));
        if(this.overheated){
            for(AbstractMonster mo: getAliveMonsters()){ doPow(mo, new IgnitePower(mo, this.magicNumber)); }
        }
    }
}