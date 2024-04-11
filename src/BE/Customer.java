package BE;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String birthDate;
    private String lastName;

    public Customer(int id, String name, String email, String birthDate, String lastName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {return birthDate;}

    public void setName(String name) {this.name = name;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public void setEmail(String email) {this.email = email;}

    public void setBirthDate(String birthDate) {this.birthDate = birthDate;}

}
