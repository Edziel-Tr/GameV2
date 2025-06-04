package com.mycompany.game;

import java.util.ArrayList;

public class Player {

    public String name;
    public String sect;
    public CultivationRealm realm = CultivationRealm.OUTER;
    public int hp = 120;
    public int qi = 80;
    public int atk = 18;
    public int def = 7;
    public int silver = 50;
    public ArrayList<Skill> skills = new ArrayList<>();
    public ArrayList<Items> inventory = new ArrayList<>();
    public ArrayList<String> learnedManuals = new ArrayList<>();
    public String sectTitle = "Disciple";

    public void advanceRealm() {
        int next = realm.ordinal() + 1;
        if (next < CultivationRealm.values().length) {
            realm = CultivationRealm.values()[next];
            // adjust stats
            hp = (int) (120 * realm.statMultiplier);
            qi = (int) (80 * realm.statMultiplier);
            atk = (int) (18 * realm.statMultiplier);
            def = (int) (7 * realm.statMultiplier);
            // update title if needed
            sectTitle = realm.title;
        }
    }
}
