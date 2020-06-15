package MokouMod.cards.mku_rar;

import MokouMod.actions.SacredFireAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doDmg;
public class SacredFire extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            SacredFire.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY

    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 7;
    private static final int UPG_DMG = 3;
    public SacredFire() {
        super(cardInfo, true);
        setDamage(DMG, UPG_DMG);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
        if(anonymouscheckBurst()){
            this.triggeredBurst = true;
            atb(new SacredFireAction(m, upgraded)); }
        if(this.overheated){ atb(new SacredFireAction(m, upgraded)); }
    }
}