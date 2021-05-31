package src.employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import src.utilities.Input;
import src.utilities.Timecard;

public class Hourly extends Employee{
    private ArrayList<Timecard> timeCards = new ArrayList<Timecard>();
    private double hourSalary;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

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

    public Salaried toSalaried () {
        System.out.println("Qual será o salário inicial?");
        double salary = Input.readDouble();

        return new Salaried(this.name, this.address, this.card, this.getPaymentMethod(), salary, 1);
    }

    public Salaried toComissioned () {
        System.out.println("Qual será o salário inicial?");
        double salary =  Input.readDouble();

        System.out.println("Qual a taxa de comissão?");
        double taxa =  Input.readDouble();

        return new Salaried(this.name, this.address, this.card, this.getPaymentMethod(), salary, taxa);
    }

    @Override
    public String showEmployeeInfo() {
        return super.showEmployeeInfo() +
        "\nTipo: Horista"+
        "\nSalario/Hora:" + this.hourSalary +
        "\n----------";
    }
}
