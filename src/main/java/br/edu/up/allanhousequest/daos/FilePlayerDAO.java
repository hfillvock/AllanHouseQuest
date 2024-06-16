package br.edu.up.allanhousequest.daos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.allanhousequest.models.Player;

public class FilePlayerDAO implements PlayerDAO {
    private static final String FILE_PATH = "players.dat";

    @Override
    public void savePlayers(List<Player> players) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> loadPlayers() {
        List<Player> players = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            players = (List<Player>) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return players;
    }
}
