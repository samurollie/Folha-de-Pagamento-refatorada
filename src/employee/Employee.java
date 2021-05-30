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

    public Hourly SalariedToHourly (Salaried employee) {
        System.out.println("Insira o valor do coeficiente salario/hora: ");
        double hourSalary =  Input.readDouble();
        int method;
        if (employee.paymentMethod == "hand") {
            method = 1;
        } else if (employee.paymentMethod == "deposit") {
            method = 2;
        } else {
            method = 3;
        }
        
        return new Hourly(employee.name, employee.address, employee.card, method, hourSalary);
    }

    public Hourly ComissionedToHourly (Salaried employee) {
        System.out.println("Insira o valor do coeficiente salario/hora: ");
        double hourSalary =  Input.readDouble();
        int method;
        if (employee.paymentMethod == "hand") {
            method = 1;
        } else if (employee.paymentMethod == "deposit") {
            method =  2;
        } else {
            method = 3;
        }
        
        return new Hourly(employee.name, employee.address, employee.card, method, hourSalary);
    }

    public Salaried HourlyToSalaried (Hourly employee) {
        System.out.println("Qual será o salário inicial?");
        double salary =  Input.readDouble();
        int method;
        if (employee.paymentMethod == "hand") {
            method = 1;
        } else if (employee.paymentMethod == "deposit") {
            method =  2;
        } else {
            method = 3;
        }

        return new Salaried(employee.name, employee.address, employee.card, method, salary, 1);
    }

    public Salaried ComissionedToSalaried (Salaried employee) {
        System.out.println("O salário atual é: " + employee.getSalary() + " deseja manter?");
        System.out.println("(1) - Sim");
        System.out.println("(2) - Não");
        int ans = Input.readInt();
        double newSalary;
        if (ans == 1) {
            newSalary = employee.getSalary();
        } else {
            System.out.println("Qual será o novo salário?");
            newSalary =  Input.readDouble();
        }

        int method;
        if (employee.paymentMethod == "hand") {
            method = 1;
        } else if (employee.paymentMethod == "deposit") {
            method =  2;
        } else {
            method = 3;
        }

        return new Salaried(employee.name, employee.address, employee.card, method, newSalary, 1);
    }

    public Salaried SalariedToComissioned (Salaried employee) {
        System.out.println("O salário atual é: " + employee.getSalary() + " deseja manter?");
        System.out.println("(1) - Sim");
        System.out.println("(2) - Não");
        int ans = Input.readInt();
        double newSalary;
        if (ans == 1) {
            newSalary = employee.getSalary();
        } else {
            System.out.println("Qual será o novo salário?");
            newSalary =  Input.readDouble();
        }

        int method;
        if (employee.paymentMethod == "hand") {
            method = 1;
        } else if (employee.paymentMethod == "deposit") {
            method =  2;
        } else {
            method = 3;
        }

        System.out.println("Qual a taxa de comissão?");
        double taxa =  Input.readDouble();

        return new Salaried(employee.name, employee.address, employee.card, method, newSalary, taxa);
    }

    public Salaried HourlyToComissioned (Hourly employee) {
        System.out.println("Qual será o salário inicial?");
        double salary =  Input.readDouble();
        int method;
        if (employee.paymentMethod == "hand") {
            method = 1;
        } else if (employee.paymentMethod == "deposit") {
            method =  2;
        } else {
            method = 3;
        }

        System.out.println("Qual a taxa de comissão?");
        double taxa =  Input.readDouble();
        return new Salaried(employee.name, employee.address, employee.card, method, salary, taxa);
    }
}