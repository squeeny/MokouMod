package MokouMod.cards.mku_rar;

import MokouMod.actions.SwitchPilesAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
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
            COSTS[2],
            CardType.SKILL,
            CardTarget.SELF
    );
    public final static String ID = makeID(cardInfo.cardName);
    private static final int SCRY = 10;
    public Ventilation() {
        super(cardInfo, true);
        setMagic(SCRY);
        setBurst(true, false);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SwitchPilesAction());
        if(!upgraded) {
            doPow(p, new NoDrawPower(p));
            if(anonymouscheckBurst()) { atb(new ScryAction(this.magicNumber)); }
        } else { atb(new ScryAction(this.magicNumber)); }
    }
    @Override
    public void triggerOnGlowCheck() {
        if (anonymouscheckBurst() && !upgraded){ this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy(); }
        else{ this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy(); }
    }
}