package exercise;

// BEGIN
public interface Home extends Comparable<Home>{
    public double getArea();

    @Override
    public default int compareTo(Home another){
        if(another.getArea() > getArea()){
            return -1;
        } else if (another.getArea() < getArea()) {
            return 1;
        }
        return 0;
    }
}
// END
