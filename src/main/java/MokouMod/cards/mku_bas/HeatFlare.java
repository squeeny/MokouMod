package MokouMod.cards.mku_bas;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.doDmg;
public class HeatFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            HeatFlare.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 6;
    private static final int UPG_DMG = 3;
    public HeatFlare() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        animationHandler(this);
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if(anonymouscheckBurst()){ doDmg(m, this.damage, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL); }
        if(this.overheated){
            doDmg(m, this.damage, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        }
    }
}