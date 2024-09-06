import java.util.Objects;

public class Circle extends GeometricObject implements Comparable<Circle> {
    private double radius;
    
    /** Default Constructor */
    public Circle() {
        //Superclass constructor
        super();
    }
    /** Constructor with radius. */
    public Circle(double radius) {
        this(radius, "white", false); //Default values for color and filled.
    }
    
    /** Constructor with radius, color and filled. */
    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }
    
    /** Return radius */
    public double getRadius() {
        return radius;
    }
    
    /** Set a new radius */
    public void setRadius(double radius) {
        this.radius = radius;
    }
    
    @Override
    /** Return area */
    public double getArea() {
        return radius * radius * Math.PI;
    }
    
    /** Return diameter */
    public double getDiameter() {
        return 2 * radius;
    }
    
    @Override
    /** Return perimeter */
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    /** Print the circle info */
    public void printCircle() {
        System.out.println("The circle is created " + getDateCreated() +
        " and the radius is " + radius);
    }

    /**This uses an equals method to see if a circle is equivalent to another circle.*/
    @Override
    public boolean equals(Object c) {
        if (this == c) return true;
        if (c == null || getClass() != c.getClass()) return false;
        Circle other = (Circle) c;
        return Double.compare(this.radius, other.radius) == 0;
    }
    /** Override hashcode method.*/
    @Override
    public int hashCode() {
        return Objects.hash(radius); 
    }

    /**This statement compares one circle to another circle. */
    @Override
    public int compareTo(Circle other) {
        return Double.compare(this.radius, other.radius);
    }
}
