package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.cards.CardENUMs;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
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
    private static final int UPG_DMG = 6;
    public Fireball() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMultiDamage(true);
        setBurst(true);
        setEthereal(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(anonymouscheckBurst() || this.overheated){
            this.triggeredBurst = true;
            doAllDmg(this.damage, AbstractGameAction.AttackEffect.FIRE, false); }
    }
    @Override
    public void triggerOnGlowCheck() {
        if ((this.hasTag(CardENUMs.BURST) && anonymouscheckBurst()) || this.overheated) { glowColor = GOLD_BORDER_GLOW_COLOR;
        } else { glowColor = BLUE_BORDER_GLOW_COLOR; }
    }
}
