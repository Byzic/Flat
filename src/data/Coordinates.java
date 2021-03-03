package data;

import java.util.Objects;
/**
 * X-Y координаты
 */
public class Coordinates {
    private float x; //Значение поля должно быть больше -107
    private float y;

    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X координата
     */
    public double getX() {
        return x;
    }

    /**
     * @return Y координата
     */
    public Float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return (x == coordinatesObj.getX()) && (y==coordinatesObj.getY());
        }
        return false;
    }
}
