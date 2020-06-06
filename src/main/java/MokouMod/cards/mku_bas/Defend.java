package MokouMod.cards.mku_bas;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.doDef;
public class Defend extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Defend.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    public Defend() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        doDef(this.block);
        if(this.overheated){
            doDef(this.block);
        }
    }
}
