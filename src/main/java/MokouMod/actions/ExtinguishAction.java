package MokouMod.actions;

import MokouMod.powers.IgnitePower;
import MokouMod.vfx.ObtainRelicLater;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static Utilities.squeenyUtils.getAliveMonsters;
import Utilities.CardInfo;

import java.util.ArrayList;

import static Utilities.squeenyUtils.*;

public class ExtinguishAction extends AbstractGameAction {
    private boolean upgraded;
    private boolean overheated;
    public ExtinguishAction(boolean upgraded, boolean overheated){
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
        this.overheated = overheated;
    }
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int count = p().exhaustPile.group.size();
            int validity = 0;
            System.out.println(count);
            int criteria = this.upgraded ? (p().masterDeck.size() / 3) * 2 : (p().masterDeck.size() / 4) * 3;
            System.out.println(criteria);
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            if (count == 0) { this.isDone = true; }
            if (count > 0) {
                for (AbstractCard card : p().exhaustPile.group) {
                    if (StSLib.getMasterDeckEquivalent(card) != null){ validity++; }
                    stanceChoices.add(card);
                }
                for (AbstractCard card : stanceChoices) { p().exhaustPile.removeCard(card); }
            }
            boolean hitTotal = validity >= criteria ? true : false;
            System.out.println(hitTotal);
            if(hitTotal){
                getRelic(AbstractRelic.RelicTier.RARE);
                if(this.overheated){ getRelic(AbstractRelic.RelicTier.SHOP); }
                for(AbstractMonster mo: getAliveMonsters()){
                    att(new SuicideAction(mo));
                }
                this.isDone = true;
            }
            else { this.isDone = true; }
        }
    }
    public void getRelic(AbstractRelic.RelicTier relicTier) { AbstractDungeon.getCurrRoom().addRelicToRewards(relicTier); }
}