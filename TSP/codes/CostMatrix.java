public class CostMatrix implements CostOfSearch {

	private final int[] totalCosts;
	private final int numOfCities;
	// calculating cost for searching algorithms as a matrix
	public CostMatrix(int cities) {
		this.totalCosts = new int[cities * (cities - 1) / 2];
		this.numOfCities = cities;
	}

	private int currentPtPosition(int x, int y) {
		int i = Math.min(x, y), j = Math.max(x, y);
		int k = i + 1;
		int s = i * numOfCities + j;
		return s - k * (k + 1) / 2;
	}

	public int getCostForCurPt(int i, int j) {
		return totalCosts[currentPtPosition(i, j)];
	}

	public int incCost(int i, int j) {
		int position = currentPtPosition(i, j);
		int cost = totalCosts[position] + 1;
		totalCosts[position] = cost;
		return cost;
	}

}
