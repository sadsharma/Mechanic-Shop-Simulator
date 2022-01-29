/**
 * @author Sadikshya Sharma
 * SBU ID: 113305452
 * Recitation 3
 * This class is the car list class which created the actual list for the
 * mechanics who need an oil changing list.
 */
public class CarList {
    private CarListNode head;
    private CarListNode tail;
    private CarListNode cursor;
    private int numberOfCars;

    /**
     * Default constructor that sets the values of head, tail and cursor to null
     */
    public CarList()
    {
        this.cursor = null;
        this.head = null;
        this.tail = null;
    }

    /**
     * mutator method for head
     * @param head is the value that head would be set to
     */
    public void setHead(CarListNode head)
    {
        this.head = head;
    }

    /**
     * getter method for head
     * @return head
     */
    public CarListNode getHead()
    {
        return head;
    }

    /**
     * mutator method for cursor
     * @param cursor is what this.cursor is set to
     */
    public void setCursor(CarListNode cursor)
    {
        this.cursor = cursor;
    }

    /**
     * getter method for cursor
     * @return cursor
     */
    public CarListNode getCursor()
    {
        return cursor;
    }

    /**
     * mutator method for tail
     * @param tail
     */
    public void setTail(CarListNode tail)
    {
        this.tail = tail;
    }

    /**
     * getter method for tail
     * @return tail
     */
    public CarListNode getTail()
    {
        return tail;
    }

    /**
     * getter method for number of cars in a list
     * @return number of cars in a given list
     */
    public int numCars()
    {
        return numberOfCars;
    }

    /**
     * gives access to the car that is where the cursor is pointed
     * @return the car that cursor holds or null if there is no car in cursor
     */
    public Car getCursorCar()
    {
        if(cursor == null)
        {
            return null;
        }
        return cursor.getData();
    }

    /**
     * resets the cursor to head
     */
    public void resetCursorToHead()
    {
        cursor = head;
    }

    /**
     * moves the cursor forward to the next car in the list
     * @throws EndOfList if there is no car to move the cursor to
     */
    public void cursorForward() throws EndOfList
    {
        if(cursor != tail)
        {
            cursor = cursor.getNext();
        }
        else
        {
            throw new EndOfList();
        }
    }

    /**
     * moves the cursor backward to the previous car in the list
     * @throws EndOfList if there is no car to move the cursor back to
     */
    public void cursorBackward() throws EndOfList
    {
        if(cursor != head)
        {
            cursor = cursor.getPrev();
        }
        else {
            throw new EndOfList();
        }
    }
    /**
     * inserts car before wherever the cursor is, takes into account if the
     * cursor is head or tail and sets the links appropriately
     * @param newCar is the car being added to the list
     * @throws IllegalArgumentException when the car being added is null
     */
    public void insertBeforeCursor(Car newCar) throws IllegalArgumentException
    {
        CarListNode newCarNode = new CarListNode(newCar);
        if(newCar == null)
        {
            throw new IllegalArgumentException();
        }
        else if(cursor == null)
        {
            head = newCarNode;
            cursor = newCarNode;
            tail = newCarNode;
        }
        else if(cursor == head)
        {
            //newCarNode.setPrev(null);
            newCarNode.setNext(head);
            head.setPrev(newCarNode);
            head = newCarNode;
        }
        else
        {
            newCarNode.setNext(cursor);
            newCarNode.setPrev(cursor.getPrev());
            cursor.getPrev().setNext(newCarNode);
            cursor.setPrev(newCarNode);
        }
        numberOfCars++;
    }

    /**
     * adds a car to the end of the list
     * @param newCar is the car being added to the end of list
     * @throws IllegalArgumentException when the car being added is null
     */
    public void appendToTail(Car newCar) throws IllegalArgumentException
    {
        if( newCar == null)
        {
            throw new IllegalArgumentException();
        }
        CarListNode addedCar = new CarListNode(newCar);
        if(head == null)
        {
            head = addedCar;
            tail = addedCar;
            cursor = addedCar;
        }
        else {
            tail.setNext(addedCar);
            addedCar.setPrev(tail);
            tail = addedCar;
        }
        numberOfCars++;
    }

    /**
     * removes the node at cursor and makes the cursor the previous node
     * unless the head was removed in which case the cursor would be equal to
     * the new head
     * @return the removed node
     * @throws EndOfList when the cursor is null
     */
    public Car removeCursor() throws EndOfList
    {
        CarListNode tempCar = cursor;
        if( cursor == null)
        {
            throw new EndOfList();
        }
        else if( numberOfCars == 1)
        {
            head = null;
            cursor = null;
            tail = null;
        }
        else if(cursor == head)
        {
            head = head.getNext();
            head.setPrev(null);
            cursor = head;
        }
        else if ( cursor == tail)
        {
            tail = tail.getPrev();
            tail.setNext(null);
            cursor = tail;
        }
        else {
            CarListNode temp = cursor.getPrev();
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = temp;
        }
        numberOfCars--;
        return tempCar.getData();
    }

    /**
     * Prints everything in the given list
     */
    public void printList ()
    {
        System.out.println("-----------------------");
        CarListNode temp = head;
        while(temp != null)
        {
            if(temp == cursor)
            {
                System.out.print("-->");
            }
            System.out.print(temp.getData() + "   ");
            temp = temp.getNext();
            System.out.println(" ");
        }
        System.out.println("-----------------------");
    }
}
