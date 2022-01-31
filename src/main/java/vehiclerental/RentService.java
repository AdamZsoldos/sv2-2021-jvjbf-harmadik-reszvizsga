package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {

    private static final long MAXIMUM_RENT_TIME_IN_MINUTES = 180;

    Set<User> users = new HashSet<>();
    Set<Rentable> rentables = new HashSet<>();
    Map<Rentable, User> actualRenting = new TreeMap<>();

    public Set<User> getUsers() {
        return users;
    }

    public Set<Rentable> getRentables() {
        return rentables;
    }

    public Map<Rentable, User> getActualRenting() {
        return actualRenting;
    }

    public void registerUser(User user) {
        validateUser(user);
        users.add(user);
    }

    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }

    public void rent(User user, Rentable rentable, LocalTime time) {
        validateRent(user, rentable);
        rentable.rent(time);
        actualRenting.put(rentable, user);
    }

    public void closeRent(Rentable rentable, int minutes) {
        if (!actualRenting.containsKey(rentable)) {
            throw new IllegalArgumentException("Renting not found");
        }
        actualRenting.get(rentable).minusBalance(rentable.calculateSumPrice(minutes));
        actualRenting.remove(rentable);
        rentable.closeRent();
    }

    private void validateUser(User user) {
        if (users.stream().anyMatch(u -> u.getUserName().equals(user.getUserName()))) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
    }

    private void validateRent(User user, Rentable rentable) {
        if (!users.contains(user)) {
            throw new IllegalStateException("User not found");
        }
        if (!rentables.contains(rentable)) {
            throw new IllegalStateException("Rentable not found");
        }
        if (rentable.getRentingTime() != null) {
            throw new IllegalStateException("Rentable already rented");
        }
        if (user.getBalance() < rentable.calculateSumPrice(MAXIMUM_RENT_TIME_IN_MINUTES)) {
            throw new IllegalStateException("User balance too low");
        }
    }
}
