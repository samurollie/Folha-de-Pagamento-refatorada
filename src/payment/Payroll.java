package src.payment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import src.employee.Employee;
import src.payment.methods.*;
import src.utilities.Input;


public class Payroll {
    private HashMap<Employee, CheckHand> checkHandList; // Lista dos empregados que recebem o cheque em mãos
    private HashMap<Employee, CheckMail> checkMailList; // Lista dos empregados que recebem o cheque pelos correios
    private HashMap<Employee, Deposit> depositList; // Lista dos empregados que recebem por deposito
    private Calendar calendar;
    private ArrayList<Integer> fridays;
    private Scanner input = new Scanner(System.in);

    public Payroll() {
        this.checkHandList = new HashMap<Employee, CheckHand>();
        this.checkMailList = new HashMap<Employee, CheckMail>();
        this.depositList = new HashMap<Employee, Deposit>();
        this.calendar = Calendar.getInstance(new Locale("pt", "BR"));
        this.fridays = new ArrayList<Integer>();
    }

    public void addEmployee(Employee employee) {
        if (employee.getPaymentMethod() == 1) {
            this.checkHandList.put(employee, new CheckHand(employee.getName()));
        } else if (employee.getPaymentMethod() == 2) {
            System.out.println("Insira o numero do Banco: ");
            int bankNumber = Input.readInt();
            
            System.out.println("Insira o numero da agencia:");
            int agencyNumber = Input.readInt();

            System.out.println("Insira o numero da conta:");
            int accountNumber = Input.readInt();
            
            System.out.println("Insira seu CPF: ");
            int cpf = Input.readInt();

            this.depositList.put(employee, new Deposit(agencyNumber, accountNumber, bankNumber, cpf));
        } else if (employee.getPaymentMethod() == 3) {
            this.checkMailList.put(employee, new CheckMail(employee.getName(), "here", employee.getName(), employee.getAddress()));
        }
    }

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
