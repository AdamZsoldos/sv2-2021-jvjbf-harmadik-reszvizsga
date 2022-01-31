package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable {

    private final String model;
    private LocalTime rentingTime;
    private static final int RENT_COST_PER_MINUTE = 15;

    public Bike(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        rentingTime = time;
    }

    @Override
    public void closeRent() {
        rentingTime = null;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) minutes * RENT_COST_PER_MINUTE;
    }
}
