package src.payment.methods;

public class CheckHand implements PaymentMethod{
    private int number;
    private double value;
    private String destination;
    private String location;
    private String date;

    @Override
    public int toInt() {
        return 1;
    }

    @Override
    public String toString() {
        return "Cheque em Mãos";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
