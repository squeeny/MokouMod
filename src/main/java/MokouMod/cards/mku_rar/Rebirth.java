package MokouMod.cards.mku_rar;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;import static MokouMod.MokouMod.makeID;
import static MokouMod.mechanics.ImmortalityManager.addExhaustion;
public class Rebirth extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Rebirth.class.getSimpleName(),
            COSTS[5],
            CardType.POWER,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int PROVIDENCE = 1;
    private static final int UPGRADE_COST = 4;
    public Rebirth() {
        super(cardInfo,false);
        setMagic(PROVIDENCE);
        setCostUpgrade(UPGRADE_COST);
        //this.exhaust = true;
        //this.purgeOnUse = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addExhaustion(this.magicNumber);
        if (this.overheated) { addExhaustion(this.magicNumber); }
    }
}