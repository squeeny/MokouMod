package MokouMod.cards.mku_com;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
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
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 4;
    private static final int RESONANCE = 1;
    public FlareStarter() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(RESONANCE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(this.block);
        atb(new AdvancePhaseAction());
        if(this.overheated){
            doDef(this.block);
            atb(new AdvancePhaseAction());
        }
    }
}