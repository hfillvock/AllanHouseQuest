import br.edu.up.allanhousequest.controllers.RPGController;
import br.edu.up.allanhousequest.factories.RPGFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class test {
    protected static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        logger.info("hello world");
        logger.always();

        RPGController RPG = RPGFactory.getRPG();

        RPG.loadGame();

        RPG.startGame();

        RPG.saveGame();
    }
}
