package MokouMod.cards.mku_com;

import MokouMod.actions.EruptionAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.atb;
public class FujiyamaVolcano extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            FujiyamaVolcano.class.getSimpleName(),
            COST_X,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 2;
    private static final int UPG_DMG = 1;
    private static final int IGNITE = 2;
    private static final int UPG_IGNITE = 1;
    public FujiyamaVolcano() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMultiDamage(true);
        setMagic(IGNITE, UPG_IGNITE);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        atb(new EruptionAction(p, this.multiDamage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse, this.magicNumber));
        if(this.overheated){
            atb(new EruptionAction(p, this.multiDamage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse, this.magicNumber));

        }
    }

}