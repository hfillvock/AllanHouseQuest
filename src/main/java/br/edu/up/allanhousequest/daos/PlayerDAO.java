package br.edu.up.allanhousequest.daos;

import java.util.List;

import br.edu.up.allanhousequest.models.Player;

public interface PlayerDAO {
    void savePlayer(Player player);
    Player loadPlayer(int id);
    List<Player> loadAllPlayers();
}
