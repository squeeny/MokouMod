package MokouMod.cards.mku_rar;

import MokouMod.actions.ForestOfTheLostAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
public class EnchantingBambooForest extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            EnchantingBambooForest.class.getSimpleName(),
            COSTS[3],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int COST_DECREASE = 1;
    public EnchantingBambooForest() {
        super(cardInfo, false);
        setCostUpgrade(COSTS[2]);
        setMagic(COST_DECREASE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ForestOfTheLostAction(this.magicNumber));
        if(this.overheated){ atb(new ForestOfTheLostAction(this.magicNumber)); }
    }
}