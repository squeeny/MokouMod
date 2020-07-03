package MokouMod;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.actions.MessageCaller;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.characters.MKU;
import MokouMod.mechanics.ImmortalityManager;
import MokouMod.patches.cards.CardENUMs;
import MokouMod.patches.combat.BurstMechanics;
import MokouMod.patches.combat.ResonanceMechanics;
import MokouMod.powers.OverheatPower;
import MokouMod.relics.PhoenixFeather;
import MokouMod.ui.ResonanceBurst;
import MokouMod.util.IDCheckDontTouchPls;
import MokouMod.variables.DoubleDamage;
import MokouMod.variables.MokouSecondMagicNumber;
import Utilities.TextureLoader;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.abstracts.CustomSavable;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.p;

@SpireInitializer
public class MokouMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        OnStartBattleSubscriber,
        PreRoomRenderSubscriber,
        PostBattleSubscriber,
        PreStartGameSubscriber{
    public static final Logger logger = LogManager.getLogger(MokouMod.class.getName());
    private static String modID;
    public static boolean[] activeTutorials = new boolean[]{true};
    public static boolean tackybypass = false;
    public static ResonanceBurst resonanceBurst;
    public static boolean drawResonanceBurstUI = false;
    public static Properties MokouModDefaultSettings = new Properties();

    private static final String MODNAME = "MokouMod";
    private static final String AUTHOR = "squeeny";
    private static final String DESCRIPTION = "Fujiwara no Mokou joins Slay The Spire!";
    public static final Color mokou_red = CardHelper.getColor(60, 36, 36);
    private static final String ATTACK_mokou_red = "CapriCoreResources/images/MokouMod/512/bg_attack_mokou_red.png";
    private static final String SKILL_mokou_red = "CapriCoreResources/images/MokouMod/512/bg_skill_mokou_red.png";
    private static final String POWER_mokou_red = "CapriCoreResources/images/MokouMod/512/bg_power_mokou_red.png";
    private static final String ENERGY_ORB_mokou_red = "CapriCoreResources/images/MokouMod/512/card_mokou_red_orb.png";
    private static final String CARD_ENERGY_ORB = "CapriCoreResources/images/MokouMod/512/card_small_orb.png";
    private static final String ATTACK_mokou_red_PORTRAIT = "CapriCoreResources/images/MokouMod/1024/bg_attack_mokou_red.png";
    private static final String SKILL_mokou_red_PORTRAIT = "CapriCoreResources/images/MokouMod/1024/bg_skill_mokou_red.png";
    private static final String POWER_mokou_red_PORTRAIT = "CapriCoreResources/images/MokouMod/1024/bg_power_mokou_red.png";
    private static final String ENERGY_ORB_mokou_red_PORTRAIT = "CapriCoreResources/images/MokouMod/1024/card_mokou_red_orb.png";
    private static final String MKU_BUTTON = "CapriCoreResources/images/MokouMod/charSelect/mokouButton.png";
    private static final String MKU_PORTRAIT = "CapriCoreResources/images/MokouMod/charSelect/mokouPortraitBG.png";
    public static final String MKU_SHOULDER_1 = "CapriCoreResources/images/MokouMod/char/mokou/shoulder.png";
    public static final String MKU_SHOULDER_2 = "CapriCoreResources/images/MokouMod/char/mokou/shoulder2.png";
    public static final String MKU_CORPSE = "CapriCoreResources/images/MokouMod/char/mokou/corpse.png";
    public static final String BADGE_IMAGE = "CapriCoreResources/images/MokouMod/Badge.png";

    public MokouMod() {
        logger.info("Subscribe to BaseMod hooks");
        BaseMod.subscribe(this);
        setModID("MokouMod");
        logger.info("Done subscribing");
        logger.info("Creating the color " + MKU.Enums.COLOR_RED.toString());
        BaseMod.addColor(MKU.Enums.COLOR_RED, mokou_red, mokou_red, mokou_red,
                mokou_red, mokou_red, mokou_red, mokou_red,
                ATTACK_mokou_red, SKILL_mokou_red, POWER_mokou_red, ENERGY_ORB_mokou_red,
                ATTACK_mokou_red_PORTRAIT, SKILL_mokou_red_PORTRAIT, POWER_mokou_red_PORTRAIT,
                ENERGY_ORB_mokou_red_PORTRAIT, CARD_ENERGY_ORB);
        logger.info("Done creating the color");
        logger.info("Adding mod settings");
        logger.info("Done adding mod settings");
    }
    public static void setModID(String ID) {
        Gson coolG = new Gson();
        InputStream in = MokouMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { modID = EXCEPTION_STRINGS.DEFAULTID;
        } else { modID = ID; }
        logger.info("Success! ID is " + modID);
    }
    public static String getModID() {
        return modID;
    }
    private static void pathCheck() {
        Gson coolG = new Gson();
        InputStream in = MokouMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        /* No need, Mokou runs CapriCore as a dependency, which has the resources.
        String packageName = MokouMod.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) {
            //if (!packageName.equals(getModID())) { throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); }
            //if (!resourcePathExists.exists()) { throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); }
        }
         */
    }
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        MokouMod mokoumod = new MokouMod();
        logger.info("========================= /Hello World./ =========================");
        try {
            for (int i = 0; i < activeTutorials.length; i++) { MokouModDefaultSettings.setProperty("activeTutorials" + i, "true"); }
            SpireConfig config = new SpireConfig("mokouMod", "MokouModConfig", MokouModDefaultSettings);
            for (int j = 0; j < activeTutorials.length; j++) { activeTutorials[j] = config.getBool("activeTutorials" + j); }
        } catch (IOException e) { e.printStackTrace(); }
    }
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + MKU.Enums.MKU.toString());
        BaseMod.addCharacter(new MKU("Mokou", MKU.Enums.MKU), MKU_BUTTON, MKU_PORTRAIT, MKU.Enums.MKU);
        logger.info("Added " + MKU.Enums.MKU.toString());
    }
    @Override
    public void receivePostInitialize() {
        try { CreatePanel(); }
        catch (IOException e) { e.printStackTrace(); }
        resonanceBurst = new ResonanceBurst();
        logger.info("Loading badge image and mod options");
        logger.info("Done loading badge Image and mod options");
        BaseMod.addSaveField("TheImmortalExhaustionCount", new CustomSavable<Integer>() {
            @Override
            public Integer onSave() {
                return ImmortalityManager.getExhaustion();
            }

            @Override
            public void onLoad(Integer i) {
                ImmortalityManager.setExhaustion(i == null ? 3: i);
            }
        });

    }
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        BaseMod.addRelicToCustomPool(new PhoenixFeather(), MKU.Enums.COLOR_RED);
        //BaseMod.addRelic(new PlaceholderRelic2(), RelicType.SHARED);
        //UnlockTracker.markRelicAsSeen(BottledPlaceholderRelic.ID);
        logger.info("Done adding relics!");
    }
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        pathCheck();
        logger.info("Add variabls");
        BaseMod.addDynamicVariable(new MokouSecondMagicNumber());
        BaseMod.addDynamicVariable(new DoubleDamage());
        logger.info("Adding cards");
        new AutoAdd(modID).packageFilter("MokouMod.cards").setDefaultSeen(true).cards();
    }
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
    public static void runAnimation(String anim) {
        if (p() instanceof MKU) { ((MKU) p()).runAnim(anim); }
    }
    public void receiveOnBattleStart(AbstractRoom room) {
        tackybypass = true;
        if (p() instanceof MKU) {
            if (MokouMod.activeTutorials[0]){ atb(new MessageCaller(0)); }
            ResonanceMechanics.resonanceTurnAmount.set(p(), 0);
            ResonanceMechanics.resonanceburstPhase.set(p(), 0);
            ResonanceMechanics.maxResonanceBurstPhase.set(p(), 10);
            resonanceBurst.reset();
            BurstMechanics.PlayerBurstField.isBurst.set(p(), false);
            drawResonanceBurstUI = true;
        }
    }
    @Override
    public void receivePreRoomRender(SpriteBatch sb) {
        if (AbstractDungeon.getCurrRoom() != null && drawResonanceBurstUI && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) { resonanceBurst.render(sb, false); }
        else { resonanceBurst.render(sb, true); }
    }
    private void CreatePanel() throws IOException {
        SpireConfig spireConfig = new SpireConfig("mokoumod", "MokouModConfig");
        ModPanel settingsPanel = new ModPanel();
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("CreatePanelMokou"));
        String[] TEXT = uiStrings.TEXT;
        ModLabeledToggleButton tutorialOpen = new ModLabeledToggleButton(TEXT[0], 500.0F, 550.0F, Settings.CREAM_COLOR, FontHelper.charDescFont, activeTutorials[0], settingsPanel, label -> {
        }, button -> {
            for (int i = 0; i < activeTutorials.length; i++) { spireConfig.setBool("activeTutorials", activeTutorials[i] = button.enabled); }
            CardCrawlGame.mainMenuScreen.optionPanel.effects.clear();
            try { spireConfig.save();
            } catch (IOException e) { e.printStackTrace(); }
        });
        settingsPanel.addUIElement(tutorialOpen);
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }
    public static void saveData() throws IOException {
        SpireConfig config = new SpireConfig("mokoumod", "MokouModConfig");
        int i;
        for (i = 0; i < activeTutorials.length; i++) { config.setBool("activeTutorials" + i, activeTutorials[i]); }
        config.save();
    }
    @Override
    public void receivePreStartGame() {
        resonanceBurst.hide();
        drawResonanceBurstUI = false;
    }
    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        ResonanceMechanics.resonanceTurnAmount.set(p(), 0);
        BurstMechanics.PlayerBurstField.isBurst.set(p(), false);
        if(p().hasPower(OverheatPower.POWER_ID)){ atb(new RemoveSpecificPowerAction(p(), p(), OverheatPower.POWER_ID)); }
        drawResonanceBurstUI = false;
    }
}