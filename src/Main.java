import java.util.Random;

public class Main {
    public static int bossHealth = 600;
    public static int bossDamage = 20;
    public static String bossDefence;
    public static int[] heroesHealth = {280, 270, 250, 300};
    public static int[] heroesDamage = {10, 15, 20, 0};
    public static String[] heroes = {"Viking", "Wizard", "Kinetic", "Medic"};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Healing"};
    public static int roundNumber;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++; // roundNumber = roundNumber + 1
        chooseBossDefence();
        bossHits();
        heroesHit();
        medicHeals();
        printStatistics();

    }

    public static void medicHeals(){
        for (int i = 0; i < heroesHealth.length - 1; i++) {
            if (heroesHealth[i] < 100) {
                Random random = new Random();
                int randomIndex100 = random.nextInt(heroesHealth.length - 1);
                if (heroesHealth[randomIndex100] > 0) {
                    int n = 10;
                    heroesHealth[randomIndex100] = heroesHealth[randomIndex100] + n;
                }
            }
            break;
        }
    }


    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boos choosed: " + bossDefence +  " defence");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage + (bossDamage/4);
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coefficient = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = coefficient * heroesDamage[i];
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        /*String defence = "No defence";
        if (bossDefence != null) {
            defence = bossDefence;
        }*/
        System.out.println("ROUND " + roundNumber + " --------------");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage
                + " " + (bossDefence != null ? bossDefence : ""));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroes[i] +  " health: " + heroesHealth[i]
                    + ", damage: " + heroesDamage[i]);
        }
    }
}