package id.go.kominfo.laila.mysqljdbcdemo.model;

import java.io.Serializable;

public class Friend implements Serializable {
    private int id;
    private String name;
    private String address;
    private String phone;

    public Friend(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Friend(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return String.format("%-3d %-20s %-25s %-14s", getId(), getName(), getAddress(), getPhone());
    }
}
