/**
 * @author Sadikshya Sharma
 * SBU ID: 113305452
 * Recitation 3
 * This class is the car list node class which creates the node for every car
 */
public class CarListNode {
    private Car data;
    private CarListNode next;
    private CarListNode prev;

    /**
     * CarListNode constructor
     * @param initData a car to create a node
     * @throws IllegalArgumentException is thrown when the parameter is null
     */
    public CarListNode(Car initData) throws IllegalArgumentException
    {
        if(initData == null)
        {
            throw new IllegalArgumentException();
        }
        this.data = initData;
        this.next = null;
        this.prev = null;
    }

    /**
     * mutator method that sets next for a node
     * @param next is what this.next will be set to
     */
    public void setNext(CarListNode next)
    {
        this.next = next;
    }

    /**
     * getter method for next
     * @return next
     */
    public CarListNode getNext()
    {
        return next;
    }

    /**
     * mutator methods that sets previous
     * @param prev is what this.prev will be set to
     */
    public void setPrev(CarListNode prev)
    {
        this.prev = prev;
    }

    /**
     * getter method for previous
     * @return previous
     */
    public CarListNode getPrev()
    {
        return prev;
    }

    /**
     * getter method for data
     * @return method
     */
    public Car getData()
    {
        return data;
    }
}
