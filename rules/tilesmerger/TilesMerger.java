package rules.tilesmerger;

import dto.tiles.Tile;
import exceptions.MergeNotPossibleException;
import rules.pointscounter.PointsCounter;

import java.util.List;

/**
 * Created on 29.10.2015.
 *
 * @author Źmicier Dzikański
 * this manager checks if tiles should be merged
 */
public interface TilesMerger {

    PointsCounter getPointsCounter();

    void setPointsCounter(PointsCounter pointsCounter);

    /**
     * checks if tiles should be merged
     * @param t1 is a first tile
     * @param t2 is a second tile
     * @return true if they should be merger and false otherwise
     */
    boolean isMerged(Tile t1, Tile t2);

    /**
     * for special versions of the game. Checks if several tiles should be merged in one
     * @param list is a list of tiles
     * @return true if all tiles should be merged and false otherwise
     */
    boolean isMerged(List<Tile> list);

    /**
     * calculate points for merge
     * @param t1 is a first tile
     * @param t2 is a second tile
     * @return points
     * @throws MergeNotPossibleException if tiles can't be merged
     */
    int pointsForMerge(Tile t1, Tile t2) throws MergeNotPossibleException;

    /**
     * for special versions of the game. Calculate points for merge
     * @param list is a list of tiles
     * @return points
     * @throws MergeNotPossibleException if tiles can't be merged
     */
    int pointsForMerge(List<Tile> list) throws MergeNotPossibleException;

    /**
     * merges two tiles
     * @param t1 is a first tile
     * @param t2 is a second tile
     * @return new tile
     * @throws MergeNotPossibleException if tiles can't be merged
     */
    Tile merge(Tile t1, Tile t2) throws MergeNotPossibleException;

    /**
     * for special versions of the game. Merges several tiles in one
     * @param list is a list of tiles
     * @return new tile
     * @throws MergeNotPossibleException if tiles can't be merged
     */
    Tile merge(List<Tile> list) throws MergeNotPossibleException;
}
