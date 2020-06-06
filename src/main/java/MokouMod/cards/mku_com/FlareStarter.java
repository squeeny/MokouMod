package MokouMod.cards.mku_com;

import MokouMod.actions.EnterBurstAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doDef;
public class FlareStarter extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            FlareStarter.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    public FlareStarter() {
        super(cardInfo, true);
        setBlock(BLOCK, UPG_BLOCK);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        doDef(this.block);
        atb(new EnterBurstAction());
        if(this.upgraded){ atb(new EnterBurstAction()); }
        if(this.overheated){
            atb(new EnterBurstAction());

        }
    }
}