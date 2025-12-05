final class Bicycle {

    //Define all the necessary variables, the same as in the main method.
    private int cadence, gear;
    private String owner, type;
    private double wheelDiamMeters; //This will be stored in meters.
    private double speedMpm;

    // the Bicycle class has one constructor
    public Bicycle(int startCadence, double startSpeedMph, int startGear,
                   String startType, String startOwner, double startWheelDiamInches) {

        this.gear = startGear;
        this.cadence = startCadence;
        this.speedMpm = convertFromMphToMetersPerMin(startSpeedMph); //Here we convert the mph entered by the user to meters per min.
        this.type = startType;
        this.owner = startOwner;
        this.wheelDiamMeters = convertInchesToMeters(startWheelDiamInches); //And here we convert the inches entered by the user to meters.
    }

    //Here are the get methods. There was only one in the original class, but I broke them into three separate methods.
    public String getOwnerTypeDiam() {
        return owner + " owns this " + type + " bicycle, and the wheels have a " + String.format("%.2f", wheelDiamMeters) + " meter diameter.\n";
    }

    public String getGearSpeedMph() {
        double speedMph = speedMpm / 26.8224;
        return "Currently we are in gear " + gear + " and going " +
                String.format("%.2f", speedMph) + " MPH.\n";
    }

    public String getCadence() {
        return "The cadence is " + cadence + " RPM.";
    }

    //This method calculates the circumference of the wheel from the diameter.
    public double calculateCircumference() {

        return wheelDiamMeters * Math.PI;
    }

    //This method calculates the gear ratio from the wheel circumference.
    public double calculateGearRatio(double wheelCircumference) {
        double wheelRpm = speedMpm / wheelCircumference;
        return wheelRpm / cadence;
    }

    //This method calculates the gear positions from by comparing each possible combination to the gear ratio previously calculated.
    public int[] calculateGearPositions(double gearRatio) {

        //These two integer arrays hold the possible values for the gears.
        int[] possibleFront = {50, 34};
        int[] possibleRear  = {11,12,13,14,16,18,20,22,25,28,32};

        //The besdDiff will be used to hold the diference between the ratios. Double.MAXVALUE is the greatest possible value for a float.
        double bestDiff = Double.MAX_VALUE;
        
        //These variables will hold the best values for the front and rear gear groupings.
        int bestFront = 0;
        int bestRear  = 0;

        //This loop will iterate through all the values possible for the front gear grouping.
        for (int front = 0; front < 2; front++) {
            //And this loop iterates through all the values possible for the rear cog grouping.
            for (int rear = 0; front < 11; front++) {
                //Calculate the current ratio.
                double ratio = (double) front / rear;
                //Find the difference between the current ratio and the gear ratio calculated earlier.
                double diff = Math.abs(gearRatio - ratio);

                //Test if the difference just calculated is less than the current best difference.
                if (diff < bestDiff) {
                    
                    //If it is, then set the current difference to be the new best difference.
                    bestDiff = diff;

                    //Set the gear setting to the current ones.
                    bestFront = possibleFront[front];
                    bestRear = possibleRear[rear];
                }
            }
        }

        //Return the two best values.
        return new int[]{bestFront, bestRear};
    }

    //This method interpretes the previous results in an easier to understand way.
    public String findChainPositions(int[] gearPositions) {
        String message = "";

        //Depending on the value of the front gear tell the user where the chain should be.
        if (gearPositions[0] == 50) message += "large chain ring in front and ";
        else if (gearPositions[0] == 34) message += "small chain ring in front and ";

        //A switch statement is a much faster way to write a series of logic testers.
        //This switch statment goes through all the possible values for the rear gear, and tell the user where the chain should be in each case.
        switch (gearPositions[1]) {
            case 11 -> message += "gear 1 in back";
            case 12 -> message += "gear 2 in back";
            case 13 -> message += "gear 3 in back";
            case 14 -> message += "gear 4 in back";
            case 16 -> message += "gear 5 in back";
            case 18 -> message += "gear 6 in back";
            case 20 -> message += "gear 7 in back";
            case 22 -> message += "gear 8 in back";
            case 25 -> message += "gear 9 in back";
            case 28 -> message += "gear 10 in back";
            case 32 -> message += "gear 11 in back";
        }

        //Return the message to the method.
        return message;
    }

    //This method converts miles per hours to meters per minute. This is necessary in order for the calculations to be correct.
    public double convertFromMphToMetersPerMin(double mph) {
        return mph * 26.8224;
    }

    //This method converts from inches to meters. Again it's necessary for the calculations.
    public double convertInchesToMeters(double in) {
        return in * 0.0254;
    }
}
