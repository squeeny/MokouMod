package MokouMod.cards.mku_com;

import MokouMod.MokouMod;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.*;
public class FlickeringFlame extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            FlickeringFlame.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = MokouMod.makeID(cardInfo.cardName);
    private static final int DMG = 6;
    private static final int UPG_DMG = 2;
    private static final int VULNERABLE = 1;
    private static final int UPG_VULNERABLE = 1;
    private static final int DRAW = 1;
    public FlickeringFlame() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(VULNERABLE, UPG_VULNERABLE);
        setMokouMagic(DRAW);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
        doPow(m, new VulnerablePower(m, this.magicNumber, false));
        if(mokouUtils.anonymouscheckBurst()) {
            this.triggeredBurst = true;
            atb(new DrawCardAction(p(), this.mokouSecondMagicNumber)); }
        if(this.overheated){
            doPow(m, new WeakPower(m, this.magicNumber, false));
            atb(new DrawCardAction(p(), this.mokouSecondMagicNumber));
        }
    }
}