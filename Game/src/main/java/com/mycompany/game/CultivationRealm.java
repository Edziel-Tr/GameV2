package com.mycompany.game;

public class CultivationRealm {

    public class Enemy {

        public String name;
        public String sect;
        public CultivationRealm realm;
        public String title;
        public int hp, atk, def;
        public Skill[] skills;

        public Enemy(String name, String sect, CultivationRealm realm, String title, int hp, int atk, int def, Skill[] skills) {
            this.name = name;
            this.sect = sect;
            this.realm = realm;
            this.title = title;
            this.hp = hp;
            this.atk = atk;
            this.def = def;
            this.skills = skills;
        }
    }
}
