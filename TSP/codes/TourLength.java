public interface TourLength<E extends Point> {
  // overriden tourlength interface with length methods for Fast and Guided Local Searches algorithms
  double length(E[] points);
  double length(E[] points, int startPt, int endPt);
}