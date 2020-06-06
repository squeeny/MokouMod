package MokouMod.relics;

import MokouMod.MokouMod;
import Utilities.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import Utilities.CardInfo;

import static CapriCore.CapriCore.*;
import static Utilities.squeenyUtils.*;



public class PhoenixFeather extends CustomRelic {

    /*
     * https:
     *
     * At the start of each combat, gain 1 Strength.
     * Gain 1 extra strength for each EXHAUSTION you have.
     */

    public static final String ID = MokouMod.makeID(PhoenixFeather.class.getSimpleName());
    public static int STR_COUNT = 1;
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("phoenix_feather.png", MokouMod.getModID()));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("phoenix_feather.png", MokouMod.getModID()));


    public PhoenixFeather() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }


    @Override
    public void atPreBattle() {

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
