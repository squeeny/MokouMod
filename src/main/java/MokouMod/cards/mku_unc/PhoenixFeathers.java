package MokouMod.cards.mku_unc;

import MokouMod.actions.PhoenixFeathersAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class PhoenixFeathers extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            PhoenixFeathers.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.NONE
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int EXHAUST_GRAB = 2;
    public PhoenixFeathers() {
        super(cardInfo, true);
        setMagic(EXHAUST_GRAB);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PhoenixFeathersAction(this.magicNumber));
        if(this.overheated){ atb(new BetterDiscardPileToHandAction(magicNumber)); }
    }
    @Override
    public void triggerOnExhaust() { atb(new ExhumeAction(this.upgraded)); }
}
