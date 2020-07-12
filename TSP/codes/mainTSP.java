import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class mainTSP {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the input file name:");
		String input = sc.nextLine();
		sc.close();
		String output = "test-output-" + input.substring(11);
		List<Point> point = new ArrayList<Point>();
		File file = new File(input);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		// reading input file
		while ((line = br.readLine()) != null) {
			if (line != null) {
				String pointarr[] = line.split("\\s+");
				if (pointarr[0].equals("") && pointarr.length == 4) {
					if(pointarr.length != 3) {
						int i  = 0 ;	
							for(int j = i ; j < pointarr.length-1 ; j++) {
								String temp = "";			
								temp = (pointarr[j+1]) ;
								pointarr[j] = temp ;
								
							}
					}
				}
				if(!pointarr[0].equals("")) {
 					Point p = new Point(Integer.parseInt(pointarr[0]), Integer.parseInt(pointarr[1]),
							Integer.parseInt(pointarr[2]));
					point.add(p);
				}
			}
		}
		br.close();
		// adding points to points array
		Point[] points = point.toArray((Point[]) Array.newInstance(point.get(0).getClass(), point.size()));
		
		/*in below iterations the main purpose is to 
		* optimize input set to find better approximation values.
		* to do that simply locate the center of given set of points
		*/ 
		 //in this part it will find median of x and y's 
		 // then simply locate and place it to first point.
		
		ArrayList<Integer> ValuesX = new ArrayList<Integer>();
		ArrayList<Integer> ValuesY = new ArrayList<Integer>();
		for (int i = 0; i < points.length; i++) {
			ValuesX.add(points[i].getX());
			ValuesY.add(points[i].getY());
		}
		int maxX, minX, maxY, minY;
		maxX = Collections.max(ValuesX);
		minX = Collections.min(ValuesX);
		maxY = Collections.max(ValuesY);
		minY = Collections.min(ValuesY);
		double centerCityId = setCenterCityId(minX, maxX, minY, maxY, points);
		for (int j = 0; j < points.length; j++) {
			if (points[j].getId() == (int) centerCityId) {
				int id = points[0].getId();
				int xVal = points[0].getX();
				int yVal = points[0].getY();
				points[0].setId(points[j].getId());
				points[0].setX(points[j].getX());
				points[0].setY(points[j].getY());
				points[j].setId(id);
				points[j].setX(xVal);
				points[j].setY(yVal);
				break;
			}
		}
		
		TourLength<Point> tourLength = new DefaultLength();
		double initlength = tourLength.length(points);

		TSP fls = new FastLocalSearch(new TwoOptMoveCost());
		double newLength = fls.optimizer(points, initlength);
		int initLen = (int) initlength ;
		int newLen = (int) newLength ;
		System.out.println("Default tour length = "+ initLen  + " Fast Local Search tour length = "+ newLen);
		int maxRuns = 0;
		if(points.length > 10000) {
			maxRuns = 22000 ; 
		}
		else if(points.length < 300) {
			maxRuns = 103000 ;
		}
		else if(points.length < 1050) {
			maxRuns = 170000 ;
		}
		else if(points.length > 1003 && points.length < 3000) {
			maxRuns = 231000 ;
		}
		
		double alpha = Double.parseDouble("0.125");

		CostOfSearch cost;
		cost = new CostMatrix(points.length);

		double frequency = alpha * (newLength / points.length);

		fls = new FastLocalSearch(new GLSMoveCost(cost, frequency));
		TSP guidedSearch = new GuidedLocalSearch(tourLength, fls, cost, maxRuns);
		double guidedScore = guidedSearch.optimizer(points, newLength);
		int writeToFile = (int) guidedScore ;
		System.out.printf("Fast Local Search tour length = " + newLen + " Guided Local Search tour length = " + writeToFile + "\n");
		FileWriter myWriter = new FileWriter(output);
 		myWriter.write(writeToFile + "\n");
		for (int i = 0; i < points.length; i++) {
			myWriter.write(points[i].getId() + " " + points[i].getX() + " " + points[i].getY() + "\n");
		}
		myWriter.close();
	System.out.println("results are written to corresponding output file");
	}

	private static double setCenterCityId(int minX, int maxX, int minY, int maxY, final Point[] points) {
		double tempX = (double) (minX + maxX) / 2;
		double tempY = (double) (minY + maxY) / 2;
		double centerCityId = getClosestCityIndex(tempX, tempY, points);
		return centerCityId;
	}

	private static int getClosestCityIndex(double fromX, double fromY, final Point[] points) {
		int minIndex = 0;
		double minDist = Double.MAX_VALUE;
		for (int i = 0; i < points.length; i++) {
			double currentX = points[i].getX();
			double currentY = points[i].getY();
			double dist = calculateDistance(fromX, fromY, currentX, currentY);
			if (minDist > dist) {
				minIndex = i;
				minDist = dist;
			}
		}

		return minIndex;
	}

	private static double calculateDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

}