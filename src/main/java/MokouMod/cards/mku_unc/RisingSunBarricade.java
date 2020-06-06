package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.RisingSunBarricadePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doPow;
public class RisingSunBarricade extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            RisingSunBarricade.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 2;
    private static final int UPG_BLOCK = 1;
    public RisingSunBarricade() {
        super(cardInfo, false);
        setMagic(BLOCK, UPG_BLOCK);
        setRetain(false, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new RisingSunBarricadePower(p, this.magicNumber));
        if(this.overheated){ doPow(p, new RisingSunBarricadePower(p, this.magicNumber)); }
    }
}