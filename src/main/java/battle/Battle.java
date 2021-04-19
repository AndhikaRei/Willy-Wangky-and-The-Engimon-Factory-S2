package main.java.battle;

import java.util.List;

import main.java.element.Element;
import main.java.engimon.Engimon;
import main.java.inventory.Skill_Item;
import main.java.skill.Skill;

public class Battle {

    // Mencari tahun apakah engimon "player" bisa menang melawan engimon "enemy"
    public static Boolean playerEngimonWin(Engimon player, Engimon enemy){
        // Menghitung elemenAdvantage setiap engimon
        Double playerElAdvantage = Element.advantage(player.getElement(), enemy.getElement());
        Double enemyElAdvantage = Element.advantage(enemy.getElement(), player.getElement());

        // Menghitung damage total dari skill setiap engimon
        Integer playerSkillDamage = sumOfSkillPower(player);
        Integer enemySkillDamage = sumOfSkillPower(enemy);

        Double playerPower = player.getLevel()*playerElAdvantage + playerSkillDamage.doubleValue();
        Double enemyPower = enemy.getLevel()*enemyElAdvantage + enemySkillDamage.doubleValue();

        System.out.println("(player)" + player.getSpecies()  + " VS " + enemy.getSpecies() + "(enemy)");
        // GUI ENGIMON BATTLE
        System.out.println('\n' + "Engimon is Battling"); 
        System.out.println("Player Engimon Battle Stat" + '\n' +"LV "+player.getLevel()+" Element Advantage: "+ playerElAdvantage+ " Skill Damage: " + playerSkillDamage + " Total Power: " + playerPower + '\n');
        System.out.println("Enemy Engimon Battle Stat"+ '\n' +"LV "+enemy.getLevel()+" Element Advantage: "+ enemyElAdvantage+ " Skill Damage: " + enemySkillDamage + " Total Power: " + enemyPower + '\n');

        // Menghitung sekaligus membandingkan total power menggunakan rumus yang telah disediakan
        return (playerPower >= enemyPower);
    }

    // Mendapatkan element advantage dari engimon satu ke engimon lain
    public static Double getElAdvantage(Engimon a, Engimon b){
        return Element.advantage(a.getElement(), b.getElement());
    }

     // Menghitung damage total  dari skill suatu engimon
    public static Integer sumOfSkillPower(Engimon player){
        int playerSkillDamage = 0;
        // Dapatkan vector yang berisikan skill engimon
        List<Skill> skillPlayer = player.getSkill();
    
        // Loop untuk hitung total damage nya
        for (Skill skill : skillPlayer) {
            playerSkillDamage += skill.totalDamage();
        }
        
        return playerSkillDamage;
    }

    // Mendapatkan power engimon A terhadap engimon B
    public static double totalPower(Engimon A, Engimon B){
        // Menghitung elemenAdvantage A terhadap B
        Double engimonAElAdvantage = Element.advantage(A.getElement(), B.getElement());
        // Menghitung damage total dari skill A
        Integer engimonASkillDamage = sumOfSkillPower(A);
        return A.getLevel()*engimonAElAdvantage.doubleValue() + engimonASkillDamage.doubleValue();
    }

    // Mendapatkan Skill yang dimiliki oleh musuh
    public static Skill_Item getEnemySkillItem(Engimon enemy){
        return new Skill_Item(enemy.getSkill().get(0));
    }
}
