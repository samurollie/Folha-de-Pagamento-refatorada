package src.payment;

import java.util.Calendar;
import java.util.HashMap;

import src.employee.Employee;
import src.payment.methods.*;


public class Payroll {
    private HashMap<Employee, CheckHand> checkHandList; // Lista dos empregados que recebem o cheque em mãos
    private HashMap<Employee, CheckMail> checkMailList; // Lista dos empregados que recebem o cheque pelos correios
    private HashMap<Employee, Deposit> depositList; // Lista dos empregados que recebem por deposito
    private Calendar calendar;




    /* public void addEmployee(Employee employee) {
        if (employee.getPaymentMethod() == 1) {
            checkHandList.put(employee, new CheckHand(employee.getName()));
        } else if (method ==  2) {
            this.paymentMethod = "deposit";
        } else if (method == 3) {
            this.paymentMethod = "mail";
        } else {
            System.out.println("Metodo de pagamento inválido!\n");
        }
    } */
}
