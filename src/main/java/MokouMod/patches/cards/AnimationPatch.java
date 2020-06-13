package MokouMod.patches.cards;

import MokouMod.characters.MKU;
import MokouMod.interfaces.onOtherExhaustSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.p;

@SpirePatch(
        clz= AbstractPlayer.class,
        method="useCard",
        paramtypez={
                AbstractCard.class,
                AbstractMonster.class,
                int.class
        }
)

public class AnimationPatch {

    public static SpireReturn<Void> Prefix(AbstractPlayer __instance, AbstractCard c, AbstractMonster m, int energyOnUse) {
        if(__instance instanceof MKU){ animationHandler(c); }
        return SpireReturn.Continue();
    }
}