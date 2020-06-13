package MokouMod.cards.mku_bas;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doDmg;
public class Strike extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Strike.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 6;
    private static final int UPG_DMG = 3;
    private static final int ENERGY = 2;
    public Strike() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(ENERGY);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if(this.overheated){ atb(new GainEnergyAction(this.magicNumber)); }
    }
}