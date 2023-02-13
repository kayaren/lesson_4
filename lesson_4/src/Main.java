import java.util.Random;

public class Main {
    public static int bossHealth = 3000;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {260, 270, 250, 300,300,230 };
    public static int[] heroesDamage = {20, 10, 15, 0, 40, 20};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic" , "medic", "lucky", "thor"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void medicSkill(){
        for (int i = 0; i < heroesHealth.length ; i++) {
            if (i==3){
                continue;

            }
            if (heroesHealth[i] > 0 && heroesHealth [i] <100 && heroesHealth[3]<0){
                heroesHealth[i]+=50;
                System.out.println("medic вылечил"+ heroesAttackType);
                break;
            }
        }
    }

    public static void lucky (){
        Random random = new Random();
        int randomEventsion = random.nextInt(4) +1 ;
        switch (randomEventsion){
            case 1:
                heroesHealth[4] = heroesHealth[4] + bossDamage;
                System.out.println("lucky");
        }

    }

    public static void thor(){
        Random random =  new Random();
        boolean thor = random.nextBoolean();
        if (thor){
            bossDamage = 0;
            System.out.println("босс оглушен");
        }else {bossDamage=50;}
    }


    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        printStatistics();
        lucky();
        medicSkill();
        thor();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    damage = heroesDamage[i] * coeff;
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

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " +
                (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
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
}
