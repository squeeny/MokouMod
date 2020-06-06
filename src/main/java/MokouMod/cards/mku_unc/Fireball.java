package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doAllDmg;
public class Fireball extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Fireball.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 18;
    private static final int UPG_DMG = 7;
    public Fireball() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMultiDamage(true);
        setBurst(true);
        setEthereal(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doAllDmg(this.damage, AbstractGameAction.AttackEffect.FIRE, false);
        if(this.overheated){
            doAllDmg(this.damage, AbstractGameAction.AttackEffect.FIRE, false);

        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) { return false; }
        if (mokouUtils.anonymouscheckBurst()) { canUse = true; }
        else { canUse = false; }
        return canUse;
    }
}
