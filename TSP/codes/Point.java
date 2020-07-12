public class Point {
// point class for optimizing code efficiency more flexible with using point classes' object variables.
  private int id;
  private  int x;
  private  int y;

  private boolean used;
  public Point() {	  
  }

  public Point(int id, int x, int y) {
    this(id, x, y, true);
  }

  public Point(int id, int x, int y, boolean isused) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.used = isused;
  }

  public int getId() {
    return id;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
  public void setId(int id) {
	    this.id = id;
	  }
  public void setX(int x) {
	    this.x = x;
	  }
  public void setY(int y) {
	    this.y = y;
	  }
  
  public double distance(final Point endPoint) {
    return Math.round(Math.sqrt(distanceBetweenPts(endPoint)));
  }

  public double distanceBetweenPts(final Point endpoint) {
    final double distX = this.x - endpoint.x;
    final double distY = this.y - endpoint.y;
    return (distX * distX) + (distY * distY);
  }

  public boolean isUsed() {
    return used;
  }

  public void setUsedPoints(boolean used) {
    this.used = used;
  }

  public Point copy() {
    return new Point(id, x, y, used);
  }

  public String toString() {
    return id + " " + x + " " + y;
  }

  public static Point[] copy(Point[] pointArray) {
    final Point[] pointsarr = new Point[pointArray.length];
    for(int x = 0; x < pointArray.length; x++) {
    	pointsarr[x] = pointArray[x].copy();
    }
    return pointsarr;
  }
}