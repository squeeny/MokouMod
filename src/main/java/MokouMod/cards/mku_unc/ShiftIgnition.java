package MokouMod.cards.mku_unc;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import MokouMod.vfx.combat.unique.FlameSlingEffect;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.watcher.TriggerMarksAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class ShiftIgnition extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ShiftIgnition.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);;
    private static final int IGNITE = 3;
    private static final int UPG_IGNITE = 2;
    public ShiftIgnition() {
        super(cardInfo, false);
        setDamage(IGNITE, UPG_IGNITE);
        setIgnite(true);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doVfx(new FlameSlingEffect(p.drawX, p.drawY+(p.hb_h/2f), m.drawX, m.drawY+(m.hb_h/2f)));
        doPow(m, new IgnitePower(m, this.damage));
        if (this.overheated){ atb(new TriggerMarksAction(this)); }
    }
}