import java.util.Random;

public class heroesHit {
    public static void heroesHit() {
        for (int i = 0; i < Main.heroesDamage.length; i++) {
            if (Main.heroesHealth[i] > 0 && Main.bossHealth > 0) {
                int damage = Main.heroesDamage[i];
                if (Main.heroesAttackType[i] == Main.bossDefence) {
                    Random random = new Random();
                    int coefficient = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = coefficient * Main.heroesDamage[i];
                    System.out.println("Critical damage: " + damage);
                }
                int bossHealth
                        = 0;
                if (Main.bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }
}
