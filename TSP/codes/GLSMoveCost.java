
public class GLSMoveCost implements MoveCost<Point> {

  private final CostOfSearch costs;
  private double frequency;

  // calculating edge moves between guided edge pairs for guided local search steps
  public GLSMoveCost(final CostOfSearch cost, double frequency) {
    this.costs = cost;
    this.frequency = frequency;
  }

  private double getCost(Point start, Point end) {
    return costs.getCostForCurPt(start.getId(), end.getId());
  }

  public double CostForMove(Point a, Point b, Point c, Point d,
                         int indexA, int indexB, int indexC, int indexD,
                         Point[] tour) {
	// finding corresponding cost value when moving edge pairs to optimize the tour length
    double aTob = a.distanceBetweenPts(b), cTod = c.distanceBetweenPts(d); // current edge pairs
    double aToc = a.distanceBetweenPts(c), bTod = b.distanceBetweenPts(d); // new edge pairs

    if(aTob < aToc && cTod < bTod) return 1;

    double distance_ab = Maths.sqrt(aTob), distance_cd = Maths.sqrt(cTod);
    double distance_ac = Maths.sqrt(aToc), distance_bd = Maths.sqrt(bTod);
    double difference1 = (distance_ac + distance_bd) - (distance_ab + distance_cd);

    double cost_ab = getCost(a, b), cost_cd = getCost(c, d);
    double cost_ac = getCost(a, c), cost_bd = getCost(b, d);
    double current_cost = cost_ab + cost_cd;
    double new_cost = cost_ac + cost_bd;
    double difference2 = frequency * (new_cost - current_cost);

    return difference1 + difference2;
  }
}
