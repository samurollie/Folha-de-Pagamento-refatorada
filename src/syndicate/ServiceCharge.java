package src.syndicate;

public class ServiceCharge {
    private double charge;
    private String description;

    public ServiceCharge(double charge, String description) {
        this.charge = charge;
        this.description = description;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCharge() {
        return this.charge;
    }

    public String getDescription() {
        return this.description;
    }
}
