package MokouMod.cards.mku_unc;


import MokouMod.actions.DiscardPileToHandAction;
import MokouMod.actions.ExhaustPileToDiscardAction;
import MokouMod.actions.SwitchPilesAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.MeltdownPower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
import static Utilities.squeenyUtils.doPow;

public class Meltdown extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Meltdown.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;
    public Meltdown() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new MeltdownPower(p, this.block));
        if(this.overheated){ doPow(p, new MeltdownPower(p, this.block)); }
    }
}