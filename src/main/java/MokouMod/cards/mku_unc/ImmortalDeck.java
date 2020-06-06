package MokouMod.cards.mku_unc;


import MokouMod.actions.DiscardPileToHandAction;
import MokouMod.actions.ExhaustPileToDiscardAction;
import MokouMod.actions.SwitchPilesAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
public class ImmortalDeck extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ImmortalDeck.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int ATTACKS = 1;
    private static final int UPG_ATTACKS = 1;
    public ImmortalDeck() {
        super(cardInfo, true);
        setMagic(ATTACKS, UPG_ATTACKS);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustPileToDiscardAction());
        atb(new DiscardPileToHandAction(this.magicNumber, true, true, AbstractCard.CardType.ATTACK));
        if(this.overheated){
            atb(new SwitchPilesAction());

        }
    }
}