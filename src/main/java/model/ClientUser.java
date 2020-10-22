package model;

public class ClientUser {

    private static ClientUser current = null;

    private ClientUser() {

    }


    public static ClientUser getInstance() {
        if (current == null)
            current = new ClientUser();
        return current;
    }
}
