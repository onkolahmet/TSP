
// Fast Local Search, 2-Opt
public class FastLocalSearch implements TSP {

	private final MoveCost costFor2optMove;

	public FastLocalSearch() {
		this(new TwoOptMoveCost());
	}

	public FastLocalSearch(MoveCost cost) {
		this.costFor2optMove = cost;
	}
	 // this implementation currently reverses whatever subtour does not wrap around	 
	private void reverseOrder(final Point[] curPt, final int starter, final int endPoint) {
		for (int i = starter, j = endPoint; i < j; i++, j--) {
			final Point tempPtr = curPt[i];
			curPt[i] = curPt[j];
			curPt[j] = tempPtr;
		}
	}

	/**
	 *surrounds around.
	 */
	private int round(final int i, final int max) {
		return (max + i) % max;
	}

	/**
	 * set  bits as used; edges ab, cd.
	 */
	private void pointsToUse(final Point x, final Point y, final Point z, final Point t) {
		x.setUsedPoints(true);
		y.setUsedPoints(true);
		z.setUsedPoints(true);
		t.setUsedPoints(true);
	}

	
	 //  finding a move from the current point. 
	 // search for a 2-opt move that will result in a better tour. 
	private double findMove(final int currentVal, final Point currentPt, final Point[] points, final int numOfCities) {

		final MoveCost currentCost = this.costFor2optMove;

		// previous and next city index and point object.
		final int prevInd = round(currentVal - 1, numOfCities);
		final int nextInd = round(currentVal + 1, numOfCities);
		final Point prevPoint = points[prevInd];
		final Point nextPoint = points[nextInd];

		for (int x = round(currentVal + 2, numOfCities), y = round(currentVal + 3,
				numOfCities); y != currentVal; x = y, y = round(y + 1, numOfCities)) {

			final Point c = points[x];
			final Point d = points[y];

			// calculate cost for previous edge
			final double costVal1 = currentCost.CostForMove(prevPoint, currentPt, c, d, prevInd, currentVal, x, y,
					points);
			if (costVal1 < 0) {
				pointsToUse(prevPoint, currentPt, c, d);
				reverseOrder(points, Math.min(prevInd, x) + 1, Math.max(prevInd, x));
				return costVal1;
			}
			
			// calculate cost for next edge		
			final double costVal2 = currentCost.CostForMove(currentPt, nextPoint, c, d, currentVal, nextInd, x, y,
					points);
			if (costVal2 < 0) {
				pointsToUse(currentPt, nextPoint, c, d);
				reverseOrder(points, Math.min(currentVal, x) + 1, Math.max(currentVal, x));
				return costVal2;
			}

		}
		return 0d;
	}

	/**
	 * optimizing the tour.
	 */
	public double optimizer(final Point[] points, double score) {

		// total number of cities in the tour
		final int numofCities = points.length;	
		int visitedCities = 0, currentCity = 0;
		while (visitedCities < numofCities) {
			final Point currentPt = points[currentCity];
			if (currentPt.isUsed()) {
				// from the current point, try to find a move.
				final double modifiedVersion = findMove(currentCity, currentPt, points, numofCities);

				if (modifiedVersion < 0) {
					currentCity = round(currentCity - 1, numofCities);
					visitedCities = 0;
					score += modifiedVersion;
					continue;
				}
				currentPt.setUsedPoints(false);
			}

			currentCity = round(currentCity + 1, numofCities);
			visitedCities++;
		}
		return score;
	}

}