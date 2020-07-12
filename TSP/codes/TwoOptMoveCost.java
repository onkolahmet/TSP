public class TwoOptMoveCost implements MoveCost<Point> {

  public double CostForMove(Point a, Point b, Point c, Point d,
                         int indexA, int indexB, int indexC, int indexD,
                         Point[] tour) {

    //  edges that we have (ab) (cd),  edges that we may use(ac) (bd)
    double aTob = a.distanceBetweenPts(b), cTod = c.distanceBetweenPts(d);
    double aToc = a.distanceBetweenPts(c), bTod = b.distanceBetweenPts(d);

    // if both of the edges are greater , then there will be no gain on cost.
    if(aTob < aToc && cTod < bTod) return 1;

    // if there is a gain on costs then calculate it
    return (Maths.sqrt(aToc) + Maths.sqrt(bTod)) -
           (Maths.sqrt(aTob) + Maths.sqrt(cTod));
  }

}
