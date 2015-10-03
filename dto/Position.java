package dto;

import dto.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class Position {
    private int size = 4;
    Tile[][] table;

    public Position() {
        table = new Tile[size][size];
    }

    public Position(int size) {
        this.size = size;
        table = new Tile[size][size];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        table = new Tile[size][size];
    }

    public Tile[][] getTable() {
        return table;
    }

    public void setTable(Tile[][] table) {
        this.table = table;
        this.size = table.length;
    }

    public Tile getTile(int x, int y) {
        if(x>=size||y>=size) return null;
        return table[x][y];
    }
}
