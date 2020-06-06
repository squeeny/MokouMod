package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doDmg;
import static Utilities.squeenyUtils.doPow;
public class SoaringPhoenixHourai extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            SoaringPhoenixHourai.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 5;
    private static final int UPG_DMG = 3;
    private static final int FLIGHT = 1;
    public SoaringPhoenixHourai() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(FLIGHT);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage);
        int currentmaxhealth = AbstractDungeon.player.maxHealth;
        int currenthealth = AbstractDungeon.player.currentHealth;
        if (currenthealth <= (currentmaxhealth) / 2) { doPow(p, new FlightPower(p, this.magicNumber)); }
        if (currenthealth >= (currentmaxhealth) / 2) {
            if (this.overheated) { doPow(p, new FlightPower(p, this.magicNumber)); }
        }
        if(this.overheated){  }
    }
}