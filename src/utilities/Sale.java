package src.utilities;

import java.util.Date;

public class Sale {
    //TODO: mudar date de string pra data msm

    public Date date;
    public double value;
    public String description;
    public int employeeId;
    
    public Sale(Date date, double value, String description, int employeeId) {
        this.date = date;
        this.value = value;
        this.description = description;
        this.employeeId = employeeId;
    }
}
