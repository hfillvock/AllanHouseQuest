import br.edu.up.allanhousequest.controllers.RPGController;
import br.edu.up.allanhousequest.models.RPGModel;
import br.edu.up.allanhousequest.utils.Utils;
import br.edu.up.allanhousequest.views.RPGView;

public class test {
    public static void main(String[] args) {
        
        RPGModel rpgModel = new RPGModel();
        RPGView rpgView = new RPGView();
        RPGController rpgController = new RPGController(rpgModel, rpgView);

        rpgController.startGame();

        rpgView.listPlayers(rpgModel);

        Utils.printDivider();

        System.out.println("\n\n\nainda não tá salvando!!!!!!! \n\n\n");
    }
}
