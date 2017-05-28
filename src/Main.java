
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clusterer clusterer = new Clusterer("points.txt", "clusters.txt");
		clusterer.performKMeansClustering();
		clusterer.printClusters();
	}

}
