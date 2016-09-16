package ca.ualberta.cs.lonelytwitter;

import java.util.Date;
/**
 * Created by ting8 on 9/15/16.
 */
public class Mood2 extends Mood {

    public Mood2(Date date){
        super(date);
    }

    @Override
    public String currentMood() {
        return "Mood2";
    }
}
