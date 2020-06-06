package MokouMod.cards.mku_com;

import MokouMod.actions.ConvertIgniteVigorAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doDmg;
public class Singe extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Singe.class.getSimpleName(),
            COSTS[0],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 4;
    private static final int UPG_DMG = 3;
     public Singe() {
         super(cardInfo, false);
         setDamage(DMG, UPG_DMG);
         setBurst(true, false);
     }
     @Override
     public void use(AbstractPlayer p, AbstractMonster m) {
         animationHandler(this);
         doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
         atb(new ConvertIgniteVigorAction());
         if(this.overheated){
             doDmg(m, this.damage, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

         }
     }
     @Override
     public boolean canUse(AbstractPlayer p, AbstractMonster m) {
         boolean canUse = super.canUse(p, m);
         if (!canUse) { return false; }
         if (this.overheated) { return true; }
         if (anonymouscheckBurst()) { canUse = true; }
         else { canUse = false; }
         return canUse;
     }
 }


