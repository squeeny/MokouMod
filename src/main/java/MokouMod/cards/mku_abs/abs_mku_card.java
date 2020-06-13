package MokouMod.cards.mku_abs;

import MokouMod.MokouMod;
import MokouMod.cards.mku_bas.Defend;
import MokouMod.cards.mku_com.Stoke;
import MokouMod.characters.MKU;
import MokouMod.patches.cards.CardENUMs;
import MokouMod.powers.SpontaneousHumanCombustionPower;
import MokouMod.powers.OverheatPower;
import Utilities.CardInfo;
import Utilities.TextureLoader;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.CommonKeywordIconsField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.random.Random;

import java.util.ArrayList;

import static MokouMod.MokouMod.getModID;
import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.*;
import static Utilities.squeenyUtils.p;

public abstract class abs_mku_card extends CustomCard {
    public static final int[] COSTS = new int[]{0, 1, 2, 3, 4 , 5};
    public static final int COST_X = -1;
    public static final int COST_UNPLAYABLE = -2;
    protected CardStrings cardStrings;
    protected String img;
    protected boolean upgradesDescription;
    protected int baseCost;
    protected boolean upgradeCost;
    protected boolean upgradeDamage;
    protected boolean upgradeBlock;
    protected boolean upgradeMagic;
    protected boolean upgradeMokouMagic;
    protected int costUpgrade;
    protected int damageUpgrade;
    protected int blockUpgrade;
    protected int magicUpgrade;
    protected int mokoumagicUpgrade;
    protected boolean baseExhaust;
    protected boolean upgradeExhaust;
    protected boolean baseInnate;
    protected boolean upgradeInnate;
    protected boolean baseRetain;
    protected boolean upgradeRetain;
    protected boolean baseEthereal;
    protected boolean upgradeEthereal;
    protected boolean baseIgnite;
    protected boolean upgradeIgnite;
    protected boolean baseBurst;
    protected boolean upgradeBurst;
    public int mokouSecondMagicNumber;
    public int mokouBaseSecondMagicNumber;
    public boolean upgradedMokouSecondMagicNumber;
    public boolean isMokouSecondMagicNumberModified;
    public boolean alwaysModified;
    private static final float FPS_SCALE = (240f / Settings.MAX_FPS);
    private static final int MAX_PARTICLES = 75;
    public boolean overheated;
    private boolean showoverheat;
    private abs_mku_overheat overheat;
    public boolean profaned;

