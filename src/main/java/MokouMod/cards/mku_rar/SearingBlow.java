package MokouMod.cards.mku_rar;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.interfaces.onBurstSubscriber;
import MokouMod.vfx.combat.BlueSearingBlowEffect;
import Utilities.CardInfo;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doDmg;
import static Utilities.squeenyUtils.doVfx;

public class SearingBlow extends abs_mku_card implements onBurstSubscriber {
    private final static CardInfo cardInfo = new CardInfo(
            SearingBlow.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 10;
    private static final int UPG_DMG = 4;
    public static boolean upgradeable;
    public SearingBlow() { this(0, true); }
    public SearingBlow(int upgrades, boolean upgradeable) {
        super(cardInfo, false);
        setDamage(DMG);
        this.upgradeable = upgradeable;
        this.timesUpgraded = upgrades;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.upgradeable = false;
        doVfx(new BlueSearingBlowEffect(m.hb.cX, m.hb.cY, this.timesUpgraded), 0.2F);
        doDmg(m, this.damage);
        if(this.overheated){
            if(StSLib.getMasterDeckEquivalent(this) != null){ StSLib.getMasterDeckEquivalent(this).upgrade(); }
            if (this.canUpgrade()) { this.upgrade(); }
        }
    }
    public boolean canUpgrade() { return true; }
    @Override
    public void upgrade() {
        upgradeDamage(UPG_DMG + this.timesUpgraded);
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }
    @Override
    public AbstractCard makeCopy() {
         return new SearingBlow(this.timesUpgraded, this.upgradeable);
    }
    @Override
    public void onBurst() {
        if(this.upgradeable){ this.upgrade(); }
        else { this.upgradeable = true; }
    }
}
