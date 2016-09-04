/* EDIT THIS FILE */
// Name: Cherrie Wang
// Student ID: 5322184701

package itp341.javareview.superhero;

public class Superhero {
    // public static variables
    public final static int MAX_HEALTHPOINTS = 100;
    public final static int MAX_ATTACKVALUE = 20;
    public final static int MIN_HEALTHPOINTS = 0;
    public final static int MIN_ATTACKVALUE = 5;

    // private variables
    private String name;
    private int healthPoints;
    private int attackValue;

    // constructor
    public Superhero(String myName) {
        name = myName;
        healthPoints = MAX_HEALTHPOINTS;
        attackValue = MIN_ATTACKVALUE + (int)(Math.random() * ((MAX_ATTACKVALUE - MIN_ATTACKVALUE) + 1));
    }
    // mutators
    public void setName(String newName) {
        name = newName;
    }
    public void setHealthPoints(int newHealth) {
        healthPoints = newHealth;
    }
    public void setAttackValue(int newAttackValue) {
        attackValue = newAttackValue;
    }
    // accessors
    public String getName(){
        return name;
    }
    public int getHealthPoints(){
        return healthPoints;
    }
    public int getAttackValue(){
        return attackValue;
    }
    public String getHeroStats() {
        String allStats;
        allStats = "Name: "+name+System.lineSeparator()
                +"Health Points: "+healthPoints+ System.lineSeparator()
                +"Attack Value: "+attackValue;
        return allStats;
    }
    public boolean isInjured(){
        if (healthPoints <= MIN_HEALTHPOINTS){
            return true;
        } else {
            return false;
        }
    }
    public void loseHealthPoints(int damage){
        healthPoints = healthPoints-damage;
    }

}




