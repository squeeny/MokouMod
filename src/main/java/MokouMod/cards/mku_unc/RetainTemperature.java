package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.RetainTemperaturePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doPow;
public class RetainTemperature extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            RetainTemperature.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int ATTACKS = 1;
    private static final int UPG_ATTACKS = 1;
    private static final int VIGOR = 3;
    public RetainTemperature() {
        super(cardInfo,true);
        setMagic(ATTACKS, UPG_ATTACKS);
        setMokouMagic(VIGOR);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new VigorPower(p, this.mokouSecondMagicNumber));
        doPow(p, new RetainTemperaturePower(p, this.magicNumber));
        if(this.overheated){
            doPow(p, new VigorPower(p, this.mokouSecondMagicNumber));
            doPow(p, new RetainTemperaturePower(p, this.magicNumber));
        }
    }
}
