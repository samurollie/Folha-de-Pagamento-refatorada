package src.employee;

public class Salaried extends Employee{
    private double salary;

    public Salaried(String name, String adress, int card, int paymentMethod, double salary) {
        super(name, adress, card, paymentMethod);
        this.setSalary(salary);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
 
    @Override
    public String showEmployeeInfo() {
        return "----------\n"+ 
        "Nome:" + this.name + 
        "\nEndereço:" + this.address + 
        "\nCard:" + this.card + 
        "\nMétodo de pagamento:" + this.paymentMethod + 
        "\nTipo: Salariado" +
        "\nSalario:" + this.salary +
        "\n----------";
    }

    /* @Override
    public Salaried add() {
        Scanner input = new Scanner(System.in);
        if 
        System.out.println("Será um empregado comissionado?");
        System.out.println("(1) - Sim");
        System.out.println("(2) - Não");
        int comissioned = input.nextInt();
    } */
}
