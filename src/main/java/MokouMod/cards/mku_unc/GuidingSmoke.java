package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doDmg;
public class GuidingSmoke extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            GuidingSmoke.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 10;
    private static final int UPG_DMG = 4;
    private static final int CARDS_FROM_DISCARD = 1;
    public GuidingSmoke() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(CARDS_FROM_DISCARD);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage);
        if(anonymouscheckBurst()) { atb(new BetterDiscardPileToHandAction(magicNumber)); }
        if(this.overheated){ atb(new ExhumeAction(false)); }
    }
}