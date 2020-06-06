package MokouMod.cards.mku_unc;

import MokouMod.actions.CataclysmAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.atb;
public class Cataclysm extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Cataclysm.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 2;
    private static final int UPG_DMG = 1;
    public Cataclysm() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new CataclysmAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.overheated));
    }
}

