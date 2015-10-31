package dto.tiles;

/**
 * Created on 03.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class StringTile extends Tile {
    private String value = "x2";

    public StringTile() {
    }

    public StringTile(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if(value instanceof String) this.value = (String)value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringTile that = (StringTile) o;

        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "(" + value + ")";
    }
}

