package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.util.mokouUtils;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.animationHandler;
import static Utilities.squeenyUtils.*;

public class Firecracker extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Firecracker.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;
    private static final int ENERGY = 2;
    public Firecracker() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(DRAW, UPG_DRAW);
        setMokouMagic(ENERGY);
        setBurst(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(this.block);
        if (mokouUtils.anonymouscheckBurst()) { atb(new DrawCardAction(p(), DRAW)); }
        if(this.overheated){ atb(new GainEnergyAction(this.mokouSecondMagicNumber)); }
    }
}