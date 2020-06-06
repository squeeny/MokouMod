package MokouMod.cardmods;


import MokouMod.MokouMod;
import MokouMod.actions.LoseOverheatAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.OverheatPower;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;

public class OverheatText extends AbstractCardModifier {
    private String desc;

    public OverheatText(String desc) {
        this.desc = desc;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) { return rawDescription + this.desc; }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) { return card instanceof abs_mku_card && (p().hasPower(OverheatPower.POWER_ID) && p().getPower(OverheatPower.POWER_ID).amount == 1); }
    public void onOtherCardPlayed(AbstractCard card, AbstractCard otherCard, CardGroup group) {
        if(removeOnCardPlayed(card)){
            System.out.println("ya");
            atb(new LoseOverheatAction());
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new OverheatText(desc);
    }

    public String identifier(AbstractCard card) {
        return MokouMod.makeID("Overheat");
    }

}