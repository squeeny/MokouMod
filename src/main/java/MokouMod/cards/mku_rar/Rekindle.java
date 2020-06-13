package MokouMod.cards.mku_rar;

import MokouMod.actions.RekindleAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
public class Rekindle extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Rekindle.class.getSimpleName(),
            COSTS[2],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int CARD_REQUIREMENT = 5;
    private static final int UPG_CARD_REQUIREMENT = -1;
    public Rekindle() {
        super(cardInfo, false);
        setMagic(CARD_REQUIREMENT, UPG_CARD_REQUIREMENT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { atb(new RekindleAction(this.upgraded, this.overheated)); }
}