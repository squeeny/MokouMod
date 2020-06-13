package MokouMod.patches.combat;

import MokouMod.interfaces.onGainOverheatSubscriber;
import MokouMod.interfaces.onOtherExhaustSubscriber;
import MokouMod.mechanics.ImmortalityManager;
import MokouMod.powers.RetainTemperaturePower;
import MokouMod.relics.PhoenixFeather;
import MokouMod.ui.ExhaustionPanel;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.ui.panels.ExhaustPanel;
import javassist.CtBehavior;

import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.p;

@SpirePatch(
        clz= CardGroup.class,
        method="moveToExhaustPile",
        paramtypez={
                AbstractCard.class
        }
)

public class onOtherExhaust {

    public static SpireReturn<Void> Prefix(CardGroup __instance, AbstractCard c) {
        for (AbstractCard card : p().hand.group){
            if(card instanceof onOtherExhaustSubscriber){ ((onOtherExhaustSubscriber) card).onOtherExhaust(card); }
        }
        return SpireReturn.Continue();
    }
}