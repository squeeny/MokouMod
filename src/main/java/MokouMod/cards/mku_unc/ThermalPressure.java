package MokouMod.cards.mku_unc;

import MokouMod.actions.TriggerIgniteAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.watcher.TriggerMarksAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;

public class ThermalPressure extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ThermalPressure.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int IGNITE = 4;
    private static final int UPG_IGNITE = 4;
    public ThermalPressure() {
        super(cardInfo, false);
        setMagic(IGNITE, UPG_IGNITE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(m, new IgnitePower(m, this.magicNumber));
        atb(new TriggerIgniteAction());
        if(this.overheated) {
            for (AbstractMonster mo : getAliveMonsters()) { doPow(mo, new IgnitePower(mo, this.magicNumber)); }
            doPow(p, new IgnitePower(p, this.magicNumber));
        }
    }

}

