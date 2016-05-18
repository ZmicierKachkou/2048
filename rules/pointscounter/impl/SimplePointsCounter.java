package rules.pointscounter.impl;

import dto.tiles.IntegerTile;
import dto.tiles.Tile;
import exceptions.MergeNotPossibleException;
import rules.pointscounter.PointsCounter;

import java.util.List;

/**
 * Created on 28.02.2016.
 *
 * @author Źmicier Dzikański
 */
public class SimplePointsCounter implements PointsCounter {
    @Override
    public int pointsForMerge(Tile t1, Tile t2) throws MergeNotPossibleException {
        if(!(t1 instanceof IntegerTile) || !(t2 instanceof IntegerTile)) throw new MergeNotPossibleException("Only integer tiles are expected");
        else {
            IntegerTile i1 = (IntegerTile)t1;
            IntegerTile i2 = (IntegerTile)t2;
            return i1.getValue()+i2.getValue();
        }
    }

    @Override
    public int pointsForMerge(List<Tile> list) throws MergeNotPossibleException {
        if(list == null || list.size() < 2) throw new MergeNotPossibleException("Not enough tiles for merging");
        else return pointsForMerge(list.get(0), list.get(1));
    }
}
