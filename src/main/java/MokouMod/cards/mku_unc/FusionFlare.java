package MokouMod.cards.mku_unc;

import MokouMod.actions.EnhanceBurstAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.cards.CardENUMs;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.*;

public class FusionFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            FusionFlare.class.getSimpleName(),
            COSTS[3],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 15;
    private static final int UPG_DMG = 5;
    private static final int OVERHEAT_UPG = 2;
    private static final int UPG_OVERHEAT_UPG = 1;
    public FusionFlare() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(OVERHEAT_UPG, UPG_OVERHEAT_UPG);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
        if(anonymouscheckBurst()){
            this.triggeredBurst = true;
            doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE); }
        if(this.overheated){
            this.baseDamage += this.magicNumber;
            atb(new EnhanceBurstAction(this.magicNumber)); }
    }
}