package src.employee;

import java.util.ArrayList;
import java.util.Date;

import src.utilities.Sale;

public class Salaried extends Employee{
    private double salary;
    private double comissionPercentage;
    private ArrayList<Sale> sales = new ArrayList<Sale>();

    public Salaried(String name, String adress, int card, int paymentMethod, double salary, double comissionPercentage) {
        super(name, adress, card, paymentMethod);
        this.setSalary(salary);
        this.comissionPercentage = comissionPercentage;
    }

    public void addSale(Date date, double value, String description, int employeeId) {
        Sale sale = new Sale(date, value, description, employeeId); 
        this.sales.add(sale);
    }

    public void showSales() {
        System.out.println("Exibindo as vendas feitas por " + this.name);
        System.out.println("Porcentagem de comissão: " + comissionPercentage + "%");
        for (Sale sale : sales) {
            System.out.println("\n-----------------------\n");
            System.out.println("Data: " + sale.date);
            System.out.println("Descrição: " + sale.description);
            System.out.println("Valor: " + sale.value);
        }
    }

    public double getComissionPercentage() {
        return comissionPercentage;
    }

    public void setComissionPercentage(double comissionPercentage) {
        this.comissionPercentage = comissionPercentage;
    }

    public boolean hasSales() {
        return !sales.isEmpty();
    }

    public ArrayList<Sale> getSales() {
        return this.sales;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    private String getType() {
        return (this.comissionPercentage == 1) ? "Salariado" : "Comissionado";
    }

    @Override
    public String showEmployeeInfo() {
        return "----------\n"+ 
        "Nome:" + this.name + 
        "\nEndereço:" + this.address + 
        "\nCard:" + this.card + 
        "\nMétodo de pagamento:" + this.paymentMethod + 
        "\nTipo: " + this.getType() +
        "\nSalario:" + this.salary +
        "\nPorcentagem de Comissão: " + this.comissionPercentage + "(" + this.getType() + ")" +
        "\n----------";
    }
}
