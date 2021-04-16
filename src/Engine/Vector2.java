package Engine;

/**
 * @author jamescolbert
 */
public class Vector2 {
    public float x;
    public float y;
    
    public static final Vector2 Zero = new Vector2(0, 0);
    public static final Vector2 One = new Vector2(1, 1);
    
    public Vector2(float X, float Y) {
        x = X;
        y = Y;
    }
    
    public Vector2() {}
    
    public Vector2(Vector2 toCopy) {
    	this.x = toCopy.x;
    	this.y = toCopy.y;
    }
    
    public float distanceTo(Vector2 otherVector) {
        float _x = x - otherVector.x;
        float _y = y - otherVector.y;
        
        return (float)Math.sqrt((double)(_x * _x) + (double)(_y * _y));
    }
    
    public void normalize() {
        x = x > 1 ? 1 : x < -1 ? -1 : x;
        y = y > 0 ? 1 : y < -1 ? -1 : y;
    }
    
    public void multiply(Vector2 otherVector) {
    	x *= otherVector.x;
    	y *= otherVector.y;
    }
    
    public void multiply(float scalar) {
    	x *= scalar;
    	y *= scalar;
    }
    
    public void add(Vector2 otherVector) {
    	x += otherVector.x;
    	y += otherVector.y;
    }
    
    public void add(float x, float y) {
    	this.x += x;
    	this.y += y;
    }
}