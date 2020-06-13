package MokouMod.cards.mku_unc;

import MokouMod.actions.CataclysmAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static MokouMod.util.mokouUtils.anonymouscheckBurst;
import static Utilities.squeenyUtils.atb;
public class Cataclysm extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Cataclysm.class.getSimpleName(),
            COSTS[3],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 2;
    private static final int UPG_DMG = 1;
    private static final int BLOCK = 2;
    private static final int UPG_BLOCK = 1;
    public Cataclysm() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setBlock(BLOCK, UPG_BLOCK);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { atb(new CataclysmAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.overheated, this.block)); }
}

