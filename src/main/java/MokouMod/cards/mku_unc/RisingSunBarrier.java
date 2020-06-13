package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.RisingSunBarrierPower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doPow;
public class RisingSunBarrier extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            RisingSunBarrier.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 1;
    private static final int UPG_BLOCK = 1;
    private static final int DRAW = 3;
    private static final int UPG_DRAW = 1;
    public RisingSunBarrier() {
        super(cardInfo, false);
        setMagic(BLOCK, UPG_BLOCK);
        setMokouMagic(DRAW, UPG_DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new RisingSunBarrierPower(p, this.magicNumber));
        if(this.overheated){ atb(new DrawCardAction(p, this.mokouSecondMagicNumber)); }
    }
}