package editting_algorithms;

public class Square {
	public int xmin;
    public int ymin;
    public int xmax;
    public int ymax;

    public Square(int xmin, int ymin, int xmax, int ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        System.out.println("square (xmin: " + xmin + ", ymin: " + ymin + "; xmax: " + xmax + ", ymax: " + ymax + ")");
    }


}
