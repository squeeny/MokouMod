package MokouMod.cards.mku_rar;

import MokouMod.actions.GainBlockPerIgniteStackAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Utilities.squeenyUtils.*;
public class ProfanedFlare extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            ProfanedFlare.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    public ProfanedFlare() {
        super(cardInfo, false);
        setRetain(false, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster mo: getAliveMonsters()){
            if(p().hasPower(IgnitePower.POWER_ID)) { doPow(mo, new StrengthPower(mo, -p().getPower(IgnitePower.POWER_ID).amount)); }
        }
        if (this.overheated) { atb(new GainBlockPerIgniteStackAction()); }
    }
}
