package MokouMod.cards.mku_unc;

import MokouMod.actions.DoubleIgniteAction;
import MokouMod.actions.GainBlockPerIgniteStackAction;
import MokouMod.actions.TripleIgniteAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.cards.CardENUMs;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jdk.nashorn.internal.ir.annotations.Ignore;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class HeatTransfer extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            HeatTransfer.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    public HeatTransfer() {
        super(cardInfo, true);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster mo: getAliveMonsters()){
            if(mo.hasPower(IgnitePower.POWER_ID)) {
                doPow(p, new IgnitePower(p, (this.upgraded ? mo.getPower(IgnitePower.POWER_ID).amount * 3 : mo.getPower(IgnitePower.POWER_ID).amount* 2 )));
                atb(new RemoveSpecificPowerAction(mo, p, IgnitePower.POWER_ID));
            }
        }
        if (this.overheated) { atb(new GainBlockPerIgniteStackAction()); }
    }
}
