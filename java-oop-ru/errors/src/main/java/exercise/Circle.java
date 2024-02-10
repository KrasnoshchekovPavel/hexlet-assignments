package exercise;

// BEGIN
public class Circle {
    private final int radius;
    private final Point point;

    public Circle(Point point, int radius) {
        this.radius = radius;
        this.point = point;
    }

    public int getRadius(){
        return radius;
    }

    public double getSquare() throws NegativeRadiusException{
        if(radius < 0){
            throw new NegativeRadiusException("");
        }
        return Math.pow(radius, 2) * Math.PI;
    }
}
// END
