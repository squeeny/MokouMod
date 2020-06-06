package MokouMod.cards.mku_rar;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.BlueFlareImpending;
import MokouMod.powers.BlueFlarePower;
import MokouMod.vfx.general.BlueInflameEffect;
import Utilities.CardInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class BlueFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            BlueFlare.class.getSimpleName(),
            COSTS[2],
            CardType.SKILL,
            CardTarget.SELF

    );
    public static final String ID = makeID(cardInfo.cardName);
    public BlueFlare() {
        super(cardInfo, false);
        setCostUpgrade(COSTS[1]);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new BlueFlareImpending(p, 1  ));
        if(this.overheated){
            atb(new VFXAction(new BorderLongFlashEffect(Color.TEAL, true)));
            doPow(p, new BlueFlarePower(p, 1));
            doVfx(new BlueInflameEffect(p));
        }
    }
}