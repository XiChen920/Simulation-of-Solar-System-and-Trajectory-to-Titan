package phase3.calculations;
/**
 * A similar class to the Vector3d class, but in two dimensions
 *
 */
public class Vector2d implements Vector3dInterface{
    private double x;
    private double y;
    
    public Vector2d(){

    	this.x = 0;
    	this.y = 0;

    }
    
    public Vector2d(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public Vector2d(Vector3dInterface object){
        this.x = object.getX();
        this.y = object.getY();
    }
    		
    		
    		
    @Override
	public Vector3dInterface add(Vector3dInterface other) {
    	Vector3dInterface a = new Vector2d(this);
    	a.setX(a.getX() + other.getX());
    	a.setY(a.getY() + other.getY());
		return a;
	}
    
	@Override
	public Vector3dInterface sub(Vector3dInterface other) {
		Vector3dInterface a = this;
		a.setX(a.getX() - other.getX());
		a.setY(a.getY() - other.getY());
		return a;
	}
	@Override
	public Vector3dInterface mul(double scalar) {
		Vector3dInterface a = new Vector2d(this);
		a.setX(a.getX() * scalar);
		a.setY(a.getY() * scalar);
		return a;
	}
	
	public Vector3dInterface mul(Vector2d scalar) {
		Vector3dInterface a = new Vector2d(this);
		Vector3dInterface b = new Vector2d(scalar);
		a.setX(a.getX() * b.getX() + a.getX() * b.getY());
		a.setY(a.getY() * b.getX() + a.getY() * b.getY());
		return a;
	}
	@Override
	public Vector3dInterface addMul(double scalar, Vector3dInterface other) {
		double scale = scalar;
		Vector3dInterface a = new Vector2d(this);
		Vector3dInterface b = new Vector2d(other);
		b = b.mul(scale);
		a = a.add(b);
		return a;
	}
	
	
	@Override
	public double norm() {
		return Math.sqrt(Math.pow(this.getX(),2)+Math.pow(this.getY(),2));
	}
	
	public Vector2d normalize() {
		return (Vector2d) this.mul(1/this.norm());
	}
	
	public Vector2d normalizeAB(Vector2d B) {
		Vector2d a = new Vector2d(this);
		Vector2d b = new Vector2d(B);
		
		Vector2d ab = new Vector2d(a.sub(b));
		return  ab.normalize();	
	}
	
	
	@Override
	public double dist(Vector3dInterface other) {
		Vector3dInterface a = new Vector2d(this);
		Vector3dInterface b = new Vector2d(other);
		Vector3dInterface aminb = a.sub(b);
		return Math.sqrt(Math.pow(aminb.getX(),2)+Math.pow(aminb.getY(),2));
	}
	@Override
	public Vector3dInterface mulByObj(Vector3dInterface other){
		Vector3dInterface a = new Vector2d(this);
        a.setX(a.getX() * other.getX());
        a.setY(a.getY() * other.getY());
        return a;
    }
	@Override
	public String toString() {
		return "("+this.getX()+","+this.getY()+")";
	}
    
    
    //getters & setters
    
	@Override
	public double getX() {
		return x;
	}
	@Override
	public void setX(double x) {
		this.x = x;
	}
	@Override
	public double getY() {
		return y;
	}
	@Override
	public void setY(double y) {
		this.y = y;		
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setZ(double z) {
		// TODO Auto-generated method stub
	}


	
	


}

