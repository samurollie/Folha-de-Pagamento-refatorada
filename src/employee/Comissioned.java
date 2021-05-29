package src.employee;

import java.util.ArrayList;
import java.util.Date;

import src.utilities.Sale;

public class Comissioned extends Salaried{
    private double comissionPercentage;
    private ArrayList<Sale> sales = new ArrayList<Sale>();
    
    public Comissioned(String name, String adress, int card, int paymentMethod, double salary, double comissionPercentage) {
        super(name, adress, card, paymentMethod, salary);
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

    @Override
    public String showEmployeeInfo() {
        return "----------\n"+ 
        "Nome:" + this.name + 
        "\nEndereço:" + this.address + 
        "\nCard:" + this.card + 
        "\nMétodo de pagamento:" + this.paymentMethod + 
        "\nTipo: Comissionado" +
        "\nSalario:" + this.getSalary() +
        "\nPorcentagem de Comissão:" + this.comissionPercentage +
        "\n----------";
    }
}