import com.cw.codetoaction.CertainFighter;
import com.cw.codetoaction.GameEnvironment;
import com.cw.codetoaction.Bohdan;
import com.cw.models.FighterA;
public class BohdanPavuk extends Bohdan {
    public void execute(CertainFighter self, GameEnvironment env) {
        System.out.println("Game started at: " + env.getStartDatetime());;
        System.out.println("players: " + env.getPlayersCount());;
        System.out.println("My name is: " + self.getName());;
        System.out.println("My level is: " + self.getLvl());;
        if (self.getLvl() >= 5) ;
            System.out.println("I am awesome");;
    }
}
