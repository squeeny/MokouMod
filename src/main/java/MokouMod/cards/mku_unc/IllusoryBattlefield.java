package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;

public class IllusoryBattlefield extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            IllusoryBattlefield.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 7;
    private static final int UPG_DMG = 3;
    private static final int WEAK_VULN = 2;
    private static final int UPG_WEAK_VULN = 1;
    public IllusoryBattlefield() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(WEAK_VULN, UPG_WEAK_VULN);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage);
        for (AbstractMonster mo: getAliveMonsters()) {
            doPow(mo, new VulnerablePower(mo, this.magicNumber, false));
            doPow(mo, new WeakPower(mo, this.magicNumber, false));
        }
        if(this.overheated){
            for (AbstractMonster mo: getAliveMonsters()) {
                doPow(mo, new VulnerablePower(mo, this.magicNumber, false));
                doPow(mo, new WeakPower(mo, this.magicNumber, false));
            }
        }
    }

}