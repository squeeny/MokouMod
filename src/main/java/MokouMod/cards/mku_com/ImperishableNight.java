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
import static Utilities.squeenyUtils.doPow;
public class ImperishableNight extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ImperishableNight.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 12;
    private static final int UPG_DMG = 6;
    private static final int IGNITE = 5;
    public ImperishableNight() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(IGNITE);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
        if(!m.hasPower(IgnitePower.POWER_ID)){ doPow(m, new IgnitePower(m, this.magicNumber)); }
        else if (this.overheated){ doPow(m, new IgnitePower(m, this.magicNumber)); }
    }
}