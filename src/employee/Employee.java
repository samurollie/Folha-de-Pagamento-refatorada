package src.employee;

import src.payment.methods.CheckHand;
import src.payment.methods.CheckMail;
import src.payment.methods.Deposit;
import src.payment.methods.PaymentMethod;

public class Employee {
    protected String name;
    protected String address;
    protected int card;
    protected PaymentMethod paymentMethod;

    public Employee(String name, String address, int card, int paymentMethod) {
        this.name = name;
        this.address = address;
        this.card = card;
        this.setPaymentMethod(paymentMethod);
    }

    public void setPaymentMethod(int method) {
        if (method == 1) {
            this.paymentMethod = new CheckHand();
        } else if (method ==  2) {
            this.paymentMethod = new Deposit();
        } else if (method == 3) {
            this.paymentMethod = new CheckMail();
        } else {
            System.out.println("Metodo de pagamento inválido!\n");
        }
    }

    public int getPaymentMethod() {
        return this.paymentMethod.toInt();
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
        "\nMétodo de pagamento:" + this.paymentMethod.toString();
    }
}