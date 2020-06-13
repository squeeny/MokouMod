package MokouMod.cards.mku_unc;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;

public class WarmUp extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            WarmUp.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int RESONANCE = 5;
    private static final int UPG_RESONANCE = 5;
    public WarmUp() {
        super(cardInfo, false);
        setMagic(RESONANCE, UPG_RESONANCE);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AdvancePhaseAction(this.magicNumber));
        if(this.overheated){ atb(new AdvancePhaseAction(this.magicNumber)); }
    }
}