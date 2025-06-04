package com.mycompany.game;

public class Skill {

    private final String name;
    private int mastery; // 0-100, affects power

    public Skill(String name) {
        this.name = name;
        this.mastery = 0;
    }

    public String getName() {
        return name;
    }

    public int getMastery() {
        return mastery;
    }

    public void increaseMastery(int amt) {
        mastery = Math.min(100, mastery + amt);
    }

    @Override
    public String toString() {
        return name + " (Mastery: " + mastery + "%)";
    }
}
