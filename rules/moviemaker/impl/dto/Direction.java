package rules.moviemaker.impl.dto;

import dto.Position;
import dto.movies.FirstPlayerMovie;
import dto.tiles.Tile;

/**
 * Created on 03.04.2016.
 *
 * @author Źmicier Dzikański
 */
public class Direction {
    private FirstPlayerMovie direction;
    private Position position;
    public Direction(Position position, FirstPlayerMovie direction) {
        this.position = position;
        this.direction = direction;
    }

    private int getX(int x, int y) {
        if(direction == FirstPlayerMovie.UP) return y;
        else if(direction == FirstPlayerMovie.DOWN) return position.getSize()-y-1;
        else if(direction == FirstPlayerMovie.LEFT) return x;
        else return position.getSize()-x-1;
    }

    private int getY(int x, int y) {
        if(direction == FirstPlayerMovie.UP) return x;
        else if(direction == FirstPlayerMovie.DOWN) return position.getSize()-x-1;
        else if(direction == FirstPlayerMovie.LEFT) return y;
        else return position.getSize()-y-1;
    }

    public Tile getTile(int x, int y) {
        return position.getTile(getX(x, y), getY(x, y));
    }

    public void setTile(Tile t, int x, int y) {
        position.setTile(getX(x, y), getY(x, y), t);
    }
}
