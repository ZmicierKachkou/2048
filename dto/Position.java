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
    private Tile[][] table;

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

    public void setTile(int x, int y, Tile tile) {
        if(x>=size||y>=size) return;
        table[x][y] = tile;
    }

    public Tile getTile(int x, int y) {
        if(x>=size||y>=size) return null;
        return table[x][y];
    }

    @Override
    public Position clone() {
        Position newPosition = new Position(size);
        for(int x=0; x<size; x++) {
            for(int y=0; y<size; y++) {
                newPosition.table[x][y] = this.table[x][y];
            }
        }
        return newPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (size != position.size) return false;
        for(int x=0; x<size; x++) {
            for(int y=0; y<size; y++) {
                if(this.table[x][y] == null) {
                    if(position.table[x][y] != null) return false;
                }
                else if(!this.table[x][y].equals(position.table[x][y])) return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                if(table[x][y] == null) builder.append(" - \t");
                else builder.append(" ").append(table[x][y].getValue()).append(" \t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
