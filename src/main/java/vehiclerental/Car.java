package vehiclerental;

import java.time.LocalTime;

public class Car implements Rentable {

    private final String model;
    private LocalTime rentingTime;
    private final int rentCostPerMinute;

    public Car(String model, int rentCostPerMinute) {
        this.model = model;
        this.rentCostPerMinute = rentCostPerMinute;
    }

    public String getModel() {
        return model;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    public int getRentCostPerMinute() {
        return rentCostPerMinute;
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
        return (int) minutes * rentCostPerMinute;
    }
}
