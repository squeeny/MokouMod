package MokouMod.patches.general;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = SpirePatch.CLASS
)
public class ResonanceBurstPhaseValue {
    public static SpireField<Integer> resonanceburstPhase = new SpireField<>(()->0);
    public static SpireField<Integer> maxResonanceBurstPhase = new SpireField<>(()->10);
}