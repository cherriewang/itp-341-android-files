/* EDIT THIS FILE */
// Name: Cherrie Wang
// Student ID: 5322184701

package itp341.javareview.superhero;

public class HeroBattle {
    public String play(){
        String allText;
        // create two superhero objects
        Superhero hero1 = new Superhero("Batman");
        Superhero hero2 = new Superhero("Superman");
        // generate inital stats
        allText = "------ INITIAL HERO STATS ------"+ System.lineSeparator()
                + hero1.getHeroStats() + System.lineSeparator()
                + hero2.getHeroStats() + System.lineSeparator();
        // players fight, while they are not injured
        allText = allText+"------ Begin Battle! ------"+System.lineSeparator();
        int roundCounter = 0;
        while(!hero1.isInjured() && !hero2.isInjured()) {
            roundCounter++;
            allText += System.lineSeparator() + "ROUND "+roundCounter+System.lineSeparator();
            // hero one attacks
            allText = allText+hero1.getName()+" attacks! ";
            hero2.loseHealthPoints(hero1.getAttackValue());

            // hero two attacks
            allText = allText+hero2.getName()+" attacks!"+System.lineSeparator();
            hero1.loseHealthPoints(hero2.getAttackValue());

            // display stats for both
            allText += "--- ROUND "+roundCounter+" STATS ---"+System.lineSeparator();
            allText += hero1.getHeroStats()+System.lineSeparator();
            allText += hero2.getHeroStats()+System.lineSeparator();
        }
        allText+= "------ Battle Complete ------" +System.lineSeparator();
        if(hero1.isInjured()&&hero2.isInjured()){
            allText+= "TIE - BOTH INJURED!";
        }
        else if(hero1.isInjured()){
            allText+="WINNER: "+hero2.getName()+" ";
            allText+="LOSER: "+hero1.getName();
        } else {
            allText+="WINNER: "+hero1.getName()+" ";
            allText+="LOSER: "+hero2.getName();
        }

        // returns all text generated in battle
        return allText;
    }

}
