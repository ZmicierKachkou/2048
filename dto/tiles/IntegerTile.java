package dto.tiles;

/**
 * Created on 03.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class IntegerTile extends Tile {
    private Integer value = 1;

    public IntegerTile() {
    }

    public IntegerTile(Integer value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if(value instanceof Integer) this.value = (Integer)value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerTile that = (IntegerTile) o;

        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
