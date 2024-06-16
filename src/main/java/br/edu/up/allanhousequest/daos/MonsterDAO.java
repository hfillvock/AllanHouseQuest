package br.edu.up.allanhousequest.daos;

import java.util.List;

import br.edu.up.allanhousequest.models.Monster;

public interface MonsterDAO {
    void saveMonsters(List<Monster> monsters);
    List<Monster> loadMonsters();
}
