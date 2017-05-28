import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class Clusterer {

	private LinkedList<Point> allDataPoints = null;
	private LinkedList<Cluster> clusters = null;
	private LinkedList<Cluster> previousClusters = null;
	private String data_points_file_name = null;
	private String cluster_center_points_file_name = null;

	public Clusterer(String input_point_file_path, String cluster_centroid_file_path) {
		this.data_points_file_name = input_point_file_path;
		this.cluster_center_points_file_name = cluster_centroid_file_path;
		clusters = new LinkedList<Cluster>();
		previousClusters = new LinkedList<Cluster>();
		allDataPoints = new LinkedList<Point>();
		readClusterCentroid();
		readDataPoints();
	}

	public void performClustering() {
		for (Cluster cluster : clusters) {
			cluster.reset();

		}

		for (Point point : allDataPoints) {
			Cluster lowest_distance_cluster = clusters.get(getIndexOfClusterCloseToThePoint(clusters, point));
			lowest_distance_cluster.setMember(point);
		}
	}

	public void performKMeansClustering() {
		performClustering();
        printClusters();
		while (!isCurrentClustersAndPreviousClustersAreSame(previousClusters, clusters)) {
			previousClusters = (LinkedList<Cluster>) clusters.clone();
			for (Cluster cluster : clusters) {
				cluster.updateCentiod();
			}
			performClustering();
			printClusters();
		}
	}

	int getIndexOfClusterCloseToThePoint(LinkedList<Cluster> clusters, Point p) {
		int index = 0;
		double distance = Double.MAX_VALUE;

		for (int i = 0; i < clusters.size(); i++) {
			if (getDistance(p, clusters.get(i).getCentroid()) <= distance) {
				distance = getDistance(p, clusters.get(i).getCentroid());
				index = i;
			}

		}

		return index;
	}

	double getDistance(Point p1, Point p2) {
		// manhatn distance is used
		return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
	}

	boolean isCurrentClustersAndPreviousClustersAreSame(LinkedList<Cluster> c1s, LinkedList<Cluster> c2s) {
		if (c1s.size() != c2s.size()) {
			return false;
		} else if (c1s.size() == c2s.size()) {
			for (int i = 0; i < c1s.size(); i++) {

				Cluster first_cluster = c1s.get(i);
				Cluster second_cluster = c2s.get(i);

				if (first_cluster.getMembers().size() != second_cluster.getMembers().size()) {
					return false;
				} else if (first_cluster.getMembers().size() == second_cluster.getMembers().size()) {

					for (int j = 0; j < first_cluster.getMembers().size(); j++) {
						Point p1 = first_cluster.getMembers().get(j);
						Point p2 = second_cluster.getMembers().get(j);
						if (getDistance(p1, p2) != 0) {
							return false;

						}
					}
				}

			}
		}
		return true;
	}

	public void readDataPoints() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(data_points_file_name));
			String line = null;
			while ((line = br.readLine()) != null) {
				if ((line = line.trim()).length() > 0) {
					String[] parts = line.split("\\s{1,}");
					if (parts.length > 2) {
						Point point = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), parts[2]);
						// System.out.println(point.toString());
						allDataPoints.add(point);
					}
				}

			}
		} catch (Exception e) {

		}
	}

	public void printClusters() {
		for (Cluster cluster : clusters) {
			System.out.println("Cluster : " + cluster.getName());
			System.out.println("---------------------");
			for (Point point : cluster.getMembers()) {
				System.out.println(point.toString());
			}
			System.out.println("---------------------");
		}
	}

	public void readClusterCentroid() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(cluster_center_points_file_name));
			String line = null;
			while ((line = br.readLine()) != null) {
				if ((line = line.trim()).length() > 0) {
					String[] parts = line.split("\\s{1,}");
					if (parts.length > 2) {
						Point point = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), parts[2]);
						Cluster cluster = new Cluster(point);
						clusters.add(cluster);
						// System.out.println(cluster.toString());
					}
				}

			}
		} catch (Exception e) {

		}
	}
}
