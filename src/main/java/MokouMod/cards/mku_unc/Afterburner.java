package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.cards.CardENUMs;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
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
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (anonymouscheckBurst()) {
            this.triggeredBurst = true;
            atb(new GainEnergyAction(ENERGY)); }
        if (this.overheated) { atb(new GainEnergyAction(ENERGY)); }
    }
}
