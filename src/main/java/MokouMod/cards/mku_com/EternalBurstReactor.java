package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.combat.BurstMechanics;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.*;
public class EternalBurstReactor extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            EternalBurstReactor.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int DMG = 1;
    private static final int UPG_DMG = 2;
    public EternalBurstReactor() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(0);
    }
    @Override
    public float getTitleFontSize()
    {
        return 16;
    }
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        animationHandler(this);
        int burstCount = 0;
        for (int i = 0; i <= BurstMechanics.PlayerBurstField.turnBurstAmount.get(p()) - 1; i += 1) { burstCount++; }
        this.magicNumber = burstCount - 1;
        calculateCardDamage(m);
        for (int i = 0; i <= BurstMechanics.PlayerBurstField.turnBurstAmount.get(p()) - 1; i += 1){ doDmg(m, this.damage, AbstractGameAction.AttackEffect.BLUNT_LIGHT); }
        if(this.overheated){
            for (int i = 0; i <= BurstMechanics.PlayerBurstField.turnBurstAmount.get(p()) - 1; i += 1){ atb(new DrawCardAction(1)); }

        }
    }
    public void applyPowers() {
        int burstCount = 0;
        for (int i = 0; i <= BurstMechanics.PlayerBurstField.turnBurstAmount.get(p()) - 1; i += 1){ burstCount++; }
        if (burstCount <= 0) { this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1]; }
        if (burstCount > 0) {
            this.magicNumber = this.baseMagicNumber = burstCount;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }
}