package model;

public class ClientUser {

    private static ClientUser current = null;

    private EmployeeType type;

    private ClientUser() {

    }

    public void setEmployeeType(EmployeeType type) {
        this.type = type;
    }

    public EmployeeType getEmployeeType() {
        return type;
    }


    public static ClientUser getInstance() {
        if (current == null)
            current = new ClientUser();
        return current;
    }
}
