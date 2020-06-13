package MokouMod.cards.mku_unc;

import MokouMod.actions.IgneousBlowAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
public class IgneousBlow extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            IgneousBlow.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 9;
    private static final int UPG_DMG = 3;
    public IgneousBlow() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new IgneousBlowAction(m, new DamageInfo(p, this.damage = this.overheated ? (int) (this.damage * 1.5F) : this.damage, this.damageTypeForTurn)));
    }
}