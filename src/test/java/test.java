import br.edu.up.allanhousequest.controllers.RPGController;
import br.edu.up.allanhousequest.factories.RPGFactory;

public class test {
    public static void main(String[] args) {
        RPGController RPG = RPGFactory.getRPG();

        RPG.loadGame();

        RPG.startGame();

        RPG.saveGame();
    }
}
