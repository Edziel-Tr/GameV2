package com.mycompany.game;

import java.util.*;

public class Game {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();

    // ... sects, sectSkills, etc.

    Player player = new Player();

    // --- Main Menu Loop ---
    void start() {
        // ... setupPlayer() etc ...
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Status");
            System.out.println("2. Inventory");
            System.out.println("3. Travel");
            System.out.println("4. Practice Sword Art");
            System.out.println("5. Rest");
            System.out.println("6. Exit");
            System.out.print("Pick: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1" -> showStatus(player);
                case "2" -> inventoryMenu();
                case "3" -> treeTravel(root);
                case "4" -> practiceSkill(player);
                case "5" -> { player.hp = player.hp*player.realm.statMultiplier; player.qi = player.qi*player.realm.statMultiplier; System.out.println("You meditate and recover."); }
                case "6" -> { System.out.println("May your sword reach the peak of Mount Hua!"); return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    // --- Inventory Menu ---
    void inventoryMenu() {
        System.out.println("\n--- Inventory ---");
        for (int i = 0; i < player.inventory.size(); i++)
            System.out.println((i+1) + ". " + player.inventory.get(i));
        if (player.inventory.isEmpty()) return;
        System.out.print("Choose an item number to use/learn, or 0 to leave: ");
        int pick = safeIntInput(0, player.inventory.size());
        if (pick == 0) return;
        Item item = player.inventory.get(pick-1);
        switch (item.type) {
            case MANUAL -> {
                if (player.learnedManuals.contains(item.skillToLearn)) {
                    System.out.println("You have already learned this sword art.");
                } else {
                    System.out.println("Learn " + item.skillToLearn + "? (y/n)");
                    if (scan.nextLine().equalsIgnoreCase("y")) {
                        player.skills.add(new Skill(item.skillToLearn));
                        player.learnedManuals.add(item.skillToLearn);
                        System.out.println("You have learned: " + item.skillToLearn + "!");
                        player.inventory.remove(item);
                    }
                }
            }
            case QI_PILL -> {
                System.out.println("Use " + item.name + "? (y/n)");
                if (scan.nextLine().equalsIgnoreCase("y")) {
                    player.qi = Math.min(player.qi + item.effectValue, 80 * (int)player.realm.statMultiplier);
                    System.out.println("Qi restored!");
                    player.inventory.remove(item);
                }
            }
            case HP_PILL -> {
                System.out.println("Use " + item.name + "? (y/n)");
                if (scan.nextLine().equalsIgnoreCase("y")) {
                    player.hp = Math.min(player.hp + item.effectValue, 120 * (int)player.realm.statMultiplier);
                    System.out.println("HP restored!");
                    player.inventory.remove(item);
                }
            }
        }
    }

    // --- Practice/Training/Sparring ---
    void practiceSkill(Player player) {
        System.out.println("\nWhich sword art to practice?");
        for (int i = 0; i < player.skills.size(); i++)
            System.out.println((i + 1) + ". " + player.skills.get(i));
        int n = safeIntInput(1, player.skills.size()) - 1;
        Skill skill = player.skills.get(n);
        skill.increaseMastery(5); // mastery up
        System.out.println("You practice " + skill.getName() + ". Mastery increased!");
        player.qi = Math.min(player.qi + 10, 80 * (int)player.realm.statMultiplier);
    }

    // --- Enemy Generation (with skills/rank/realm/title) ---
    Enemy randomEnemy() {
        // Pick sect, realm, title, and skills
        String[] sectNames = { "Mount Hua Sect", "Southern Edge Sect", "Wudang Sect" };
        CultivationRealm[] realms = CultivationRealm.values();
        String[][] skillsBySect = sectSkills; // as before

        String sect = sectNames[rand.nextInt(sectNames.length)];
        CultivationRealm realm = realms[rand.nextInt(realms.length)];
        String title = realm.title;
        int sectIndex = Arrays.asList(sects).indexOf(sect);
        Skill[] skills = Arrays.stream(skillsBySect[sectIndex])
            .map(Skill::new)
            .toArray(Skill[]::new);

        int base = realm.ordinal() + 1;
        int hp = 40 + rand.nextInt(20) * base;
        int atk = 10 + rand.nextInt(5) * base;
        int def = 5 + rand.nextInt(3) * base;

        String[] names = {"Disciple", "Elder", "Master", "Demon", "Bandit"};
        String name = sect + " " + names[rand.nextInt(names.length)] + " " + (char)('A' + rand.nextInt(5));
        return new Enemy(name, sect, realm, title, hp, atk, def, skills);
    }

    // --- Battle: allow use of pills and mastery affects skill power ---
    void battle(Enemy enemy) {
        System.out.println("Duel: " + enemy.name + " (" + enemy.title + ", " + enemy.realm.name() + ") uses " + enemy.skills[0].getName() + "!");
        while (player.hp > 0 && enemy.hp > 0) {
            System.out.println("HP: " + player.hp + " | Enemy HP: " + enemy.hp);
            // ... (skill selection and attack logic, use skills, use pills from inventory like above) ...
        }
    }

    // --- Cultivation Advancement Example (call when needed) ---
    void tryAdvanceRealm() {
        System.out.println("Try to advance your cultivation realm? (y/n)");
        if (scan.nextLine().equalsIgnoreCase("y")) {
            player.advanceRealm();
            System.out.println("You have advanced to " + player.realm.name() + "! Stats increased.");
        }
    }

    // ... rest of your methods ...

    static int safeIntInput(int min, int max) {
        try {
            int n = Integer.parseInt(scan.nextLine());
            if (n >= min && n <= max) return n;
        } catch (Exception ignored) {}
        System.out.println("Invalid, using " + min);
        return min;
    }
}
    

