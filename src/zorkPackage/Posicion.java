package zorkPackage;

public class Posicion {
	private int y;
	private int x;
	private int z;
	
	public Posicion(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	@Override
	public String toString() {
		return "Posicion [y=" + y + ", x=" + x + ", z=" + z + "]";
	}
	
	
	
	
	
	
}
