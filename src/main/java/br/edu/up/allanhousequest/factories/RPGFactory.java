package br.edu.up.allanhousequest.factories;

import br.edu.up.allanhousequest.controllers.RPGController;
import br.edu.up.allanhousequest.models.RPGModel;
import br.edu.up.allanhousequest.views.RPGView;

public class RPGFactory {
    public static RPGController getRPG() {
        RPGModel rpgModel = new RPGModel();
        RPGView rpgView = new RPGView();
        return new RPGController(rpgModel, rpgView);
    }
}
