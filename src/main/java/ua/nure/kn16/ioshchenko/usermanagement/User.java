package ua.nure.kn16.ioshchenko.usermanagement;

import java.time.LocalDate;
import java.util.Date;
/**
* Class User contains information about user
* */
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;


    public User() {}

    public User(Long id, String firstName, String lastName, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public User(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
	public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
    * @return user's full name in format "First name, Last name"
    * */
    String getFullName() {
        return lastName + ", " + firstName;
    }

    /**
     * This method expects correct birth date established in the past
     * @return user's age in years
     */
    long getAge() {
        LocalDate date = LocalDate.now();
        int age = date.getYear() - dateOfBirth.getYear();
        if (date.getMonthValue() < dateOfBirth.getMonthValue() ||
                (date.getMonthValue() == dateOfBirth.getMonthValue() && date.getDayOfMonth() < dateOfBirth.getDayOfMonth())) {
            --age;
        }

        return age;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + "\nSurname: " + lastName + "\nDate of Birth: " + dateOfBirth;
    }

    @Override
    public int hashCode() {
        if (this.getId() == null) {
            return 0;
        }
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }
        if (this.getId() == null && ((User)obj).getId() == null) {
            return true;
        }

        return this.getId().equals(((User)obj).getId());
    }
}