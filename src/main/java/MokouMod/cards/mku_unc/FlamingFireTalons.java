package MokouMod.cards.mku_unc;

import MokouMod.actions.DoubleIgniteAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doDmg;
public class FlamingFireTalons extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            FlamingFireTalons.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 7;
    private static final int UPG_DMG = 5;
    public FlamingFireTalons() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new DoubleIgniteAction(p));
        if(this.overheated){ doDmg(m, this.damage, AbstractGameAction.AttackEffect.BLUNT_HEAVY); }
    }
}