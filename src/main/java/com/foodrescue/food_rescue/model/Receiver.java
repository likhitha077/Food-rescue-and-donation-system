
package com.foodrescue.food_rescue.model;

public class Receiver extends User {

    private String organizationName;

    public Receiver() {
        super();
    }

    public Receiver(int userId, String name, String email,
            String password, String phone, String address,
            String organizationName) {

        super(userId, name, email, password,
                phone, "RECEIVER", address, null);

        this.organizationName = organizationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public void displayRole() {
        System.out.println("I am a Food Receiver");
    }
}
