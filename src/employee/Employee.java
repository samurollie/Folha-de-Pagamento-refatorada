package src.employee;

import src.utilities.Input;

public class Employee {
    protected String name;
    protected String address;
    protected int card;
    protected String paymentMethod;

    public Employee(String name, String address, int card, int paymentMethod) {
        this.name = name;
        this.address = address;
        this.card = card;
        this.setPaymentMethod(paymentMethod);
    }

    public void setPaymentMethod(int method) {
        if (method == 1) {
            this.paymentMethod = "hand";
        } else if (method ==  2) {
            this.paymentMethod = "deposit";
        } else if (method == 3) {
            this.paymentMethod = "mail";
        } else {
            System.out.println("Metodo de pagamento inválido!\n");
        }
    }

    public int getPaymentMethod() {
        if (this.paymentMethod.equals("hand")) {
            return 1;
        } else if (this.paymentMethod.equals("deposit")) {
            return 2;
        } else {
            return 3;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String showEmployeeInfo() {
        return "----------\n"+ 
        "Nome:" + this.name + 
        "\nEndereço:" + this.address + 
        "\nCard:" + this.card + 
        "\nMétodo de pagamento:" + this.paymentMethod + 
        "\n----------";
    }
}