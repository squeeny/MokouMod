package MokouMod.cards.mku_rar;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.cards.CardENUMs;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class BurningHiddenBullet extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            BurningHiddenBullet.class.getSimpleName(),
            COSTS[3],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 6;
    private static final int EXTRA_DMG = 2;
    private static final int UPG_EXTRA_DMG = 1;
    public BurningHiddenBullet() {
        super(cardInfo, false);
        setDamage(DAMAGE);
        setMagic(EXTRA_DMG, UPG_EXTRA_DMG);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.overheated) { atb(new WallopAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        } else { doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE); }
    }
    public static int countCards() {
        int count = 0;
        for (AbstractCard c : p().hand.group) { if (criteria(c)) { count++; } }
        for (AbstractCard c : p().drawPile.group) { if (criteria(c)) { count++; } }
        for (AbstractCard c : p().discardPile.group) { if (criteria(c)) { count++; } }
        for (AbstractCard c : p().exhaustPile.group) { if (criteria(c)) { count++; } }
        return count;
    }
    public static boolean criteria(AbstractCard c) { return c.hasTag(CardENUMs.BURST); }
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * countCards();
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * countCards();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

}
