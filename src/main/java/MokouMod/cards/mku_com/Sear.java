package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import MokouMod.vfx.combat.unique.FlameSlingEffect;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.*;
public class Sear extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Sear.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int IGNITE = 2;
    private static final int UPG_IGNITE = 1;
    private static final int VULN = 1;
    private static final int UPG_VULN = 1;
    private static final int DRAW = 2;
    public Sear() {
        super(cardInfo, true);
        setMagic(IGNITE, UPG_IGNITE);
        setMokouMagic(VULN, UPG_VULN);
        setIgnite(true);
        setBurst(false, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doVfx(new FlameSlingEffect(p.drawX, p.drawY+(p.hb_h/2f), m.drawX, m.drawY+(m.hb_h/2f)));
        doPow(m, new IgnitePower(m, this.magicNumber));
        doPow(m, new VulnerablePower(m, this.mokouSecondMagicNumber, false));
        if(upgraded && anonymouscheckBurst()) {
            this.triggeredBurst = true;
            atb(new DrawCardAction(p(), DRAW)); }
        if(this.overheated){ doPow(m, new WeakPower(m, this.mokouSecondMagicNumber, false)); }
    }
}