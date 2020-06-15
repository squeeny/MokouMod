package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.*;

public class TemporalFireSeal extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            TemporalFireSeal.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 8;
    private static final int UPG_DMG = 4;
    private static final int STRENGTH = 1;
    private static final int UPG_STRENGTH = 1;
    public TemporalFireSeal() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(STRENGTH, UPG_STRENGTH);
        setExhaust(true);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
        if (mokouUtils.anonymouscheckBurst()) {
            this.triggeredBurst = true;
            doPow(m, new StrengthPower(m, -this.magicNumber)); }
        if(this.overheated){ for(AbstractMonster mo : getAliveMonsters()){ doPow(mo, new StrengthPower(mo, -this.magicNumber)); } }
    }
    @Override
    public float getTitleFontSize() { return 16; }
}