public class DefaultLength implements TourLength<Point> { 
	  // calculating default length,which is the order of input file, so that i can verify my solutions
	  // same usage of tsp.verifier but i don't waste my time for switching to python
	  @Override
	  public double length(final Point[] points) {
	    final int lengthVal = points.length;
	    double totalLength = points[lengthVal - 1].distance(points[0]);
	    totalLength += length(points, 0, lengthVal - 1);
	    return totalLength;
	  }

	  @Override
	  public double length(Point[] points, int startPt, int endPt) {
	    double totalLength = 0d;
	    for(int i = startPt + 1; i <= endPt; i++) {
	      final Point previousPt = points[i - 1], currentPt = points[i];
	      totalLength += previousPt.distance(currentPt);
	    }
	    return totalLength;
	  }
	}
