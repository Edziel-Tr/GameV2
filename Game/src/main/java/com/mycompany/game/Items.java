
package com.mycompany.game;


public class Items {
    public enum Type { MANUAL, QI_PILL, HP_PILL }
    public final String name;
    public final Type type;
    public final String skillToLearn; // only for manuals
    public final int effectValue;     // amount healed or qi restored or cap increased
    public final String quality;      // e.g. Minor, Major, Supreme

    public Items(String name, Type type, String skillToLearn, int effectValue, String quality) {
        this.name = name;
        this.type = type;
        this.skillToLearn = skillToLearn;
        this.effectValue = effectValue;
        this.quality = quality;
    }

    @Override
    public String toString() {
        if (type == Type.MANUAL)
            return name + " (Manual for " + skillToLearn + ")";
        else
            return name + " [" + quality + "] (Effect: " + effectValue + ")";
    }
}
