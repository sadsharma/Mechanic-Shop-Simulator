/**
 * @author Sadikshya Sharma
 * SBU ID: 113305452
 * Recitation 3
 * This class is the car class which creates every car on the list
 */
public class Car {
    private Make make;
    private String owner;

    /**
     * constructor for the car class initializes the values to parameter values
     * @param make is the make of the car
     * @param owner is the owner of the car
     */
    public Car(Make make, String owner)
    {
        this.make = make;
        this.owner = owner;
    }

    /**
     * mutator method for make
     * @param make is what this.make is set to
     */
    public void setMake(Make make)
    {
     this.make = make;
    }

    /**
     * mutator method for owner
     * @param owner is what this.owner is set to
     */
    public void setOwner(String owner)
    {
        this.owner = owner;
    }
    /**
     * getter method for make
     * @return make
     */
    public Make getMake() {
        return make;
    }

    /**
     * getter method for owner
     * @return owner
     */
    public String getOwner()
    {
        return owner;
    }

    /**
     * to string method for car class
     * @return make and owner of the car
     */
    public String toString()
    {
        return "Make: " + getMake() + " Owner: " + getOwner();
    }
}
