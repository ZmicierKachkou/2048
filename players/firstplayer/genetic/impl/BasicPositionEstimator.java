package players.firstplayer.genetic.impl;

import dto.Coords;
import dto.Position;
import dto.tiles.IntegerTile;
import dto.tiles.Tile;
import players.firstplayer.genetic.PositionEstimator;

import java.util.*;

/**
 * Created on 14.11.2015.
 *
 * @author Źmicier Dzikański
 */
public class BasicPositionEstimator implements PositionEstimator {
    private TreeMap<Tile, List<Coords>> map;
    private int tiles = 0;
    private int chains[][];
    private float verticalPairs, horisontalPairs;
    private float weightedVerticalPairs, weightedHorisontalPairs;
    private Position p;

    float[] coeffs = {0.012190152f, 0.22678046f, 0.02301026f, 0.0066818492f, 0.003412304f, 0.0359393f, 0.028191911f, 0.010168288f, 4.9580354E-4f, 0.036333844f, 0.23074082f, 0.036072716f, 0.0011799715f, 0.001978657f, 7.993542E-4f, 0.02928545f, 0.020526076f, 0.05104921f, 0.031203484f};
    boolean array = false;

    public float[] getCoeffs() {
        return coeffs;
    }

    public void setCoeffs(float[] coeffs) {
        this.coeffs = coeffs;
    }

