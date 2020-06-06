package MokouMod.cards.mku_rar;

import MokouMod.actions.ExtinguishAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CapriCore.CapriCore.makeCardPath;
import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class Extinguish extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Extinguish.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int EXHAUST = 3;
    private static final int EXHAUST_REDUCTION = -15;
    private static final int APPLY_IGNITE = 3;
    private static final int UPG_APPLY_IGNITE = 2;
    public static int DRAWPILE;

    public Extinguish() {
        super(cardInfo,false);
        setMagic(EXHAUST, EXHAUST_REDUCTION);
        setMokouMagic(APPLY_IGNITE, UPG_APPLY_IGNITE);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExtinguishAction(this.magicNumber));
    }
}