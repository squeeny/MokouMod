package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doDef;

public class Burn extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Burn.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int EXHAUST = 1;
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 4;
    public Burn() {
        super(cardInfo, false);
        setMagic(EXHAUST);
        setBlock(BLOCK, UPG_BLOCK);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(this.block);
        if(anonymouscheckBurst()) {
            if (this.upgraded) { atb(new ExhaustAction(1, false));
            } else { atb(new ExhaustAction(1, true, false, false)); }
        }
        if(this.overheated){
            doDef(this.block);
            atb(new ExhaustAction(1, false));
        }
    }

}