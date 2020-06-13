package MokouMod.cards.mku_com;

import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.doPow;
import static Utilities.squeenyUtils.getAliveMonsters;
public class IndiscriminateIgnition extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            IndiscriminateIgnition.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int IGNITE = 3;
    private static final int UPG_IGNITE = 2;
    public IndiscriminateIgnition() {
        super(cardInfo, false);
        setMagic(IGNITE, UPG_IGNITE);
        setExhaust(true);
        setIgnite(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster mo: getAliveMonsters()){ doPow(mo, new IgnitePower(mo, this.magicNumber)); }
        doPow(p, new IgnitePower(p, this.magicNumber));
        if(this.overheated){
            for(AbstractMonster mo: getAliveMonsters()){ doPow(mo, new IgnitePower(mo, this.magicNumber)); }
            doPow(p, new IgnitePower(p, this.magicNumber));
        }
    }
}