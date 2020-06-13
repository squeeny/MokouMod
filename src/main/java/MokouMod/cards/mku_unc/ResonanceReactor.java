package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.ResonanceReactorPower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doPow;

public class ResonanceReactor extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ResonanceReactor.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int RESONANCE_AMOUNT = 2;
    private static final int UPG_RESONANCE_AMOUNT = 2;
    public ResonanceReactor() {
        super(cardInfo, false);
        setMagic(RESONANCE_AMOUNT, UPG_RESONANCE_AMOUNT);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new ResonanceReactorPower(p, this.magicNumber));
        if(this.overheated){ doPow(p, new ResonanceReactorPower(p, this.magicNumber)); }
    }
}