    public ArrayList<FlameParticle> particles;
    public abs_mku_card(CardInfo cardInfo, boolean upgradesDescription) {
        this(MKU.Enums.COLOR_RED, cardInfo.cardName, cardInfo.cardCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, upgradesDescription, true);
    }
    public abs_mku_card(CardInfo cardInfo, boolean upgradesDescription, boolean showoverheat) {
        this(MKU.Enums.COLOR_RED, cardInfo.cardName, cardInfo.cardCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, upgradesDescription, showoverheat);
    }
    public abs_mku_card(CardColor color,
                        String cardName,
                        int cardCost,
                        CardType cardType,
                        CardTarget cardtarget,
                        CardRarity cardRarity,
                        boolean upgradesDescription, boolean showoverheat) {
        super(makeID(cardName), "", (String) null, cardCost, "", cardType, color, cardRarity, cardtarget);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);
        img = TextureLoader.getAndLoadCardTextureString(cardName, getModID());
        this.textureImg = img;
        loadCardImage(textureImg);
        this.rarity = autoRarity();
        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
        this.isDamageModified = false;
        this.isBlockModified = false;
        this.isMagicNumberModified = false;
        this.isMokouSecondMagicNumberModified = false;
        this.alwaysModified = true;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.originalName = cardStrings.NAME;
        this.name = originalName;
        this.baseCost = cost;
        this.upgradesDescription = upgradesDescription;
        this.upgradeCost = false;
        this.upgradeDamage = false;
        this.upgradeBlock = false;
        this.upgradeMagic = false;
        this.upgradeMokouMagic = false;
        this.costUpgrade = cost;
        this.damageUpgrade = 0;
        this.blockUpgrade = 0;
        this.magicUpgrade = 0;
        this.mokoumagicUpgrade = 0;
        this.baseIgnite = false;
        this.upgradeIgnite = false;
        this.baseBurst = false;
        this.upgradeBurst = false;
        this.upgradeRetain = false;
        this.upgradeInnate = false;
        this.upgradeExhaust = false;
        this.upgradeEthereal = false;
        if (cardName.toLowerCase().contains("strike")) {
            if(this.rarity == cardRarity.BASIC){ this.tags.add(CardTags.STARTER_STRIKE); }
            this.tags.add(CardTags.STRIKE);
        } if (cardName.toLowerCase().contains("defend")) {
            if(this.rarity == cardRarity.BASIC){ this.tags.add(CardTags.STARTER_DEFEND); } }
        this.particles = new ArrayList<>();
        this.overheated = p() != null && p().hasPower(OverheatPower.POWER_ID) ? true : false;
        this.profaned = false;
        CommonKeywordIconsField.useIcons.set(this, true);
        this.showoverheat = showoverheat;
        if(showoverheat) {
            overheat = new abs_mku_overheat(
                    new CardInfo(
                            cardName,
                            COST_UNPLAYABLE,
                            this.type,
                            this.target
                    ), false);
            this.cardsToPreview = overheat;
        }
        InitializeCard();
    }
    /**
     * Methods to use in constructors
     */
    public void setDamage(int damage) {
        this.setDamage(damage, 0);
    }

    public void setDamage(int damage, int damageUpgrade) {
        this.baseDamage = this.damage = damage;
        if(showoverheat){ overheat.baseDamage = this.baseDamage; }
        if (damageUpgrade != 0) {
            this.upgradeDamage = true;
            this.damageUpgrade = damageUpgrade;
            if(showoverheat){
                overheat.upgradeDamage = true;
                overheat.damageUpgrade = damageUpgrade;
            }
        }
    }

    public void setBlock(int block) {
        this.setBlock(block, 0);
    }

    public void setBlock(int block, int blockUpgrade) {
        this.baseBlock = this.block = block;
        if(showoverheat){ overheat.baseBlock = this.baseBlock; }
        if (blockUpgrade != 0) {
            this.upgradeBlock = true;
            this.blockUpgrade = blockUpgrade;
            if(showoverheat) {
                overheat.upgradeBlock = true;
                overheat.blockUpgrade = this.blockUpgrade;
            }
        }
    }

    public void setMagic(int magic) {
        this.setMagic(magic, 0);
    }

    public void setMagic(int magic, int magicUpgrade) {
        this.baseMagicNumber = this.magicNumber = magic;
        if(showoverheat){ overheat.baseMagicNumber = this.baseMagicNumber; }
        if (magicUpgrade != 0) {
            this.upgradeMagic = true;
            this.magicUpgrade = magicUpgrade;
            if(showoverheat) {
                overheat.magicNumber = this.magicUpgrade;
                overheat.upgradeMagic = true;
            }
        }
    }

    public void setMokouMagic(int magic) {
        this.setMokouMagic(magic, 0);
    }

    public void setMokouMagic(int magic, int magicUpgrade) {
        this.mokouBaseSecondMagicNumber = this.mokouSecondMagicNumber = magic;
        if(showoverheat){ overheat.mokouBaseSecondMagicNumber = this.mokouBaseSecondMagicNumber; }
        if (magicUpgrade != 0) {
            this.upgradeMokouMagic = true;
            this.mokoumagicUpgrade = magicUpgrade;
            if(showoverheat) {
                overheat.upgradeMokouMagic = true;
                overheat.mokoumagicUpgrade = this.mokoumagicUpgrade;
            }
        }
    }

    public void setCostUpgrade(int costUpgrade) {
        this.costUpgrade = costUpgrade;
        this.upgradeCost = true;
    }

    public void setExhaust(boolean exhaust) {
        this.setExhaust(exhaust, exhaust);
    }

    public void setExhaust(boolean baseExhaust, boolean upgExhaust) {
        this.baseExhaust = baseExhaust;
        this.upgradeExhaust = upgExhaust;
        this.exhaust = baseExhaust;
    }

    public void setInnate(boolean innate) {
        this.setInnate(innate, innate);
    }

    public void setInnate(boolean baseInnate, boolean upgInnate) {
        this.baseInnate = baseInnate;
        this.isInnate = baseInnate;
        this.upgradeInnate = upgInnate;
    }

    public void setRetain(boolean retain) {
        this.setRetain(retain, retain);
    }

    public void setRetain(boolean baseRetain, boolean upgRetain) {
        this.baseRetain = baseRetain;
        this.selfRetain = baseRetain;
        this.upgradeRetain = upgRetain;
    }

    public void setEthereal(boolean ethereal) {
        this.setEthereal(ethereal, ethereal);
    }

    public void setEthereal(boolean baseEthereal, boolean upgEthereal) {
        this.baseEthereal = baseEthereal;
        this.isEthereal = baseEthereal;
        this.upgradeEthereal = upgEthereal;
    }

    public void setIgnite(boolean ignite) {
        this.setIgnite(ignite, ignite);
    }

    public void setIgnite(boolean baseIgnite, boolean upgradeToIgnite) {
        if (baseIgnite) {
            this.baseIgnite = true;
            tags.add(CardENUMs.IGNITE);
        }
        this.upgradeIgnite = upgradeToIgnite;
    }

    public void setBurst(boolean burst) {
        this.setBurst(burst, burst);
    }

    public void setBurst(boolean baseBurst, boolean upgradeToBurst) {
        if (baseBurst) {
            this.baseBurst = true;
            tags.add(CardENUMs.BURST);
        }
        this.upgradeBurst = upgradeToBurst;
    }

    public void setMultiDamage(boolean isMultiDamage) {
        this.isMultiDamage = isMultiDamage;
    }

    private CardRarity autoRarity() {
        String packageName = this.getClass().getPackage().getName();
        String directParent;
        if (packageName.contains(".")) {
            directParent = packageName.substring(1 + packageName.lastIndexOf("."));
        } else {
            directParent = packageName;
        }
        switch (directParent) {
            case "mku_com":
                return CardRarity.COMMON;
            case "mku_unc":
                return CardRarity.UNCOMMON;
            case "mku_rar":
                return CardRarity.RARE;
            case "mku_bas":
                return CardRarity.BASIC;
            default:
                MokouMod.logger.info("Automatic Card rarity resulted in SPECIAL, input: " + directParent);
                return CardRarity.SPECIAL;
        }
    }
    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        if (card instanceof abs_mku_card) {
            card.rawDescription = this.rawDescription;
            ((abs_mku_card) card).upgradesDescription = this.upgradesDescription;
            ((abs_mku_card) card).baseCost = this.baseCost;
            ((abs_mku_card) card).upgradeCost = this.upgradeCost;
            ((abs_mku_card) card).upgradeDamage = this.upgradeDamage;
            ((abs_mku_card) card).upgradeBlock = this.upgradeBlock;
            ((abs_mku_card) card).upgradeMagic = this.upgradeMagic;
            ((abs_mku_card) card).upgradeMokouMagic = this.upgradeMokouMagic;
            ((abs_mku_card) card).costUpgrade = this.costUpgrade;
            ((abs_mku_card) card).damageUpgrade = this.damageUpgrade;
            ((abs_mku_card) card).blockUpgrade = this.blockUpgrade;
            ((abs_mku_card) card).magicUpgrade = this.magicUpgrade;
            ((abs_mku_card) card).mokoumagicUpgrade = this.mokoumagicUpgrade;
            ((abs_mku_card) card).baseExhaust = this.baseExhaust;
            ((abs_mku_card) card).upgradeExhaust = this.upgradeExhaust;
            ((abs_mku_card) card).baseInnate = this.baseInnate;
            ((abs_mku_card) card).upgradeInnate = this.upgradeInnate;
            ((abs_mku_card) card).baseRetain = this.baseRetain;
            ((abs_mku_card) card).upgradeRetain = this.upgradeRetain;
            ((abs_mku_card) card).baseEthereal = this.baseEthereal;
            ((abs_mku_card) card).upgradeEthereal = this.upgradeEthereal;
            ((abs_mku_card) card).baseIgnite = this.baseIgnite;
            ((abs_mku_card) card).upgradeIgnite = this.upgradeIgnite;
            ((abs_mku_card) card).baseBurst = this.baseBurst;
            ((abs_mku_card) card).upgradeBurst = this.upgradeBurst;
            ((abs_mku_card) card).mokouBaseSecondMagicNumber = this.mokouBaseSecondMagicNumber;
            ((abs_mku_card) card).mokouSecondMagicNumber = this.mokouSecondMagicNumber;
            ((abs_mku_card) card).profaned = this.profaned;
        }
        return card;
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            if(showoverheat) { overheat.upgradeName(); }
            if (this.upgradesDescription) { this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; }
            if (upgradeCost) {
                int diff = this.baseCost - this.cost; //positive if cost is reduced
                this.upgradeBaseCost(costUpgrade);
                this.cost -= diff;
                this.costForTurn -= diff;
                if (cost < 0) { cost = 0; }
                if (costForTurn < 0) { costForTurn = 0; }
            }
            if (upgradeDamage) { this.upgradeDamage(damageUpgrade); }
            if (showoverheat && upgradeDamage) { overheat.upgradeDamage(damageUpgrade); }
            if (upgradeBlock) { this.upgradeBlock(blockUpgrade); }
            if (showoverheat && upgradeBlock) { overheat.upgradeBlock(blockUpgrade); }
            if (upgradeMagic) { this.upgradeMagicNumber(magicUpgrade); }
            if (showoverheat && upgradeMagic) { overheat.upgradeMagicNumber(magicUpgrade); }
            if (upgradeMokouMagic) { this.upgradeMokouSecondMagicNumber(mokoumagicUpgrade); }
            if (showoverheat && upgradeMokouMagic) { overheat.upgradeMokouSecondMagicNumber(mokoumagicUpgrade); }
            if (!baseBurst && upgradeBurst) { tags.add(CardENUMs.BURST);
            } else if (baseBurst && !upgradeBurst) { tags.remove(CardENUMs.BURST); }
            if (!baseIgnite && upgradeIgnite) { tags.add(CardENUMs.IGNITE);
            } else if (baseIgnite && !upgradeIgnite) { tags.remove(CardENUMs.IGNITE); }
            if (baseExhaust ^ upgradeExhaust) { this.exhaust = upgradeExhaust; }
            if (baseInnate ^ upgradeInnate) { this.isInnate = upgradeInnate; }
            if (baseRetain ^ upgradeRetain) { this.selfRetain = upgradeRetain; }
            if (baseEthereal ^ upgradeEthereal) { this.isEthereal = upgradeEthereal; }
            if(showoverheat){ overheat.initializeDescription(); }
            this.initializeDescription();

        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (this.hasTag(CardENUMs.BURST) && anonymouscheckBurst()) { glowColor = GOLD_BORDER_GLOW_COLOR;
        } else { glowColor = BLUE_BORDER_GLOW_COLOR; }
    }

    public void InitializeCard() {
        FontHelper.cardDescFont_N.getData().setScale(1.0f);
        this.initializeTitle();
        this.initializeDescription();
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedMokouSecondMagicNumber) {
            mokouSecondMagicNumber = mokouBaseSecondMagicNumber;
            isMokouSecondMagicNumberModified = true;
        }
    }

    public void upgradeMokouSecondMagicNumber(int amount) {
        mokouBaseSecondMagicNumber += amount;
        mokouSecondMagicNumber = mokouBaseSecondMagicNumber;
        upgradedMokouSecondMagicNumber = true;
    }


    // This code is from Infinite Spire!
    // Credit to blank for the original code.

    @Override
    public void update(){
        super.update();
        if (this.overheated && (p() != null & !p().hasPower(OverheatPower.POWER_ID))) { if(!this.purgeOnUse) { this.overheated = false; } }
        else if (!showoverheat){ this.overheated = false; }
        else if(p() != null && p().hasPower(OverheatPower.POWER_ID)){ this.overheated = true; }
        else { this.overheated = false; }
        if(this.overheated) {
            particles.parallelStream()
                    .forEach(FlameParticle::update);
            particles.removeIf(FlameParticle::isDead);
            if (this.particles.size() < MAX_PARTICLES && !Settings.DISABLE_EFFECTS) {
                for (int i = 0; i < 2 * FPS_SCALE; i++) {
                    Vector2 point = generateRandomPointAlongEdgeOfHitbox();
                    particles.add(new FlameParticle(point.x, point.y, this.drawScale / 2, this.upgraded));
                }
            }
        }
        else{ particles.removeIf(FlameParticle::isnotDead); }
    }

    private Vector2 generateRandomPointAlongEdgeOfHitbox() {
        Vector2 result = new Vector2();
        Random random = new Random();
        boolean topOrBottom = random.randomBoolean();
        boolean leftOrRight = random.randomBoolean();
        boolean tbOrLr = random.randomBoolean();

        if(tbOrLr){
            result.x = random.random(this.hb.cX - (this.hb.width / 2f), this.hb.cX + (this.hb.width / 2f));
            result.y = topOrBottom ? this.hb.cY + (this.hb.height / 2f) : this.hb.cY - (this.hb.height / 2f);
        } else {
            result.x = leftOrRight ? this.hb.cX + (this.hb.width / 2f) : this.hb.cX - (this.hb.width / 2f);
            result.y = random.random(this.hb.cY - (this.hb.height / 2f), this.hb.cY + (this.hb.height / 2f));
        }

        return result;
    }

    @Override
    public void render(SpriteBatch sb){
        sb.setColor(Color.WHITE.cpy());
        particles.stream()
                .forEach(flameParticle -> flameParticle.render(sb));
        super.render(sb);
    }

    public static class FlameParticle {
        private Vector2 pos;
        private Vector2 vel;
        private float lifeSpan;
        private Color color;
        private float drawScale;
        private boolean upgraded;

        private static TextureAtlas.AtlasRegion image;

        public FlameParticle(float x, float y, float drawScale, boolean upgraded) {
            pos = new Vector2(x, y);
            this.drawScale = drawScale;
            this.upgraded = upgraded;
            float speedScale = MathUtils.clamp(
                    Gdx.graphics.getDeltaTime() * 240f,
                    FPS_SCALE - 0.2f,
                    FPS_SCALE + 0.2f);
            float maxV = 2.0f * drawScale;
            maxV = MathUtils.clamp(maxV, 0.01f, FPS_SCALE * 2f);
            float velX = MathUtils.random(-maxV * speedScale / 2f, maxV * speedScale / 2f);
            float velY = MathUtils.random(0.01f, maxV * speedScale);
            vel = new Vector2(velX, velY);
            lifeSpan = MathUtils.random(0.1f, 0.5f);
            if(p().hasPower(SpontaneousHumanCombustionPower.POWER_ID)){ color = Color.ROYAL.cpy(); }
            else{ color = Color.RED.cpy(); }
            if(Math.random() < 0.25) {
                if (p().hasPower(SpontaneousHumanCombustionPower.POWER_ID)) { color = getRandomBlueFireColor().cpy(); }
                else { color = getRandomFireColor().cpy(); }
            }
            if(Math.random() < 0.05 && upgraded) {
                if (p().hasPower(SpontaneousHumanCombustionPower.POWER_ID)) { color = getRandomBlueFireColor().cpy();  }
                else { color = getRandomFireColor().cpy(); }
            }
        }

        public void update() {
            this.lifeSpan -= Gdx.graphics.getDeltaTime();
            this.pos.x += this.vel.x;
            this.pos.y += this.vel.y;
        }

        public void render(SpriteBatch sb) {
            sb.setColor(color);
            int roll = MathUtils.random(0, 2);
            if (roll == 0) { this.image = ImageMaster.TORCH_FIRE_1;
            } else if (roll == 1) { this.image = ImageMaster.TORCH_FIRE_2;
            } else { this.image = ImageMaster.TORCH_FIRE_3; }
            sb.draw(this.image,
                    pos.x - 40f,
                    pos.y - 40f,
                    40f,
                    40f,
                    80f,
                    80f,
                    drawScale * (lifeSpan / 0.25f),
                    drawScale * (lifeSpan / 0.25f),
                    0f);
        }
        public boolean isDead() {
            return lifeSpan <= 0f;
        }
        public boolean isnotDead() {
            return lifeSpan != 0f;
        }
    }

}