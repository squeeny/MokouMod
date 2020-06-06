package MokouMod.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

/*
 * Patches have a pretty detailed documentation. Go check em out here:
 *
 *  https:
 */

@SpirePatch(clz = AbstractCard.class, method = SpirePatch.CLASS)
public class BottledPlaceholderField {
    public static SpireField<Boolean> inBottledPlaceholderField = new SpireField<>(() -> false);



    @SpirePatch(clz = AbstractCard.class, method = "makeStatEquivalentCopy")
    public static class MakeStatEquivalentCopy {
        public static AbstractCard Postfix(AbstractCard result, AbstractCard self) {


            inBottledPlaceholderField.set(result, inBottledPlaceholderField.get(self));


            return result;
        }
    }

}