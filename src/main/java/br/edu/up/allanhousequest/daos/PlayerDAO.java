package br.edu.up.allanhousequest.daos;

import java.util.List;

import br.edu.up.allanhousequest.models.Player;

public interface PlayerDAO {
    void savePlayers(List<Player> players);
    List<Player> loadPlayers();
}
