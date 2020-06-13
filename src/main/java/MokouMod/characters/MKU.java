package MokouMod.characters;

import MokouMod.MokouMod;
import MokouMod.MokouPlayerListener;
import MokouMod.cards.mku_bas.Defend;
import MokouMod.cards.mku_bas.Strike;
import MokouMod.cards.mku_bas.HeatSpark;
import MokouMod.relics.PhoenixFeather;
import MokouMod.ui.MokouEnergyOrb;
import Utilities.BetterSpriterAnimation;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.brashmonkey.spriter.Player;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.city.Snecko;
import com.megacrit.cardcrawl.relics.SneckoEye;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static MokouMod.MokouMod.*;
import static MokouMod.characters.MKU.Enums.COLOR_RED;

public class MKU extends CustomPlayer {

    public static final Logger logger = LogManager.getLogger(MokouMod.class.getName());

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass MKU;
        @SpireEnum(name = "MKU_RED_COLOR")
        public static AbstractCard.CardColor COLOR_RED;
        @SpireEnum(name = "MKU_RED_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;
    private static final String ID = makeID("mokou");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    public static final String[] orbTextures = {
            "CapriCoreResources/images/MokouMod/char/mokou/orb/layer1.png",
            "CapriCoreResources/images/MokouMod/char/mokou/orb/layer2.png",
            "CapriCoreResources/images/MokouMod/char/mokou/orb/layer3.png",
            "CapriCoreResources/images/MokouMod/char/mokou/orb/layer4.png",
            "CapriCoreResources/images/MokouMod/char/mokou/orb/layer5.png",
            "CapriCoreResources/images/MokouMod/char/mokou/orb/layer6.png",
            "CapriCoreResources/images/MokouMod/char/mokou/orb/layer1d.png",
            "CapriCoreResources/images/MokouMod/char/mokou/orb/layer2d.png",
            "CapriCoreResources/images/MokouMod/char/mokou/orb/layer3d.png",
            "CapriCoreResources/images/MokouMod/char/mokou/orb/layer4d.png",
            "CapriCoreResources/images/MokouMod/char/mokou/orb/layer5d.png",};

    public MKU(String name, PlayerClass setClass) {

        super(name, setClass, new MokouEnergyOrb(), new BetterSpriterAnimation(
                        "CapriCoreResources/images/MokouMod/char/mokou/Spriter/mokouanim.scml"));

        Player.PlayerListener listener = new MokouPlayerListener(this);
        ((BetterSpriterAnimation)this.animation).myPlayer.addListener((listener));

        initializeClass(null,
                MKU_SHOULDER_2,
                MKU_SHOULDER_1,
                MKU_CORPSE,
                getLoadout(), 20.0F, -60.0F, 180.0F, 300F, new EnergyManager(ENERGY_PER_TURN));

        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);

    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        logger.info("Begin loading starter Deck Strings");
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(HeatSpark.ID);
        return retVal;
    }


    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(PhoenixFeather.ID);
        UnlockTracker.markRelicAsSeen(PhoenixFeather.ID);
        UnlockTracker.markRelicAsSeen(SneckoEye.ID);
        return retVal;
    }


    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_FIRE", 0.75f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_RED;
    }

    @Override
    public Color getCardTrailColor() {
        return MokouMod.mokou_red;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new HeatSpark();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new MKU(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return MokouMod.mokou_red;
    }

    @Override
    public Color getSlashAttackColor() {
        return MokouMod.mokou_red;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL,};
    }

    @Override
    public String getSpireHeartText() { return TEXT[1]; }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public void damage(DamageInfo info) {
        int thisHealth = this.currentHealth;
        super.damage(info);
        int trueDamage = thisHealth - this.currentHealth;
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && trueDamage > 0) {
            if (this.isDead) { this.playDeathAnimation(); }
            else {
                // hit
            }
        }
    }

    public void playDeathAnimation() {
        runAnimation("Win");
    }

    public void runAnim(String animation) {
        ((BetterSpriterAnimation)this.animation).myPlayer.setAnimation(animation);
    }

    public void resetAnimation() {
        ((BetterSpriterAnimation)this.animation).myPlayer.setAnimation("Idle");
    }

    public void stopAnimation() {
        int time = ((BetterSpriterAnimation)this.animation).myPlayer.getAnimation().length;
        ((BetterSpriterAnimation)this.animation).myPlayer.setTime(time);
    }
}
