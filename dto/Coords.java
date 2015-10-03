package dto;

/**
 * Created on 01.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class Coords {
    private int x = 0;
    private int y = 0;

    public Coords() {
    }

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
