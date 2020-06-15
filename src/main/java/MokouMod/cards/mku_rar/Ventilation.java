package MokouMod.cards.mku_rar;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.actions.SwitchPilesAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doPow;
public class Ventilation extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Ventilation.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public final static String ID = makeID(cardInfo.cardName);
    private static final int ENERGY_RESONANCE = 1;
    private static final int GAIN = 1;
    public Ventilation() {
        super(cardInfo, false);
        setMagic(ENERGY_RESONANCE);
        setMokouMagic(GAIN, GAIN);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(this.magicNumber));
        atb(new AdvancePhaseAction(this.magicNumber));
        this.upgradeMagicNumber(this.mokouSecondMagicNumber);
        if(this.overheated){ this.upgradeMagicNumber(this.mokouSecondMagicNumber); }
    }
}