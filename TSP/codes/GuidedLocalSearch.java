
import java.util.ArrayList;

public class GuidedLocalSearch implements TSP {

	private final TourLength tourDist;
	private final TSP localSearch;
	private final CostOfSearch costs;
	private final int maxRuns;

	public GuidedLocalSearch(TourLength tourDistance, TSP localSearch, CostOfSearch totalCosts, int maxRuns) {
		this.tourDist = tourDistance;
		this.localSearch = localSearch;
		this.costs = totalCosts;
		this.maxRuns = maxRuns;
	}
	
	public double optimizer(Point[] points, double score) {
		double bestCurrentScore = localSearch.optimizer(points, score); // initial value 
		if (bestCurrentScore < score) {
			System.out.printf("GLS length = %.4f\n", bestCurrentScore);
			return bestCurrentScore;
		}
		double improvedScore = bestCurrentScore;
		Point[] bestRoutePoints = Point.copy(points);
		for (int i = 1; i <= maxRuns;) {
			chargeForCost(points, costs);
			improvedScore = localSearch.optimizer(points, improvedScore);
			score = tourDist.length(points);			
				if (score < bestCurrentScore) { // if score is better for small input sets change best score
					bestRoutePoints = Point.copy(points);
					bestCurrentScore = score;
					int bestCrScr = (int) bestCurrentScore ;
					System.out.println("Guided Local Search iteration " + i + " length = " + bestCrScr);				
				}
			
			i++;
		}

		for (int i = 0; i < points.length; i++) {
			points[i] = bestRoutePoints[i]; // copying best route points to points object for writing it to file later on 
		}
		return bestCurrentScore;
	}

	private void chargeForCost(Point[] points, CostOfSearch totalCosts) {
		ArrayList<Point> maxUtility = new ArrayList<>();
		// finding edges with their costs which are maximizing tour length with less cost
		double maxUtil = 0;
		for (int x = 0, y = 1; x < points.length; x++, y = (y + 1) % points.length) {
			Point start = points[x], end = points[y];
			double distance = start.distance(end);
			int cost = totalCosts.getCostForCurPt(start.getId(), end.getId());
			double efficiency = distance / (cost + 1);
			if (efficiency > maxUtil) {
				maxUtility.clear();
				maxUtility.add(start);
				maxUtility.add(end);
				maxUtil = efficiency;
			} else if (efficiency == maxUtil) {
				maxUtility.add(start);
				maxUtility.add(end);
			}
		}

		// incrementing cost for corresponding edge pairs which are maximizing tour efficieny
		for (int i = 0, length = maxUtility.size(); i < length; i += 2) {
			Point startingPt = maxUtility.get(i), endingPt = maxUtility.get(i + 1);
			int startingEdge = startingPt.getId();
			int endingEdge = endingPt.getId();
			totalCosts.incCost(startingEdge, endingEdge);
			startingPt.setUsedPoints(true);
			endingPt.setUsedPoints(true);
		}
	}
}