package phase3.calculations;
public class Vector3d implements Vector3dInterface{
    private double x;
    private double y;
    private double z;
    
    public Vector3d(){
    }
    
    public Vector3d(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector3d(Vector3dInterface object){
        this.x = object.getX();
        this.y = object.getY();
        this.z = object.getZ();
    }
    		
    		
    		
    @Override
	public Vector3dInterface add(Vector3dInterface other) {
    	Vector3dInterface a = new Vector3d(this);
    	a.setX(a.getX() + other.getX());
    	a.setY(a.getY() + other.getY());
    	a.setZ(a.getZ() + other.getZ());
		return a;
	}
    
	@Override
	public Vector3dInterface sub(Vector3dInterface other) {
		Vector3dInterface a = this;
		a.setX(a.getX() - other.getX());
		a.setY(a.getY() - other.getY());
		a.setZ(a.getZ() - other.getZ());
		return a;
	}
	@Override
	public Vector3dInterface mul(double scalar) {
		Vector3dInterface a = new Vector3d(this);
		a.setX(a.getX() * scalar);
		a.setY(a.getY() * scalar);
		a.setZ(a.getZ() * scalar);
		return a;
	}
	@Override
	public Vector3dInterface addMul(double scalar, Vector3dInterface other) {
		double scale = scalar;
		Vector3dInterface a = new Vector3d(this);
		Vector3dInterface b = new Vector3d(other);
		b = b.mul(scale);
		a = a.add(b);
		return a;
	}
	
	
	@Override
	public double norm() {
		return Math.sqrt(Math.pow(this.getX(),2)+Math.pow(this.getY(),2)+Math.pow(this.getZ(),2));
	}
	
	public Vector3d normalize() {
		return (Vector3d) this.mul(1/this.norm());
	}
	
	public Vector3d normalizeAB(Vector3d B) {
		Vector3d a = new Vector3d(this);
		Vector3d b = new Vector3d(B);
		
		Vector3d ab = new Vector3d(a.sub(b));
		return  ab.normalize();	
	}
	
	
	@Override
	public double dist(Vector3dInterface other) {
		Vector3dInterface a = new Vector3d(this);
		Vector3dInterface b = new Vector3d(other);
		Vector3dInterface aminb = a.sub(b);
		return Math.sqrt(Math.pow(aminb.getX(),2)+Math.pow(aminb.getY(),2)+Math.pow(aminb.getZ(),2));
	}
	@Override
	public Vector3dInterface mulByObj(Vector3dInterface other){
		Vector3dInterface a = new Vector3d(this);
        a.setX(a.getX() * other.getX());
        a.setY(a.getY() * other.getY());
        a.setZ(a.getZ() * other.getZ());
        return a;
    }
	@Override
	public String toString() {
		return "("+this.getX()+","+this.getY()+","+this.getZ()+")";
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
		return z;
	}
	@Override
	public void setZ(double z) {
		this.z = z;
	}

	
	


}

