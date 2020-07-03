package MokouMod.cards.mku_unc;

import MokouMod.actions.AdvancePhaseAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.combat.BurstMechanics;
import MokouMod.powers.IgnitePower;
import MokouMod.util.mokouUtils;
import MokouMod.vfx.general.BlueInflameEffect;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.*;

public class ImperishableFlame extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ImperishableFlame.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int IGNITE = 5;
    private static final int UPG_IGNITE = 2;
    private static final int RESONANCE = 1;
    private static final int UPG_RESONANCE = 1;
    public ImperishableFlame() {
        super(cardInfo, false);
        setMagic(IGNITE, UPG_IGNITE);
        setMokouMagic(RESONANCE, UPG_RESONANCE);
        this.purgeOnUse = true;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(m, new IgnitePower(m, this.magicNumber));
        atb(new AdvancePhaseAction(this.mokouSecondMagicNumber));
        if(this.overheated){
            for(AbstractMonster mo: getAliveMonsters()){ doPow(mo, new IgnitePower(mo, this.magicNumber)); }
            atb(new AdvancePhaseAction(this.mokouSecondMagicNumber));
        }
    }
    public void triggerOnExhaust() {
        atb(new MakeTempCardInHandAction(makeStatEquivalentCopy()));
    }
}
