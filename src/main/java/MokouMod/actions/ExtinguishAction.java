package MokouMod.actions;

import MokouMod.MokouMod;
import basemod.abstracts.CustomBottleRelic;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rewards.RewardItem;

import static Utilities.squeenyUtils.getAliveMonsters;

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
                for(AbstractMonster mo: getAliveMonsters()){ att(new InstantKillAction(mo)); }
            }
            this.isDone = true;
        }
    }
    public void getRelic(AbstractRelic.RelicTier relicTier) {
        AbstractRelic ______ = AbstractDungeon.returnRandomRelic(relicTier);
        do { ______ = AbstractDungeon.returnRandomRelic(relicTier);
        } while (______.relicId.equals(BottledFlame.ID)
                || ______.relicId.equals(BottledLightning.ID)
                || ______.relicId.equals(BottledTornado.ID)
                || ______.relicId.equals(Astrolabe.ID)
                || ______.relicId.equals(TinyHouse.ID)
                || ______.relicId.equals(Cauldron.ID)
                || ______.relicId.equals(Orrery.ID)
                || ______.relicId.equals(PandorasBox.ID)
                || ______.relicId.equals("Kintsugi")
                || ______ instanceof CustomBottleRelic
                || ______.relicId.equals("Thesaurus")
                || ______.relicId.equals(EmptyCage.ID)
                || ______.relicId.equals(CallingBell.ID));
        MokouMod.logger.info(______);
        AbstractDungeon.getCurrRoom().addRelicToRewards(______);
    }
}