public interface MoveCost<E extends Point> {
// interface for calling overridden CostForMove methods for Fast and Guided local searches
  double CostForMove(E a, E b, E c, E d,
                  int x, int y, int z, int t,
                  E[] tour);
}
