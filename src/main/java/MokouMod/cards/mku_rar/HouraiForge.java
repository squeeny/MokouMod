package MokouMod.cards.mku_rar;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.HouraiForgePower;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;

public class HouraiForge extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            HouraiForge.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int UPG_COST = 0;
    private static final int OVERHEAT = 1;
    public HouraiForge() {
        super(cardInfo, false);
        setInnate(false, true);
        setMagic(OVERHEAT);
        setCostUpgrade(UPG_COST);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p(), new HouraiForgePower(p()));
        if(this.overheated){ atb(new ArmamentsAction(true)); }
    }
}