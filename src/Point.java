
public class Point {
	private double x = 0;
	private double y = 0;
	private String name = null;

	public Point(double x, double y, String name) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}
@Override
public String toString() {
	
	return "X:"+getX()+" Y:"+getY()+" Name :"+getName();
}
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}
}
