package MokouMod.cards.mku_unc;

import MokouMod.actions.ConvertIgniteVigorAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class DanceofFlames extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            DanceofFlames.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int IGNITE = 3;
    private static final int UPG_IGNITE = 2;
    private static final int IGNITE_BLOCK = 3;
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    public DanceofFlames() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(IGNITE, UPG_IGNITE);
        setMokouMagic(IGNITE_BLOCK);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(IgnitePower.POWER_ID)) {
            for (int i = 0; i < p.getPower(IgnitePower.POWER_ID).amount / this.mokouSecondMagicNumber; ++i) { doDef(this.block); }
        }
        if(this.overheated){
            atb(new WaitAction(0.01F));
            atb(new ConvertIgniteVigorAction());
        }
    }
    @Override
    public void triggerWhenDrawn() { doPow(p(), new IgnitePower(p(), this.magicNumber)); }
}
