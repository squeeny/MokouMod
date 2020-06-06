package MokouMod.cards.mku_rar;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doDmg;
import static Utilities.squeenyUtils.doPow;
public class GeothermalAscension extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            GeothermalAscension.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 10;
    private static final int UPG_DMG = 5;
    private static final int FLIGHT = 1;
    public GeothermalAscension() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(FLIGHT);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage);
        doPow(p, new FlightPower(p, this.magicNumber));
        if(this.overheated) {
            doPow(p, new FlightPower(p, this.magicNumber));

        }
    }
    @Override
    public float getTitleFontSize() { return 14; }
}