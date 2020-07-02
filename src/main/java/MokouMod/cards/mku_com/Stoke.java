package MokouMod.cards.mku_com;

import MokouMod.actions.StokeAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.interfaces.onEnemyGainIgniteSubscriber;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class Stoke extends abs_mku_card implements onEnemyGainIgniteSubscriber {
    private final static CardInfo cardInfo = new CardInfo(
            Stoke.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.SKILL,
            CardTarget.NONE
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE_UP = 2;
    private static final int UPG_DAMAGE_UP = 1;
    public Stoke() {
        super(cardInfo, false, false);
        setMagic(DAMAGE_UP, UPG_DAMAGE_UP);
        setExhaust(true);
        setEthereal(true);
    }
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }
    @Override
    public void onEnemyGainIgnite() {
        atb(new StokeAction(this.magicNumber));
    }
}
