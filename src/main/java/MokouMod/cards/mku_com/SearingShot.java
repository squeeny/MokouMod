package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.*;
public class SearingShot extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            SearingShot.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 10;
    private static final int UPG_DMG = 3;
    private static final int WEAK_VULN = 1;
    private static final int UPG_WEAK_VULN = 1;
    public SearingShot() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(WEAK_VULN, UPG_WEAK_VULN);
        setMultiDamage(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doAllDmg(this.damage, AbstractGameAction.AttackEffect.SLASH_HEAVY, false);
        for(AbstractMonster mo: getAliveMonsters()){
            int currentmaxhealth = p().maxHealth;
            int currenthealth = p().currentHealth;
            if (currenthealth >= (currentmaxhealth / 2)) {
                doPow(mo, new VulnerablePower(mo, this.magicNumber, false));
                if(this.overheated){ doPow(mo, new WeakPower(mo, this.magicNumber, false)); }
            }
            else {
                doPow(mo, new WeakPower(mo, this.magicNumber, false));
                if(this.overheated){ doPow(mo, new VulnerablePower(mo, this.magicNumber, false)); }
            }
        }
    }
}