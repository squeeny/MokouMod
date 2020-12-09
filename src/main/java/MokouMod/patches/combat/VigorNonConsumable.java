package MokouMod.patches.combat;

import MokouMod.powers.HeartOfFirePower;
import MokouMod.powers.RetainTemperaturePower;
import MokouMod.relics.PhoenixFeather;
import MokouMod.util.mokouUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
@SpirePatch(
        clz= VigorPower.class,
        method="onUseCard",
        paramtypez={
                AbstractCard.class,
                UseCardAction.class
        }
)

public class VigorNonConsumable {

    public static SpireReturn<Void> Prefix(VigorPower __instance, AbstractCard c, UseCardAction action) {

        if (__instance.owner == p() && __instance.owner.hasPower(RetainTemperaturePower.POWER_ID)) {

            if (c.type == AbstractCard.CardType.ATTACK) {
                __instance.flash();
                if(__instance.owner.getPower(RetainTemperaturePower.POWER_ID).amount == 1){ att(new RemoveSpecificPowerAction(__instance.owner, __instance.owner, RetainTemperaturePower.POWER_ID)); }
                else { att(new ReducePowerAction(__instance.owner, __instance.owner,  RetainTemperaturePower.POWER_ID, 1)); }
            }
            return SpireReturn.Return(null);

        }
        else if (p().hasRelic(PhoenixFeather.ID) && __instance.owner == p()) {

            if (c.type == AbstractCard.CardType.ATTACK) {
                __instance.flash();
                if(__instance.amount == 1){
                    if(__instance.owner.hasPower(HeartOfFirePower.POWER_ID)){
                        doPow(__instance.owner, new StrengthPower(__instance.owner, __instance.amount));
                        doPow(__instance.owner, new LoseStrengthPower(__instance.owner, __instance.amount));
                    }
                    atb(new RemoveSpecificPowerAction(__instance.owner, __instance.owner, __instance));
                }
                else {
                    if(__instance.owner.hasPower(HeartOfFirePower.POWER_ID)){
                        doPow(__instance.owner, new StrengthPower(__instance.owner, (((__instance.amount * 10) / 4 )* 3) / 10));;
                        doPow(__instance.owner, new LoseStrengthPower(__instance.owner, (((__instance.amount * 10) / 4 )* 3) / 10));;
                    }
                    atb(new ReducePowerAction(__instance.owner, __instance.owner, __instance, (((__instance.amount * 10) / 4 )* 3) / 10));
                }
                return SpireReturn.Return(null);
            }
        }
        else if(__instance.owner.hasPower(HeartOfFirePower.POWER_ID)){
            doPow(__instance.owner, new StrengthPower(__instance.owner, __instance.amount));
            doPow(__instance.owner, new LoseStrengthPower(__instance.owner, __instance.amount));
        }
        return SpireReturn.Continue();
    }
}