package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitionBarrierPower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doDef;
import static Utilities.squeenyUtils.doPow;
public class IgnitionBarrier extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            IgnitionBarrier.class.getSimpleName(),
            COSTS[2],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 12;
    private static final int UPG_BLOCK = 6;
    private static final int IGNITE = 3;
    public IgnitionBarrier() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(IGNITE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(this.block);
        doPow(p, new IgnitionBarrierPower(p, this.magicNumber));
        if(this.overheated){ doPow(p, new IgnitionBarrierPower(p, this.magicNumber)); }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            initializeDescription();
        }
    }
}