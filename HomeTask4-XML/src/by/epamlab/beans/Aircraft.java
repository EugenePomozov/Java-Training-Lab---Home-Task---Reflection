package by.epamlab.beans;

public class Aircraft {
    private String number;
    private String type;
    private int capacity;

    public Aircraft(String number, String type, int capacity) {
        this.number = number;
        this.type = type;
        this.capacity = capacity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
