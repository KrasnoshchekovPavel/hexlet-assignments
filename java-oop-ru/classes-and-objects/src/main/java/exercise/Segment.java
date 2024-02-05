package exercise;

// BEGIN
public class Segment {
    private Point BeginPoint;
    private Point EndPoint;
    private Point MidPoint;

    public Segment(Point BeginPoint, Point EndPoint) {
        this.BeginPoint = BeginPoint;
        this.EndPoint = EndPoint;
        MidPoint = new Point((BeginPoint.getX() + EndPoint.getX())/2, (BeginPoint.getY() + EndPoint.getY())/2);

    }

    public Point getMidPoint(){
        return MidPoint;
    }

    public Point getBeginPoint() {
        return BeginPoint;
    }

    public Point getEndPoint() {
        return EndPoint;
    }
}
// END
