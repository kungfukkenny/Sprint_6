package ru.yandex.scooter.data;

public class OrderData {
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String date;
    private final String color;
    private final String comment;

    public OrderData(String name, String surname, String address, String phone, String date, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.color = color;
        this.comment = comment;
    }

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getDate() { return date; }
    public String getColor() { return color; }
    public String getComment() { return comment; }
}