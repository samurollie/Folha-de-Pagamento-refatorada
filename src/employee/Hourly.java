package src.employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import src.utilities.Timecard;

public class Hourly extends Employee{
    private ArrayList<Timecard> timeCards = new ArrayList<Timecard>();
    private double hourSalary;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public Hourly(String name, String adress, int card, int paymentMethod, double hourSalary) {
        super(name, adress, card, paymentMethod);
        this.setHourSalary(hourSalary);
    }

    public double getHourSalary() {
        return hourSalary;
    }

    public void setHourSalary(double hourSalary) {
        this.hourSalary = hourSalary;
    }

    public void showTimeCards() {
        int total = 0;
        for (Timecard timecard : timeCards) {
            System.out.println("Dia: " + timecard.entry.getDay());
            System.out.println("Horas Trabalhadas: " + timecard.workedHours);
            System.out.println("#############\n");

            total += timecard.workedHours;
        }

        System.out.println("Total de horas trabalhadas: " + total);
    }

    public void setTimeCards(String entry, String exit) {
        Timecard timeCard;
        try {
            timeCard = new Timecard(formatter.parse(entry), formatter.parse(exit));
            this.timeCards.add(timeCard);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String showEmployeeInfo() {
        return "----------\n"+ 
        "Nome:" + this.name + 
        "\nEndereço:" + this.address + 
        "\nCard:" + this.card + 
        "\nMétodo de pagamento:" + this.paymentMethod + 
        "\nTipo: Horista"+
        "\nSalario/Hora:" + this.hourSalary +
        "\n----------";
    }

    /* @Override
    public Hourly add() {
        return ;
    } */
}
