package rules.pointscounter;

import dto.tiles.Tile;
import exceptions.MergeNotPossibleException;

import java.util.List;

/**
 * Created on 28.02.2016.
 *
 * @author Źmicier Dzikański
 */
public interface PointsCounter {
    /**
     * calculate points for merge
     * @param t1 is a first tile
     * @param t2 is a second tile
     * @return points
     * @throws exceptions.MergeNotPossibleException if tiles can't be merged
     */
    int pointsForMerge(Tile t1, Tile t2) throws MergeNotPossibleException;

    /**
     * for special versions of the game. Calculate points for merge
     * @param list is a list of tiles
     * @return points
     * @throws MergeNotPossibleException if tiles can't be merged
     */
    int pointsForMerge(List<Tile> list) throws MergeNotPossibleException;
}
