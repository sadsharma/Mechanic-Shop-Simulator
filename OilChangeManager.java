/**
 * @author Sadikshya Sharma
 * SBU ID: 113305452
 * Recitation 3
 * This class is for Tony's Discount Oil Change, the main calls
 * the appropriate methods according to the options the user picks
 */
import java.util.Scanner;
public class OilChangeManager {
    private static int currentList = 0;
    // list 0 is joe's list and list 1 is donny's list
    private static CarList joeList = new CarList(); //Joe's car list
    private static CarList donnyList = new CarList(); // Donny's car list
    private static CarList mergedList = new CarList();//Merged list for two
    private static CarList finishedList = new CarList();//finished list
    private static Scanner input = new Scanner(System.in);
    private static boolean quit = false;
    // keeps track of user wanting to quit the game
    private static Car cutCar;
    // when the user cuts a car it is stored here ready to be pasted

    /*The main menu below calls all the methods that go along with the menu
     options*/
    public static void main(String[] args)
    {
        System.out.println("                                                ");
        System.out.println("Follow the menu to update Joe and Donny's lists " +
                "accordingly to your needs.");

        while(!quit)
        {
            printMenu();
            String optionChosen = input.nextLine().toLowerCase();

            if(optionChosen.equals("l"))
            {
                printListMenu();
                optionChosen = input.nextLine().toLowerCase();
                if(optionChosen.equals("a"))
                {
                    addCarToEndOfList();
                }
                else if(optionChosen.equals("f"))
                {
                    cursorForward();
                }
                else if(optionChosen.equals("h"))
                {
                    cursorToHead();
                }
                else if(optionChosen.equals("t"))
                {
                    cursorToTail();
                }
                else if(optionChosen.equals("b"))
                {
                    cursorBackward();
                }
                else if(optionChosen.equals("i"))
                {
                    insertCarBeforeCursor();
                }
                else if(optionChosen.equals("x"))
                {
                    cutCarAtCursor();
                }
                else if(optionChosen.equals("v"))
                {
                    pasteCarBeforeCursor();
                }
                else if(optionChosen.equals("r"))
                {
                    removeCarAtCursor();
                }
                else
                {
                    System.out.println("That is not a valid list option.");
                }
            }
            else if(optionChosen.equals("m"))
            {
                mergeLists();
            }
            else if(optionChosen.equals("p"))
            {
                printLists();
            }
            else if(optionChosen.equals("f"))
            {
                pasteToFinishedList();
            }
            else if(optionChosen.equals("s"))
            {

            }
            else if(optionChosen.equals("q"))
            {
                quit = true;
                System.out.println("Retirement will be good for you!");
            }
            else
            {
                System.out.println("That is not a valid list option.");
            }
        }

    }

    // Prints the main menu
    public static void printMenu()
    {
        System.out.println("                                    ");
        System.out.println(" L) Edit Job Lists for Joe and Donny ");
        System.out.println(" M) Merge Job Lists");
        System.out.println(" P) Print Job Lists");
        System.out.println(" F) Paste car to end of finished car list");
        System.out.println(" S) Sort Job Lists");
        System.out.println(" Q)Quit");
        System.out.println("Please select an option: ");
    }

    //accesses sub menu under Ll
    public static void printListMenu()
    {
        System.out.println("Please select a list - Joe (J) or Donny (D): ");
        String listChosen = input.nextLine().toLowerCase();
        if(listChosen.equals("j"))
        {
            currentList = 0;
            printListSubMenu();
        }
        else if(listChosen.equals("d"))
        {
            currentList = 1;
            printListSubMenu();
        }
        else
        {
            System.out.println("That is not a list option.");
            printListMenu();
        }
    }

    // prints list sub menu for lists for joe and donny
    public static void printListSubMenu()
    {
        System.out.println("Menu under L: ");
        System.out.println("     A) Add a car to the end of the list");
        System.out.println("     F) Cursor Forward");
        System.out.println("     H) Cursor to Head");
        System.out.println("     T) Cursor to Tail");
        System.out.println("     B) Cursor Backward");
        System.out.println("     I) Insert car before cursor");
        System.out.println("     X) Cut car at cursor");
        System.out.println("     V) Paste before cursor");
        System.out.println("     R) Remove cursor");
        System.out.println("Please select an option:");
    }

