package br.edu.up.allanhousequest.factories;

import br.edu.up.allanhousequest.daos.*;

public class DAOFactory {
    public static PlayerDAO getPlayerDAO() {
        return new FilePlayerDAO();
    }

    public static MonsterDAO getMonsterDAO() {
        return new FileMonsterDAO();
    }

    public static ItemDAO getItemDAO() {
        return new FileItemDAO();
    }
}

