package MokouMod.cards.mku_rar;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.ImpendingCombustionPower;
import MokouMod.powers.SpontaneousHumanCombustionPower;
import MokouMod.vfx.general.BlueInflameEffect;
import Utilities.CardInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class SpontaneousHumanCombustion extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            SpontaneousHumanCombustion.class.getSimpleName(),
            COSTS[2],
            CardType.SKILL,
            CardTarget.SELF

    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int POWER_AMOUNT = 1;
    public SpontaneousHumanCombustion() {
        super(cardInfo, false);
        setMagic(POWER_AMOUNT);
        setCostUpgrade(COSTS[1]);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new ImpendingCombustionPower(p, this.magicNumber));
        if(this.overheated){
            atb(new VFXAction(new BorderLongFlashEffect(Color.TEAL, true)));
            doPow(p, new SpontaneousHumanCombustionPower(p, this.magicNumber));
            doVfx(new BlueInflameEffect(p));
        }
    }
    @Override
    public float getTitleFontSize() { return 14; }
}