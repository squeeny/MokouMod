package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.doDmg;
public class ResoluteFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ResoluteFlare.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 9;
    private static final int UPG_DMG = 3;
    public ResoluteFlare() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
        if (m.hasPower(IgnitePower.POWER_ID)) { doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE); }
        if (this.overheated) { doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE); }
    }
}