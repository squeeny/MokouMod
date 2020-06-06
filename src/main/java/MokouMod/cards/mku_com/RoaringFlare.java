package MokouMod.cards.mku_com;

import MokouMod.actions.EnterBurstAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doDmg;
public class RoaringFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            RoaringFlare.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 5;
    private static final int UPG_DMG = 3;
    public RoaringFlare() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DMG);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
        if(this.overheated){ atb(new EnterBurstAction()); }
    }
    @Override
    public void triggerWhenDrawn() {
        atb(new EnterBurstAction());
    }
}