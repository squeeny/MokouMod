package MokouMod.cards.mku_rar;

import MokouMod.actions.ExtinguishAction;
import MokouMod.cards.mku_abs.abs_mku_card;
import MokouMod.patches.combat.BurstMechanics;
import MokouMod.powers.IgnitePower;
import Utilities.CardInfo;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import static CapriCore.CapriCore.makeCardPath;
import static MokouMod.MokouMod.makeID;
import static Utilities.squeenyUtils.*;
public class Extinguish extends abs_mku_card {
    private final static CardInfo cardInfo = new CardInfo(
            Extinguish.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    public Extinguish() {
        super(cardInfo,true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { atb(new ExtinguishAction(this.upgraded, this.overheated)); }
    public void applyPowers() {
        super.applyPowers();
        int count = p().exhaustPile.group.size();
        int validity = 0;
        int criteria = this.upgraded ? (p().masterDeck.size() / 3) * 2 : (p().masterDeck.size() / 4) * 3;
        if (count > 0) {
            for (AbstractCard card : p().exhaustPile.group) { if (StSLib.getMasterDeckEquivalent(card) != null){ validity++; }
            }
        }
        boolean hitTotal = validity >= criteria;
        System.out.println(validity);
        System.out.println(criteria);
        System.out.println(hitTotal);
        this.rawDescription = hitTotal ? cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[2] : cardStrings.DESCRIPTION + String.format(cardStrings.EXTENDED_DESCRIPTION[1], criteria - validity);
        initializeDescription();
    }
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }
}