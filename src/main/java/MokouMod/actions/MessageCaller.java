package MokouMod.actions;

import MokouMod.MokouMod;
import MokouMod.util.MokouTutorials;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.io.IOException;

public class MessageCaller extends AbstractGameAction {
    private float startingDuration;
    private DamageInfo info;
    private boolean isupgraded;
    public int code;

    public MessageCaller(int code) {

        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.code = code;


    }


    public void update() {

        if (MokouMod.activeTutorials[code]) {
            AbstractDungeon.ftue = new MokouTutorials();
            MokouMod.activeTutorials[code] = false;

            try {
                MokouMod.saveData();
                this.isDone = true;;
            } catch (IOException e) {
                e.printStackTrace();
                this.isDone = true;
            }
        }


    }
}