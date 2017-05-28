import java.util.LinkedList;

public class Cluster {

	private LinkedList<Point> members = null;
	private Point centroid = null;
	private String name = null;

	public Cluster(Point centroid) {
		this.centroid = centroid;
		members = new LinkedList<Point>();
	}

	public Point getCentroid() {
		return centroid;
	}

	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}

	public void updateCentiod() {

		double tempXSum = 0;
		double tempYSum = 0;
		for (Point point : members) {
			tempXSum = tempXSum + point.getX();
			tempYSum = tempYSum + point.getY();
		}
		centroid.setX(tempXSum / members.size());
		centroid.setY(tempYSum / members.size());
		

	}

	public String getName() {
		return centroid.getName();
	}

	public void setName(String name) {
		centroid.setName(name);
	}

	public LinkedList<Point> getMembers() {
		return members;
	}

	public void setMember(Point p) {
		members.addLast(p);
	}

	public void removeMember(Point p) {
		members.remove(p);
	}

	public void reset() {
		members.clear();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return centroid.toString();
	}

}
