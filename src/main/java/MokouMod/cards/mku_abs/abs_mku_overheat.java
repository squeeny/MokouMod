package MokouMod.cards.mku_abs;

import Utilities.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@AutoAdd.Ignore
public class abs_mku_overheat extends abs_mku_card {
    public abs_mku_overheat(CardInfo cardInfo, boolean upgradesDescription) {
        super(cardInfo, upgradesDescription, false);
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }
}
