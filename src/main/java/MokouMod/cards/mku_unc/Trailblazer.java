package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.TrailblazerPower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doPow;
public class Trailblazer extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Trailblazer.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    public static final int AMOUNT = 1;
    public Trailblazer() {
        super(cardInfo, false);
        setMagic(AMOUNT);
        setRetain(false, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new TrailblazerPower(p, this.magicNumber));
        if (this.overheated) {
            doPow(p, new TrailblazerPower(p, this.magicNumber));

        }
    }
}
