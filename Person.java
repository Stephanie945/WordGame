// src/lesson2/Person.java
package lesson2;

public class Person {
    private String firstName;
    private String lastName;

    // Constructor with only first name
    public Person(String firstName) {
        this.firstName = firstName;
        this.lastName = "";
    }

    // Constructor with first and last name
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
