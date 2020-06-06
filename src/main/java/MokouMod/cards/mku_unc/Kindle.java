package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import MokouMod.vfx.general.BlueInflameEffect;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.doPow;
import static Utilities.squeenyUtils.doVfx;
public class Kindle extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Kindle.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int IGNITE = 2;
    private static final int UPG_IGNITE = 1;
    public Kindle() {
        super(cardInfo, false);
        setMagic(IGNITE, UPG_IGNITE);
        setBurst(true);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        doVfx(new BlueInflameEffect(p));
        doPow(p, new IgnitePower(p, this.magicNumber));
        if (anonymouscheckBurst()) { doPow(p,new IgnitePower(p, this.magicNumber)); }
        if (this.overheated){
            doPow(p, new IgnitePower(p, this.magicNumber));

        }
    }
}
