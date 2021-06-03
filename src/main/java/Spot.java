import java.util.ArrayList;

public class Spot {

    private int x;
    private int y;
    private boolean hasPanda;
    private int[] pandas;
    private Coordinate coord;
    public class Coordinate{
        private int x;
        private int y;
        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int hashCode(){
            int hash = 17;
            hash = 31 * hash + this.x;
            hash = 31 * hash + this.y;
            return hash;
        }
        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null) return false;
            if (this.getClass() != other.getClass()) return false;
            Spot.Coordinate that = (Spot.Coordinate) other;
            return (this.x == that.x) && (this.y == that.y);
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

    public Spot(int x, int y){
        this.x = x;
        this.y = y;
        this.coord = new Coordinate(x,y);

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

    public boolean hasPanda() {
        return hasPanda;
    }

    public void setHasPanda(boolean hasPanda) {
        this.hasPanda = hasPanda;
    }

    public Coordinate getCoordinate(){
        return this.coord;
    }

}
