public interface TSP {
  // overriden TSP interface for calling optimizer method in Fast and Guided Local Search algorithms if necessary.
  double optimizer(Point[] points, double length);
}
