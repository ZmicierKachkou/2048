package dto.movies;

import dto.Coords;
import dto.tiles.Tile;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class SecondPlayerMovie extends Coords{
    private Tile tile;

    public SecondPlayerMovie() {
    }

    public SecondPlayerMovie(Integer x, Integer y, Tile tile) {
        super(x, y);
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
}
