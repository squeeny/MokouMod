package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.HeartOfFirePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doPow;
public class HeartofFire extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            HeartofFire.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int HEALTH = 25;
    private static final int UPG_HEALTH = -5;
    private static final int VIGOR = 6;
    public HeartofFire() {
        super(cardInfo, false);
        setMagic(HEALTH, UPG_HEALTH);
        setMokouMagic(VIGOR);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new HeartOfFirePower(p));
        if(this.overheated) {
            int currenthealth = ((p.maxHealth - p.currentHealth) / this.magicNumber);
            if (currenthealth > 0) {
                doPow(p, new VigorPower(p, currenthealth * this.mokouSecondMagicNumber));
            }
        }
    }
}