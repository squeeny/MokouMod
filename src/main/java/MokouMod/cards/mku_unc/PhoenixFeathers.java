package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.*;
public class PhoenixFeathers extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            PhoenixFeathers.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.NONE
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 4;
    private static final int BLUR = 1;
    public PhoenixFeathers() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(this.block);
        if(anonymouscheckBurst()){ doPow(p(), new BlurPower(p(), BLUR)); }
        if(this.overheated){ doPow(p(), new BlurPower(p(), BLUR)); }
    }
}
