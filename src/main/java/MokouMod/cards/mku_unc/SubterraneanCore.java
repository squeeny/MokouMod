package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
public class SubterraneanCore extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            SubterraneanCore.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int COST = 1;
    private static final int ENERGY = 1;
    private static final int UPG_ENERGY = 1;
    public SubterraneanCore() {
        super(cardInfo, true);
        setMagic(ENERGY, UPG_ENERGY);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustAction(1, false));
        atb(new GainEnergyAction(ENERGY));
        if(this.overheated){
            atb(new ExhaustAction(1, false));
            atb(new GainEnergyAction(ENERGY));
        }
    }
}