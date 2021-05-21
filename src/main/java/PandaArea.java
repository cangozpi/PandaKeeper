public class PandaArea {

    private Panda centerPanda;
    private int panda_population;
    private int height = (2 * (centerPanda.getS())) + 1;
    private int width = (2 * (centerPanda.getS())) + 1;
    private int center_x;
    private int center_y;
    private boolean hasAnotherPanda;


    public int bambooNumber() {
    return ((height*width) - panda_population);
        };


    public boolean hasPanda() {
        return hasAnotherPanda;
    }

    public void setHasPanda(boolean hasPanda) {
        this.hasAnotherPanda = hasPanda;
    }

    public Panda getCenterPanda() {
        return centerPanda;
    }

    public void setCenterPanda(Panda centerPanda) {
        this.centerPanda = centerPanda;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getCenter_x() {
        return center_x;
    }

    public void setCenter_x(int center_x) {
        this.center_x = center_x;
    }

    public int getCenter_y() {
        return center_y;
    }

    public void setCenter_y(int center_y) {
        this.center_y = center_y;
    }


}
