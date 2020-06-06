package MokouMod.cards.mku_rar;

import MokouMod.actions.ProfanedFlameAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class ProfanedFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ProfanedFlare.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    public ProfanedFlare() {
        super(cardInfo, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ProfanedFlameAction(this.damage, this.upgraded, this.overheated));
    }
}
