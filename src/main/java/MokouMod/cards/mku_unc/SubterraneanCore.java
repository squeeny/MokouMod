package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.p;

public class SubterraneanCore extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            SubterraneanCore.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int ENERGY = 1;
    private static final int UPG_ENERGY = 1;
    private static final int EXHAUST = 1;
    public SubterraneanCore() {
        super(cardInfo, true);
        setMagic(ENERGY, UPG_ENERGY);
        setMokouMagic(EXHAUST);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustAction(this.mokouSecondMagicNumber, false));
        atb(new GainEnergyAction(this.magicNumber));
        if(this.overheated){
            atb(new ExhaustAction(this.mokouBaseSecondMagicNumber, false));
            atb(new GainEnergyAction(this.magicNumber));
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) { return false; }
        if (p().hand.size() > 1) { canUse = true; }
        else {
            canUse = false;
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[1];
        }
        return canUse;
    }
}