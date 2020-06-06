package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doDef;
import static Utilities.squeenyUtils.doPow;
public class CrimsonArmoury extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            CrimsonArmoury.class.getSimpleName(),
            COSTS[2],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    public CrimsonArmoury() {
        super(cardInfo, false);
        setDamage(BLOCK, UPG_BLOCK);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(this.damage * 2);
        if(this.overheated) { doPow(p, new IgnitePower(p, this.damage)); }
    }
}