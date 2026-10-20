public class CinemaShow {

    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    public CinemaShow(String title) {
        this(title, 100);
    }

    public boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }

    public void cancel(int n) {
        seatsAvailable = Math.min(capacity, seatsAvailable + n);
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {
        CinemaShow show = new CinemaShow("Avengers: Doomsday", 50);

        System.out.println("Booking 25 seats: " + show.book(25));
        System.out.println("Seats available: " + show.getSeatsAvailable());

        System.out.println("Booking 30 seats: " + show.book(30));
        System.out.println("Seats available: " + show.getSeatsAvailable());

        System.out.println("Cancelling 10 seats");
        show.cancel(10);
        System.out.println("Seats available: " + show.getSeatsAvailable());

        System.out.println("Booking 35 seats: " + show.book(35));
        System.out.println("Seats available: " + show.getSeatsAvailable());

        System.out.println("Total booked seats: " + CinemaShow.getTotalBooked());
    }
}
