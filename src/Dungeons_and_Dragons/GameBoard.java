package Dungeons_and_Dragons;
import Units.Enemies.Enemy;
import GameTiles.Empty;
import Units.Unit;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;
    // length od the board
   // private Char[][] board= new Char[][];

    public GameBoard(Tile[][] board){
        tiles = new ArrayList<>();
        for(Tile[] line : board){
            tiles.addAll(Arrays.asList(line));
        }
    }

    public static void reomve(Enemy enemy) {
    }

    public Tile get(int x, int y) throws ExecutionControl.NotImplementedException {
        for(Tile t : tiles){
            if (t.getPosition().equals(Position.at(x, y))){
                return t;
            }
        }
        throw new RuntimeException("No tile");
    }

    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty(p));
    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        // TODO: Implement me
        return null;
    }


}