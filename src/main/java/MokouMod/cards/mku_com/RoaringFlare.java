package MokouMod.cards.mku_com;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;

public class RoaringFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            RoaringFlare.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 5;
    private static final int UPG_DMG = 3;
    private static final int RESONANCE = 1;
    public RoaringFlare() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DMG);
        setMagic(RESONANCE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
        if(this.overheated) {
            doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
            atb(new AdvancePhaseAction(this.magicNumber));
        }
    }
    @Override
    public void triggerWhenDrawn() { atb(new AdvancePhaseAction(this.magicNumber)); }
}