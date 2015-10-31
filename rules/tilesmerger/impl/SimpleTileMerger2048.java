package rules.tilesmerger.impl;

import dto.tiles.IntegerTile;
import dto.tiles.Tile;
import exceptions.MergeNotPossibleException;
import rules.tilesmerger.TileMerger;

import java.util.List;

/**
 * Created on 29.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class SimpleTileMerger2048 implements TileMerger {
    @Override
    public boolean isMerged(Tile t1, Tile t2) {
        return t1.getValue().equals(t2.getValue());
    }

    @Override
    public boolean isMerged(List<Tile> list) {
        return list.size() == 2 && isMerged(list.get(0), list.get(1));
    }

    @Override
    public Tile merge(Tile t1, Tile t2) throws MergeNotPossibleException {
        if(!(t1 instanceof IntegerTile) || !(t2 instanceof IntegerTile)) throw new MergeNotPossibleException("Only integer tiles are expected");
        else {
            IntegerTile i1 = (IntegerTile)t1;
            IntegerTile i2 = (IntegerTile)t2;
            return new IntegerTile(i1.getValue()+i2.getValue());
        }
    }

    @Override
    public Tile merge(List<Tile> list) throws MergeNotPossibleException {
        if(list == null || list.size() < 2) throw new MergeNotPossibleException("Not enough tiles for merging");
        else return merge(list.get(0), list.get(1));
    }
}
