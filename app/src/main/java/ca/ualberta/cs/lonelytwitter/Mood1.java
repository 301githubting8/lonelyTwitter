package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by ting8 on 9/15/16.
 */
public class Mood1 extends Mood {

    public Mood1(Date date){
        super(date);
    }

    @Override
    public String currentMood() {
        return "Mood1";
    }
}
