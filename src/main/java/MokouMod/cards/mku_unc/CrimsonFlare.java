package MokouMod.cards.mku_unc;

import MokouMod.actions.DoubleIgniteAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class CrimsonFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            CrimsonFlare.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 7;
    private static final int UPG_DMG = 3;
    private static final int IGNITE = 3;
    public CrimsonFlare() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(IGNITE);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : getAliveMonsters()) {
            if (mo.hasPower(IgnitePower.POWER_ID)) {
                doPow(p, new IgnitePower(p, this.magicNumber));
                if (this.overheated) { doPow(p, new IgnitePower(p, this.magicNumber)); }
            }
        }
        doDmg(m, this.damage);
        if (this.overheated) {
            atb(new WaitAction(0.01F));
            atb(new DoubleIgniteAction(p()));
        }
    }
}