    // This method adds a car to the end of a chosen list
    public static void addCarToEndOfList()
    {
        System.out.println("Enter the make of the car (Ford, GMC, Chevy, " +
                "Jeep, Dodge, Chrysler, and Lincoln): ");
        String make = input.nextLine();

        System.out.println("Enter the owner's name: ");
        String owner = input.nextLine();

        try
        {
            Make carMake = Make.valueOf(make.toUpperCase());
            Car addedCar = new Car(carMake,owner);
            if(currentList == 0)
            {
                joeList.appendToTail(addedCar);
                System.out.println(addedCar.getOwner() + "'s " +
                        addedCar.getMake().toString().toLowerCase() + " has " +
                        "been added to joe's list and is scheduled for an " +
                        "oil change!");
            }
            else
            {
                donnyList.appendToTail(addedCar);
                System.out.println(addedCar.getOwner() + "'s " +
                        addedCar.getMake().toString().toLowerCase() + " has been " +
                        "added to donny's list and is scheduled for an oil " +
                        "change!");
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Sorry, we don't service " + make);
        }

    }

    /*This method moves the cursor forward to the next car if there is a car
     to move to otherwise the user is notified that there is no car to move to*/
    public static void cursorForward() {
        if (currentList == 0) {
            try {
                joeList.cursorForward();
                System.out.println("The cursor has been moved forward in " +
                        "joe's list!");
            } catch (EndOfList e) {
                System.out.println("There is no other vehicle to put the " +
                        "cursor on. This is the end of the list so the cursor" +
                        " remained unchanged.");
            }
        } else {
            try {
                donnyList.cursorForward();
                System.out.println("The cursor has been moved forward in " +
                        "donny's list!");
            } catch (EndOfList e) {
                System.out.println("There is no other vehicle to put the " +
                        "cursor on. This is the end of the list so the cursor" +
                        " remained unchanged.");
            }
        }
    }

    /*This method moves the cursor backward to the previous car if there is a
    car to move to otherwise the user is notified that there is no car to move to*/
    public static void cursorBackward()
    {
        if (currentList == 0)
        {
            try {
                joeList.cursorBackward();
                System.out.println("The cursor has been moved backward in " +
                        "joe's list!");
            } catch (EndOfList e)
            {
                System.out.println("There is no other vehicle to put the " +
                        "cursor on. This is the front of the list so the " +
                        "cursor remained unchanged.");
            }
        }
        else {
            try {
                donnyList.cursorBackward();
                System.out.println("The cursor has been moved backward in " +
                        "donny's list!");
            } catch (EndOfList e) {
                System.out.println("There is no other vehicle to put the " +
                        "cursor on. This is the front of the list so the " +
                        "cursor remained unchanged.");
            }
        }
    }

    //The cursor is set to head and moved to the beginning of the list
    public static void cursorToHead()
    {
        if (currentList == 0)
        {
            joeList.resetCursorToHead();
            System.out.println("The cursor has been reset to head in " +
                    "joe's list!");
        }
        else {
            donnyList.resetCursorToHead();
            System.out.println("The cursor has been reset to head in " +
                    "donny's list!");
        }
    }

    //The cursor is moved to the end of the list and is set to tail
    public static void cursorToTail()
    {
        if (currentList == 0)
        {
            joeList.setCursor(joeList.getTail());
            System.out.println("The cursor has been moved to the tail in " +
                    "joe's list!");
        }
        else {
            donnyList.setCursor(joeList.getTail());
            System.out.println("The cursor has been moved to the tail in " +
                    "donny's list!");
        }
    }

    //A new car is inserted before the cursor to chosen list
    public static void insertCarBeforeCursor()
    {
        System.out.println("Enter the make of the car: ");
        String make = input.nextLine();

        System.out.println("Enter the owner's name: ");
        String owner = input.nextLine();

        try
        {
            Make carMake = Make.valueOf(make.toUpperCase());
            Car addedCar = new Car(carMake,owner);
            if(currentList == 0)
            {
                joeList.insertBeforeCursor(addedCar);
                System.out.println(addedCar.getOwner() + "'s " +
                        addedCar.getMake().toString().toLowerCase() + " has " +
                        "been added to joe's list.");
            }
            else
            {
                donnyList.insertBeforeCursor(addedCar);
                System.out.println(addedCar.getOwner() + "'s " +
                        addedCar.getMake().toString().toLowerCase() + " has " +
                        "been added to donny's list.");
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Sorry, we don't service " + make);
        }
    }

    /* The car at cursor of the chosen list is cut and ready to be pasted to
     another list */
    public static Car cutCarAtCursor()
    {
        if(currentList == 0)
        {
            try
            {
                cutCar = joeList.removeCursor();
                System.out.println(cutCar.getOwner() + "'s " +
                        cutCar.getMake().toString().toLowerCase() + " has " +
                        "been cut from joe's list.");
            }catch (EndOfList e)
            {
                System.out.println("There is no car in this list so you can't" +
                        " cut the car at cursor.");
            }
        }
        else
        {
            try
            {
                cutCar = donnyList.removeCursor();
                System.out.println(cutCar.getOwner() + "'s " +
                        cutCar.getMake().toString().toLowerCase() + " has " +
                        "been cut from donny's list.");
            }catch (EndOfList e)
            {
                System.out.println("There is no car in this list so you can't" +
                        " cut the car at cursor.");
            }
        }
        return cutCar;
    }

    /*The car cut previously is added before the cursor at the list chosen
     by the user, the method also checks to see if the user has never cut a
    car before which means one cant be pasted.*/
    public static void pasteCarBeforeCursor()
    {
        if(currentList == 0 && cutCar != null)
        {
            joeList.insertBeforeCursor(cutCar);
            System.out.println(cutCar.getOwner() + "'s " +
                    cutCar.getMake().toString().toLowerCase() + " has " +
                    "been pasted to joe's list.");
        }
        else
        {
            donnyList.insertBeforeCursor(cutCar);
            System.out.println(cutCar.getOwner() + "'s " +
                    cutCar.getMake().toString().toLowerCase() + " has " +
                    "been pasted to donny's list.");
        }
        if(cutCar == null)
        {
            System.out.println("You have not chosen a car to cut yet. You " +
                    "have to cut before pasting.");
        }
        cutCar = null;
    }

    // Removes the car at the cursor in the chosen list
    public static void removeCarAtCursor()
    {
        if(currentList == 0)
        {
            try
            {
                Car removedCar = joeList.removeCursor();
                System.out.println(removedCar.getOwner() + "'s " +
                        removedCar.getMake().toString().toLowerCase() + " has " +
                        "been removed from joe's list.");
            }catch (EndOfList e)
            {
                System.out.println("There is no car to remove!");
            }
        }
        else
        {
            try
            {
                Car removedCar = joeList.removeCursor();
                System.out.println(removedCar.getOwner() + "'s " +
                        removedCar.getMake().toString().toLowerCase() + " has " +
                        "been removed from donny's list.");
            }catch (EndOfList e)
            {
                System.out.println("There is no car to remove!");
            }
        }
    }

    /*This method asks which list should be printed and then prints the list
    that user wants printed */
    public static void printLists()
    {
        System.out.println("Please select what list to print, (J) for" +
                " Joe, (D) for Donny, and (F) for " +
                "the finished list: ");
        String option = input.nextLine().toLowerCase();
        if(option.equals("j"))
        {
            System.out.println("Joe's List:");
            joeList.printList();
        }
        else if(option.equals("d"))
        {
            System.out.println("Donny's List:");
            donnyList.printList();
        }
        else if(option.equals("f"))
        {
            System.out.println("Finished list:");
            finishedList.printList();
        }
        else
        {
            System.out.println("There is no list to be printed of " +
                    "that initial.");
        }
    }

    /* merges joe's list and donny's list to make a merged list depending on
    who called in sick*/
    public static void mergeLists()
    {
        CarList emptyList = new CarList();
        System.out.println("Who called in sick? Enter (J) for joe and (D) for" +
                " donny: ");
        String calledInSick = input.nextLine().toLowerCase();
        int mergedListLength = joeList.numCars() + donnyList.numCars();
        joeList.resetCursorToHead();
        donnyList.resetCursorToHead();
        if(calledInSick.equals("j")) {
            while (!(mergedList.numCars() == mergedListLength)) {
                try {
                    mergedList.appendToTail(donnyList.removeCursor());
                } catch (Exception e) {

                }
                try {
                    mergedList.appendToTail(joeList.removeCursor());
                } catch (Exception e) {

                }
            }
            System.out.println("Since joe is sick all the cars have been " +
                    "merged into donny's list.");
            donnyList = mergedList;
            joeList = emptyList;
        }
        else if( calledInSick.equals("d"))
        {
            while (!(mergedList.numCars() == mergedListLength)) {
                try {
                    mergedList.appendToTail(joeList.removeCursor());
                } catch (Exception e) {

                }
                try {
                    mergedList.appendToTail(donnyList.removeCursor());
                } catch (Exception e) {

                }
            }
            System.out.println("Since donny is sick all the cars have been " +
                    "merged into joe's list.");
            joeList = mergedList;
            donnyList = emptyList;
        }
        else
        {
            System.out.println("There is no mechanic of that initial in this " +
                    "shop.");
        }
    }
    // pastes the car from the front of donny or joe's list unless they choose
    // the prioritize option which they would need to cut a car first for.
    public static void pasteToFinishedList()
    {
        System.out.println("To move joe's or donny's head car to finished " +
                "list enter (H) or to prioritize a car and finish early enter" +
                        " (P): ");
        String chosen = input.nextLine().toLowerCase();
        if(chosen.equals("h"))
        {
            completeOilChangeOnCar();
        }
        else if(chosen.equals("p"))
        {
            if(cutCar == null)
            {
                System.out.println("You did not cut a car yet. You need to " +
                        "cut a car before pasting to the finished list. " +
                        "Please select menu option L and cut a car first.");
            }
            else
            {
                finishedList.appendToTail(cutCar);
                System.out.println(cutCar.getOwner() + "'s " +
                        cutCar.getMake().toString().toLowerCase() + " has " +
                        "been added to the finished list.");
            }
        }
        else
        {
            System.out.println("You did not pick a valid option.");
        }
        cutCar = null;

    }
    //A car in the beginning of the list whose oil change has been done will be
    //added to the finished list
    public static void completeOilChangeOnCar()
    {
        CarListNode currentCursor;
        System.out.println("Who finished working on their car? enter (J) for" +
                " joe and (D) for donny:");
        String finishedCar = input.nextLine().toLowerCase();
        if(finishedCar.equals("j"))
        {
            if(joeList.getCursor() == joeList.getHead()) {
                try
                {
                    Car carFinished = joeList.removeCursor();
                    finishedList.appendToTail(carFinished);
                    System.out.println(carFinished.getOwner() + "'s " +
                            carFinished.getMake().toString().toLowerCase() +" is" +
                            " done! You need to give them a call to pick up " +
                            "their car!");
                }
                catch (EndOfList e)
                {
                    System.out.println("There is no car in this list to have " +
                            "finished an oil change for!");
                }
            }else
            {
                currentCursor = joeList.getCursor();
                joeList.resetCursorToHead();
                try
                {
                    Car carFinished = joeList.removeCursor();
                    finishedList.appendToTail(carFinished);
                    System.out.println(carFinished.getOwner() + "'s " +
                            carFinished.getMake().toString().toLowerCase() +" is " +
                            "done! You need to give them a call to pick up " +
                            "their car!");
                }
                catch (EndOfList e)
                {
                    System.out.println("There is no car in this list to have " +
                            "finished an oil change for!");
                }
                joeList.setCursor(currentCursor);
            }
        }
        else if(finishedCar.equals("d"))
        {
            if(donnyList.getCursor() == donnyList.getHead()) {
                try
                {
                    Car carFinished = donnyList.removeCursor();
                    finishedList.appendToTail(carFinished);
                    System.out.println(carFinished.getOwner() + "'s " +
                            carFinished.getMake().toString().toLowerCase() +" is " +
                            "done! You need to give them a call to pick up " +
                            "their car!");
                }
                catch (EndOfList e)
                {
                    System.out.println("There is no car in this list to have " +
                            "finished an oil change for!");
                }
            }else
            {
                currentCursor = donnyList.getCursor();
                donnyList.resetCursorToHead();
                try
                {
                    Car carFinished = donnyList.removeCursor();
                    finishedList.appendToTail(carFinished);
                    System.out.println(carFinished.getOwner() + "'s " +
                            carFinished.getMake().toString().toLowerCase() +" is " +
                            "done! You need to give them a call to pick up " +
                            "their car!");
                }
                catch (EndOfList e)
                {
                    System.out.println("There is no car in this list to have " +
                            "finished an oil change for!");
                }
                donnyList.setCursor(currentCursor);
            }
        }
        else
        {
            System.out.println("That is not a mechanic in this shop!");
        }
    }
}
