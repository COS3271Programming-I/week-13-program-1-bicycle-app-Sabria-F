import java.util.Scanner;

public class BicycleApp {
    
    //Define main method.
    public static void main(String[] args) {
        Scanner userinput = new Scanner(System.in);
        
        //Define integers for cadence and gear.
        int cadence, gear;
        //Define doubles for speed and wheel diameter.
        Double speed, wheelDiameter;
        //Define strings for owner and type.
        String owner, type;

        //From now on, I plan to stop commenting formatting.
        System.out.println("\n");

        //Prompt user for the owner's name.
        System.out.print("Enter the owner of the bicycle --> ");
        owner = userinput.nextLine();

        //Prompt user for the brand of bicycle.
        System.out.print("Enter the brand of the bicycle --> ");
        type = userinput.nextLine();

        //Prompt user for the wheel diameter, this is necessary to calculate the RPM.
        System.out.print("Enter the wheel diameter of the bicycle in inches --> ");
        wheelDiameter = userinput.nextDouble();
        userinput.nextLine();

        //Prompt the user for the speed of the bicycle.
        System.out.print("Enter the current speed of the bicycle in mph --> ");
        speed = userinput.nextDouble();
        userinput.nextLine();

        //Prompt the user for the current cadence of the bicycle.
        System.out.print("Enter the current cadence of the bicycle in revolutions per minute --> ");
        cadence = userinput.nextInt();
        userinput.nextLine();

        //Prompt the user for the gear.
        System.out.print("Enter the current gear --> ");
        gear = userinput.nextInt();
        userinput.nextLine();

        //Create an instance of the bicycle class, and set all the values to the ones chosen by the user.
        Bicycle newBicycle = new Bicycle(cadence, speed, gear, type, owner, wheelDiameter);

        //Use the diameter entered by the user to calculate the wheel circumference.
        Double wheelCircumference = newBicycle.calculateCircumference();

        //Next, calculate the gear ratio.
        Double gearRatio = newBicycle.calculateGearRatio(wheelCircumference);

        //Choose the gear positions that best fit the gear ratio.
        int[] gearPositions = newBicycle.calculateGearPositions(gearRatio);

        //Translate the gear positions previously calculated into something the user can understand.
        String chainPositions = newBicycle.findChainPositions(gearPositions);

        //Get all the information about the bicycle, and store it in a string named message.
        String message = newBicycle.getOwnerTypeDiam() + newBicycle.getGearSpeedMph() + newBicycle.getCadence();

        System.out.println("\n\n");

        //Display the information to the user.
        System.out.println(message);

        //Tell the user which gear setting is currently most optimal, based on previous calculations.
        System.out.println("Based on the diameter of the wheel, the speed, and the cadence, the best gear setting right now is " + chainPositions + ".");

        System.out.println("\n\n");

        //Close the scanner.
        userinput.close();

    }
}
