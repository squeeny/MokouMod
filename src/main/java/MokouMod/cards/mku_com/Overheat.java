package MokouMod.cards.mku_com;


import MokouMod.actions.StokeAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;

public class Overheat extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Overheat.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.NONE
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 8;
    public static int DRAW = 1;
    private static final int ENHANCE = 2;
    public Overheat() {
        super(cardInfo, true);
        setBlock(BLOCK);
        setMagic(DRAW);
        setMokouMagic(ENHANCE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(this.block);
        atb(new DrawCardAction(p, this.magicNumber));
        if(this.upgraded){ atb(new StokeAction(this.mokouSecondMagicNumber)); }
        if(this.overheated){ atb(new StokeAction(this.mokouSecondMagicNumber)); }
    }
}