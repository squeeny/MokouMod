package MokouMod.cards.mku_unc;

import MokouMod.actions.FetchIgniteCardAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
public class Wildfire extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Wildfire.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int CARDS = 1;
    private static final int UPG_CARDS = 1;
    public Wildfire() {
        super(cardInfo, true);
        setMagic(CARDS, UPG_CARDS);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        atb(new FetchIgniteCardAction(this.magicNumber));
        if(this.overheated){
            atb(new FetchIgniteCardAction(1));

        }
    }
}