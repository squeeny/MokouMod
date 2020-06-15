package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.interfaces.onBurstSubscriber;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.*;

public class BlazingComet extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            BlazingComet.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 7;
    private static final int UPG_DMG = 3;
    private static final int DRAWPOWER = 2;
    private static final int UPG_DRAWPOWER = 1;
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;
    public BlazingComet() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(DRAWPOWER, UPG_DRAWPOWER);
        setMokouMagic(DRAW, UPG_DRAW);
        setBurst(true);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.FIRE);
        if(anonymouscheckBurst()){
            this.triggeredBurst = true;
            doPow(p, new DrawCardNextTurnPower(p, this.magicNumber)); }
        if(this.overheated){ atb(new DrawCardAction(this.mokouSecondMagicNumber)); }
    }
}