    private void generateParams(Position p) {
        tiles = 0;
        verticalPairs = -1;
        horisontalPairs = -1;
        weightedHorisontalPairs = -1;
        weightedVerticalPairs = -1;
        this.p = p;
        map = new TreeMap<Tile, List<Coords>>(new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                if(!(o1 instanceof IntegerTile ) ) {
                    if (!(o2 instanceof IntegerTile)) return 0;
                    else return 1;
                }
                else if (!(o2 instanceof IntegerTile )) return -1;
                IntegerTile io1 = (IntegerTile) o1;
                IntegerTile io2 = (IntegerTile) o2;
                return io1.getValue() - io2.getValue();
            }
        });
        for(int x = 0; x < p.getSize(); x++) {
            for(int y = 0; y < p.getSize(); y++) {
                if(p.getTile(x,y) != null) {
                    tiles++;
                    if(map.containsKey(p.getTile(x,y))) {
                        map.get(p.getTile(x,y)).add(new Coords(x,y));
                    }
                    else {
                        List<Coords> l = new ArrayList<Coords>();
                        l.add(new Coords(x,y));
                        map.put(p.getTile(x,y), l);
                    }
                }
            }
        }
        chains = new int[p.getSize()][p.getSize()];
        calculateLongestChains();
    }

    private float numberOfEmptyCells() {
        return p.getSize()*p.getSize() - tiles;
    }

    private float numberOfHorizontalPairs() {
        if(horisontalPairs == -1) {
            horisontalPairs = 0;
            Tile lastTile = null;
            for (int x = 0; x < p.getSize(); x++) {
                for (int y = 0; y < p.getSize(); y++) {
                    if (p.getTile(x, y) != null) {
                        if (p.getTile(x, y).equals(lastTile)) {
                            horisontalPairs++;
                            lastTile = null;
                        } else lastTile = p.getTile(x, y);
                    }
                }
                lastTile = null;
            }
        }
        return horisontalPairs;
    }

    private float numberOfVerticalPairs() {
        if(verticalPairs == -1) {
            verticalPairs = 0;
            Tile lastTile = null;
            for (int y = 0; y < p.getSize(); y++) {
                for (int x = 0; x < p.getSize(); x++) {
                    if (p.getTile(x, y) != null) {
                        if (p.getTile(x, y).equals(lastTile)) {
                            verticalPairs++;
                            lastTile = null;
                        } else lastTile = p.getTile(x, y);
                    }
                }
                lastTile = null;
            }
        }
        return verticalPairs;
    }

    private float maxNumberOfPairsInOneDirection() {
        return Math.max(numberOfHorizontalPairs(), numberOfVerticalPairs());
    }

    private float weightedNumberOfHorizontalPairs() {
        if(weightedHorisontalPairs == -1) {
            weightedHorisontalPairs = 0;
            Tile lastTile = null;
            for (int x = 0; x < p.getSize(); x++) {
                for (int y = 0; y < p.getSize(); y++) {
                    if (p.getTile(x, y) != null) {
                        if (p.getTile(x, y).equals(lastTile)) {
                            weightedHorisontalPairs += Math.log((Integer)p.getTile(x,y).getValue());
                            lastTile = null;
                        } else lastTile = p.getTile(x, y);
                    }
                }
                lastTile = null;
            }
        }
        return horisontalPairs;
    }

    private float weightedNumberOfVerticalPairs() {
        if(weightedVerticalPairs == -1) {
            weightedVerticalPairs = 0;
            Tile lastTile = null;
            for (int y = 0; y < p.getSize(); y++) {
                for (int x = 0; x < p.getSize(); x++) {
                    if (p.getTile(x, y) != null) {
                        if (p.getTile(x, y).equals(lastTile)) {
                            weightedVerticalPairs += Math.log((Integer)p.getTile(x,y).getValue());
                            lastTile = null;
                        } else lastTile = p.getTile(x, y);
                    }
                }
                lastTile = null;
            }
        }
        return verticalPairs;
    }

    private float weightedMaxNumberOfPairsInOneDirection() {
        return Math.max(weightedNumberOfHorizontalPairs(), weightedNumberOfVerticalPairs());
    }

    private float numberOfPairs() {
        return numberOfHorizontalPairs() + numberOfVerticalPairs();
    }

    private boolean isCorrectСontinuation(Tile thisTile, Tile thatTile) {
        if(thatTile == null || thisTile == null) return false;
        if(!(thatTile instanceof IntegerTile) || !(thisTile instanceof IntegerTile)) return false;
        IntegerTile iThisTile = (IntegerTile) thisTile;
        IntegerTile iThatTile = (IntegerTile) thatTile;
        return iThisTile.getValue() > iThatTile.getValue();
    }

    private int longestChainForTile(int x, int y, int[][] chains) {
        if(chains[x][y]!=0) return chains[x][y];
        if(p.getTile(x, y) == null) return 0;
        int chain = 0;
        if(x>0 && isCorrectСontinuation(p.getTile(x, y), p.getTile(x-1, y))) {
            int temp = longestChainForTile(x-1, y, chains);
            if(temp > chain) chain = temp;
        }
        if(x<p.getSize()-1 && isCorrectСontinuation(p.getTile(x, y), p.getTile(x+1, y))) {
            int temp = longestChainForTile(x+1, y, chains);
            if(temp > chain) chain = temp;
        }
        if(y>0 && isCorrectСontinuation(p.getTile(x, y), p.getTile(x, y-1))) {
            int temp = longestChainForTile(x, y-1, chains);
            if(temp > chain) chain = temp;
        }
        if(y<p.getSize()-1 && isCorrectСontinuation(p.getTile(x, y), p.getTile(x, y+1))) {
            int temp = longestChainForTile(x, y+1, chains);
            if(temp > chain) chain = temp;
        }
        chains[x][y]=chain;
        return chain;
    }

    private void calculateLongestChains() {
        int chains[][] = new int[p.getSize()][p.getSize()];
        for(int x = 0; x < p.getSize(); x++) {
            for(int y = 0; y < p.getSize(); y++) {
                chains[x][y] = longestChainForTile(x, y, chains);
            }
        }
    }

    private float longestChain() {
        int maxChain = 0;
        for(int x = 0; x < p.getSize(); x++) {
            for(int y = 0; y < p.getSize(); y++) {
                if(chains[x][y] > maxChain) maxChain = chains[x][y];
            }
        }
        return maxChain;
    }

    private float longestChainFromMaxTile() {
        int maxChain = 0;
        for(Coords coords: map.get(map.lastKey())) {
            if(chains[coords.getX()][coords.getY()] > maxChain) maxChain = chains[coords.getX()][coords.getY()];
        }
        return maxChain;
    }

    private float numberOfDifferentTiles() {
        return map.size();
    }

    private boolean isCorner(Coords coords) {
        return (coords.getX() == 0 || coords.getX() == p.getSize()-1) && (coords.getY() == 0 || coords.getY() == p.getSize()-1);
    }

    private float maxTileInCorner() {
        for(Coords coords: map.get(map.lastKey())) {
            if(isCorner(coords))
                return 1;
        }
        return 0;
    }

    private float maxTileNotInCorner() {
        for(Coords coords: map.get(map.lastKey())) {
            if(!isCorner(coords))
                return -1;
        }
        return 0;
    }

    private float numberOfMaxFragments() {
        int counter = 0;
        List<Coords> allMax = new ArrayList<Coords>();
        List<Coords> cornerMax = new LinkedList<Coords>();
        for (Coords coords : map.get(map.lastKey())) {
            allMax.add(coords);
            if (isCorner(coords)) cornerMax.add(coords);
        }
        while (!cornerMax.isEmpty()) {
            counter++;
            HashSet<Coords> fragmentMax = new HashSet<Coords>();
            fragmentMax.add(cornerMax.remove(0));
            while (!fragmentMax.isEmpty()) {
                Coords coords = fragmentMax.iterator().next();
                fragmentMax.remove(coords);
                allMax.remove(coords);
                cornerMax.remove(coords);
                Coords leftCoords = new Coords(coords.getX() - 1, coords.getY());
                if (allMax.contains(leftCoords)) {
                    fragmentMax.add(leftCoords);
                    allMax.remove(leftCoords);
                    cornerMax.remove(leftCoords);
                }
                Coords rightCoords = new Coords(coords.getX() + 1, coords.getY());
                if (allMax.contains(rightCoords)) {
                    fragmentMax.add(rightCoords);
                    allMax.remove(rightCoords);
                    cornerMax.remove(rightCoords);
                }
                Coords upCoords = new Coords(coords.getX(), coords.getY() + 1);
                if (allMax.contains(upCoords)) {
                    fragmentMax.add(upCoords);
                    allMax.remove(upCoords);
                    cornerMax.remove(upCoords);
                }
                Coords downCoords = new Coords(coords.getX(), coords.getY() - 1);
                if (allMax.contains(downCoords)) {
                    fragmentMax.add(downCoords);
                    allMax.remove(downCoords);
                    cornerMax.remove(downCoords);
                }
            }
        }
        return 1 - counter - allMax.size();
    }

    private boolean isNear(Coords t1, Coords t2) {
        if(t1.getX() == t2.getX() && Math.abs(t1.getY()-t2.getY()) == 1) return true;
        if(t1.getY() == t2.getY() && Math.abs(t1.getX()-t2.getX()) == 1) return true;
        return false;
    }

    private boolean hasWay(Coords t1, Coords t2) {
        if(t1.getY() == t2.getY()) {
            for(int i=1; i<Math.abs(t1.getX()-t2.getX()); i++)
                if(p.getTile(Math.min(t1.getX(), t2.getX())+i, t1.getY())!=null) return false;
            return true;
        }
        if(t1.getX() == t2.getX()) {
            for(int i=1; i<Math.abs(t1.getY()-t2.getY()); i++)
                if(p.getTile(t1.getX(), Math.min(t1.getY(), t2.getY())+i)!=null) return false;
            return true;
        }
        return false;
    }

    private float largeTilesAreNear() {
        Tile t = map.lastKey();
        while(t!=null) {
            if(map.get(t).size() == 1) t = map.lowerKey(t);
            else break;
        }
        if(t==null) return 1;
        else {
            int result = 0;
            for(int i=0; i<map.get(t).size(); i++) {
                for(int j=i+1; j<map.get(t).size(); j++) {
                    if(isNear(map.get(t).get(i), map.get(t).get(j))) result++;
                    if(hasWay(map.get(t).get(i), map.get(t).get(j))) result++;
                }
            }
            return result;
        }
    }

    private float rowPicks() {
        float counter = 0;
        for(int x = 0; x < p.getSize(); x++) {
            List<IntegerTile> list = new ArrayList<IntegerTile>();
            for(int y = 0; y<p.getSize(); y++)
                if(p.getTile(x, y) != null && p.getTile(x, y) instanceof IntegerTile &&
                        (list.size() == 0 || !p.getTile(x,y).equals(list.get(list.size()-1))))
                    list.add((IntegerTile)p.getTile(x,y));
                for(int i=1; i<list.size()-1; i++) {
                    if((list.get(i).getValue() - list.get(i-1).getValue()) * (list.get(i+1).getValue() - list.get(i).getValue()) < 0) counter++;
            }
        }
        return -counter;
    }

    private float columnPicks() {
        float counter = 0;
        for(int y = 0; y < p.getSize(); y++) {
            List<IntegerTile> list = new ArrayList<IntegerTile>();
            for(int x = 0; x<p.getSize(); x++)
                if(p.getTile(x, y) != null && p.getTile(x, y) instanceof IntegerTile &&
                        (list.size() == 0 || !p.getTile(x,y).equals(list.get(list.size()-1))))
                    list.add((IntegerTile)p.getTile(x,y));
            for(int i=1; i<list.size()-1; i++) {
                if((list.get(i).getValue() - list.get(i-1).getValue()) * (list.get(i+1).getValue() - list.get(i).getValue()) < 0) counter++;
            }
        }
        return -counter;
    }

    private float goodRaws() {
        float counter = 0;
        for(int x = 0; x < p.getSize(); x++) {
            List<IntegerTile> list = new ArrayList<IntegerTile>();
            for(int y = 0; y<p.getSize(); y++)
                if(p.getTile(x, y) != null && p.getTile(x, y) instanceof IntegerTile &&
                        (list.size() == 0 || !p.getTile(x,y).equals(list.get(list.size()-1))))
                    list.add((IntegerTile)p.getTile(x,y));
            boolean isGood = true;
            for(int i=1; i<list.size()-1; i++) {
                if((list.get(i).getValue() - list.get(i-1).getValue()) * (list.get(i+1).getValue() - list.get(i).getValue()) < 0) {
                    isGood = false;
                    break;
                }
            }
            if(isGood) counter++;
        }
        return counter;
    }

    private float goodColumns() {
        float counter = 0;
        for(int y = 0; y < p.getSize(); y++) {
            List<IntegerTile> list = new ArrayList<IntegerTile>();
            for(int x = 0; x<p.getSize(); x++)
                if(p.getTile(x, y) != null && p.getTile(x, y) instanceof IntegerTile &&
                        (list.size() == 0 || !p.getTile(x,y).equals(list.get(list.size()-1))))
                    list.add((IntegerTile)p.getTile(x,y));
            boolean isGood = true;
            for(int i=1; i<list.size()-1; i++) {
                if((list.get(i).getValue() - list.get(i-1).getValue()) * (list.get(i+1).getValue() - list.get(i).getValue()) < 0) {
                    isGood = false;
                    break;
                }
            }
            if(isGood) counter++;
        }
        return counter;
    }

    @Override
    public float estimatePosition(Position p) {
        generateParams(p);
        return coeffs[0]*numberOfDifferentTiles() + coeffs[1]*numberOfEmptyCells() + coeffs[2]*numberOfHorizontalPairs() +
                coeffs[3]*numberOfVerticalPairs() + coeffs[4]*maxNumberOfPairsInOneDirection() + coeffs[5]*numberOfPairs() +
                coeffs[6]*longestChain() + coeffs[7]*longestChainFromMaxTile() + coeffs[8]*maxTileInCorner() +
                coeffs[9]*numberOfMaxFragments() + coeffs[10]*maxTileNotInCorner() + coeffs[11]*largeTilesAreNear() +
                coeffs[12]*rowPicks() + coeffs[13]*columnPicks() + coeffs[14]*goodColumns() + coeffs[15]*goodRaws() +
                coeffs[16]*weightedNumberOfHorizontalPairs() + coeffs[17]*weightedNumberOfVerticalPairs() +
                coeffs[18]*weightedMaxNumberOfPairsInOneDirection();
    }
}
