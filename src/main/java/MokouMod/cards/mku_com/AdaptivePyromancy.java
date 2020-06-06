package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.*;

public class AdaptivePyromancy extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            AdaptivePyromancy.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY

    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 7;
    private static final int UPG_DMG = 3;
    private static final int IGNITE = 2;
    private static final int UPG_IGNITE = 1;
    public AdaptivePyromancy() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(IGNITE, UPG_IGNITE);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        doDmg(m, this.damage);
        if (mokouUtils.anonymouscheckBurst()) {
            if (isAttackIntent(m.intent)){
                doPow(p, new IgnitePower(p, this.magicNumber));
                if(this.overheated){
                    doPow(m, new IgnitePower(m, this.magicNumber));

                }
            }
            else {
                doPow(m, new IgnitePower(m, this.magicNumber));
                if(this.overheated){
                    doPow(p, new IgnitePower(p, this.magicNumber));

                }
            }
        }
    }
}