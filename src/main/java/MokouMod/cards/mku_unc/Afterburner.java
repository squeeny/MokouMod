package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;

public class Afterburner extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Afterburner.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int ENERGY = 2;

    public Afterburner() {
        super(cardInfo, false);
        setMagic(ENERGY);
        setExhaust(true);
        setBurst(true);
        setRetain(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { atb(new GainEnergyAction(ENERGY)); }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) { return false; }
        if (this.overheated) { canUse = true; }
        if (mokouUtils.anonymouscheckBurst()) { canUse = true; }
        else { canUse = false; }
        return canUse;
    }
}
