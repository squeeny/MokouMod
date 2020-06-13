package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitionBlastPower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.watcher.TriggerMarksAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doPow;
public class IgnitionBlast extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            IgnitionBlast.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int POWER_AMOUNT = 1;
    private static final int IGNITE = 3;
    private static final int UPG_IGNITE = 2;

    public IgnitionBlast() {
        super(cardInfo, false);
        setMagic(POWER_AMOUNT);
        setInnate(false, true);
        setMokouMagic(IGNITE, UPG_IGNITE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new IgnitionBlastPower(p, this.magicNumber));
        if(this.overheated){ atb(new TriggerMarksAction(this)); }
    }
}
/*
public class IgnitionBlast extends abs_mku_card {

    public static final String ID = MokouMod.makeID(IgnitionBlast.class.getSimpleName());
    public static final String IMG = makeCardPath("PhoenixForm.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = MKU.Enums.COLOR_RED;
    private static final int COST = 2;
    private static final int DAMAGE = 1;
    private static final int UPG_DAMAGE = 1;

    public IgnitionBlast() {
        super(ID, null, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        mokouUtils.doPow(mokouUtils.p(), new IgnitionBlastPower(mokouUtils.p(), magicNumber));
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_DAMAGE);
            initializeDescription();
        }
    }
}

 */
