package br.edu.up.allanhousequest.daos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.allanhousequest.models.*;

public class FilePlayerDAO implements PlayerDAO {
    private static final String FILE_PATH = "players.dat";

    @Override
    public void savePlayer(Player player) {
        List<Player> players = loadAllPlayers();

        players.removeIf(p -> p.getId() == player.getId());
        players.add(player);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Player loadPlayer(int id) {
        List<Player> players = loadAllPlayers();
        return players.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Player> loadAllPlayers() {
        List<Player> players = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            players = (List<Player>) in.readObject();
        } catch (FileNotFoundException e) {
            // File not found, return empty list
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return players;
    }
}
