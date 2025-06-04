
package com.mycompany.game;
import java.util.ArrayList;
import java.util.List;

public class Location {
    public String name;
    public Runnable action;
    public List<Location> children = new ArrayList<>();

    public Location(String name, Runnable action) {
        this.name = name;
        this.action = action;
    }
}
