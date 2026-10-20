class ParkingLot {

    private int twoWheelers;
    private int fourWheelers;

    private final int twoCap;
    private final int fourCap;

    private static long revenue = 0;

    ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;

        twoWheelers = 0;
        fourWheelers = 0;
    }

    void park(String type) {

        if (type.equals("two")) {
            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                System.out.println("Two-wheeler parked.");
            } else {
                System.out.println("Two-wheeler section Full");
            }
        }

        else if (type.equals("four")) {
            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                System.out.println("Four-wheeler parked.");
            } else {
                System.out.println("Four-wheeler section Full");
            }
        }

        else {
            System.out.println("Invalid vehicle type.");
        }
    }

    void leave(String type) {

        if (type.equals("two")) {
            if (twoWheelers > 0) {
                twoWheelers--;
                System.out.println("Two-wheeler left.");
            } else {
                System.out.println("No two-wheeler to leave.");
            }
        }

        else if (type.equals("four")) {
            if (fourWheelers > 0) {
                fourWheelers--;
                System.out.println("Four-wheeler left.");
            } else {
                System.out.println("No four-wheeler to leave.");
            }
        }
    }

    int getTwoWheelers() {
        return twoWheelers;
    }

    int getFourWheelers() {
        return fourWheelers;
    }

    static long getRevenue() {
        return revenue;
    }

    public static void main(String[] args) {

        ParkingLot lot = new ParkingLot(2, 2);

        lot.park("two");
        lot.park("two");
        lot.park("two");      

        lot.park("four");
        lot.park("four");
        lot.park("four");    
         
        lot.leave("two");
        lot.leave("four");

        lot.park("two");
        lot.park("four");

        System.out.println();
        System.out.println("Final Occupancy");
        System.out.println("Two Wheelers : " + lot.getTwoWheelers());
        System.out.println("Four Wheelers : " + lot.getFourWheelers());

        System.out.println("Revenue : " + ParkingLot.getRevenue());
    }
}