import java.util.Date;

public abstract class GeometricObject {
    private String color = "white";
    private boolean filled;
    private Date dateCreated;
    
    /**Construct a geometric object*/
    protected GeometricObject() {
        dateCreated = new Date();
    }
    /**Construct a geometric object with color and filled value */
    protected GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
        dateCreated = new Date();
    }
    /**Return the color*/
    public String getColor() {
        return color;
    }

    /**Setter method for color*/
    public void setColor(String color) {
        this.color = color;
    }

    /**Return filled. Since filled is boolean,
     * the get method name is isFilled*/
    public boolean isFilled() {
        return filled;
    }

    /**Setter method for new filled*/
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    /** Get dateCreated*/
    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "Created on " + dateCreated + 
        "\ncolor: " + color + " and filled: " + filled;
    }
    /**Abstract method getArea*/
    public abstract double getArea();
    /**Abstract method getPerimeter*/
    public abstract double getPerimeter();
}
