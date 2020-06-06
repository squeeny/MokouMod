package MokouMod.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Utilities.CardInfo;
import static Utilities.squeenyUtils.*;
public class ProfanedFlameMod extends AbstractCardModifier {
    private int damage;
    private boolean overheat;
    private int overheatcharges;
    private String desc;

    public ProfanedFlameMod(int damage){
        this(damage, false, 0, "");
    }
    public ProfanedFlameMod(int damage, boolean overheat, int overheatcharges, String desc) {
        this.damage = damage;
        this.overheat = overheat;
        this.overheatcharges = overheatcharges;
        this.desc = desc;
    }
    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        overheatcharges = overheatcharges > 0 ? overheatcharges : 0;
        if(!(overheatcharges == 0)){ atb(new GainEnergyAction(overheatcharges)); }
    }
    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) { return damage + this.damage; }
    @Override
    public AbstractCardModifier makeCopy() { return new ProfanedFlameMod(damage, overheat, overheatcharges, desc); }
    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) { return this.overheat ? rawDescription + desc : ""; }
}