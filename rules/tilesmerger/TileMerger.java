package rules.tilesmerger;

import dto.tiles.Tile;
import exceptions.MergeNotPossibleException;

import java.util.List;

/**
 * Created on 29.10.2015.
 *
 * @author Źmicier Dzikański
 */
public interface TileMerger {
    boolean isMerged(Tile t1, Tile t2);
    boolean isMerged(List<Tile> list);

    Tile merge(Tile t1, Tile t2) throws MergeNotPossibleException;
    Tile merge(List<Tile> list) throws MergeNotPossibleException;
}
