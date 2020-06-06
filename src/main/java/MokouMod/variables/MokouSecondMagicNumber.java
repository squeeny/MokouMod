package MokouMod.variables;

import MokouMod.cards.mku_abs.abs_mku_card;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class MokouSecondMagicNumber extends DynamicVariable {
    @Override
    public String key() {
        return "MKU:M2";
    }
    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof abs_mku_card) { return ((abs_mku_card) card).mokouBaseSecondMagicNumber; }
        return -1;
    }
    @Override
    public int value(AbstractCard card) {
        if (card instanceof abs_mku_card) { return ((abs_mku_card) card).mokouSecondMagicNumber; }
        return -1;
    }
    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof abs_mku_card) { return ((abs_mku_card) card).isMokouSecondMagicNumberModified; }
        return false;
    }
    @Override
    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof abs_mku_card) { ((abs_mku_card) card).isMokouSecondMagicNumberModified = true; }
    }
    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}