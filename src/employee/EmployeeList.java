package src.employee;

import java.util.Scanner;

import src.utilities.Sale;

public class EmployeeList {
    private Employee employees[];
    /* private Salaried salaried[];
    private Hourly hourly[];
    private Comissioned comissioned[]; */
    private int maxCapacity;
    private Scanner input = new Scanner(System.in);
    public static int size;

    public EmployeeList(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.employees = new Employee[maxCapacity];
        /* this.salaried = new Salaried[maxCapacity];
        this.comissioned = new Comissioned[maxCapacity];
        this.hourly = new Hourly[maxCapacity]; */
        EmployeeList.size = 0;
    }

    public static EmployeeList copy(EmployeeList employeeList) {
        int oldSize = EmployeeList.size;
        EmployeeList newList = new EmployeeList(employeeList.maxCapacity);
        for(int i = 0; i < employeeList.employees.length; i++) {
            
            if (employeeList.employees[i] == null) {
                continue;
            }

            if (employeeList.employees[i] instanceof Hourly) {
                Hourly aux = (Hourly)employeeList.employees[i];

                newList.employees[i] = new Hourly(aux.name, aux.address, aux.card, aux.getPaymentMethod(), aux.getHourSalary());

            } else if (employeeList.employees[i] instanceof Comissioned) {
                Comissioned aux = (Comissioned)employeeList.employees[i];

                newList.employees[i] = new Comissioned(aux.name, aux.address, aux.card, aux.getPaymentMethod(), aux.getSalary(), aux.getComissionPercentage());

                if (aux.hasSales()) {
                    for (Sale sale : aux.getSales()) {
                        ((Comissioned)newList.employees[i]).addSale(sale.date, sale.value, sale.description, sale.employeeId);
                    }
                }
            } else {
                Salaried aux = (Salaried)employeeList.employees[i];

                newList.employees[i] = new Salaried(aux.name, aux.address, aux.card, aux.getPaymentMethod(), aux.getSalary());
            }
        }
        EmployeeList.size = oldSize;
        return newList;
    }

    public void showAll() {
        for (Employee employee : employees) {
            if (employee == null) 
                continue;
            
            System.out.println("\n");
            System.out.println(employee.showEmployeeInfo());
            System.out.println("\n");
        }
    }

    /* Função que verifica se existe algum empregado com aquele ID */
    public boolean containsId(int id) {
        if (getEmployee(id) != null) {
            return true;
        } else {
            return false;
        }
    }

    public Employee getEmployee(int index) {
        /* if (this.hourly[index] != null) {
            return this.hourly[index];
        } else if (this.comissioned[index] != null) {
            return this.comissioned[index];
        } else if (this.salaried[index] != null) {
            return this.salaried[index];
        } else {
            return null;
        } */
        return this.employees[index];
    }

    // public int listSize() {
    //     return EmployeeList.size;
    // }
    
    public void resize(int newSize) {
        System.out.printf("Antigo tamanho: %d\n", this.employees.length);
        Employee newList[] = new Employee[newSize];
        for (int i = 0; i < maxCapacity; i++) {
            newList[i] = this.employees[i];
        }
        employees = newList;
        maxCapacity = newSize;
        System.out.printf("Novo tamanho: %d\n", this.employees.length);
    }

    public void addEmployee(String name, String address, int id, int payment) {
        System.out.println("Que tipo de empregado " + name + " será?");
        System.out.println("(1) - Horista");
        System.out.println("(2) - Assalariado");
        int type = input.nextInt();
        
        if (type == 1) {
            System.out.println("Insira o valor do coeficiente salario/hora: ");
            double hourSalary = input.nextDouble();
            this.employees[id] = new Hourly(name, address, id, payment, hourSalary);
        } else {
            int comissioned;
            System.out.println("Será um empregado comissionado?");
            System.out.println("(1) - Sim");
            System.out.println("(2) - Não");
            comissioned = input.nextInt();
            
            System.out.println("Qual será o salário inicial?");
            double salary = input.nextDouble();
            
            if (comissioned == 1) {
                System.out.println("Qual a taxa de comissão?");
                double taxa = input.nextDouble();
                this.employees[id] = new Comissioned(name, address, id, payment, salary, taxa);
            } else {
                this.employees[id] = new Salaried(name, address, id, payment, salary);
            }
        }

        System.out.println("Adicionando empregado " + id);
        EmployeeList.size += 1;

        System.out.println("Empregado adicionado!\n");
        System.out.println(this.employees[id].showEmployeeInfo());
    }

    public void removeEmployee(int id) {
        if (containsId(id)) {
            this.employees[id] = null;
            EmployeeList.size--;

            System.out.println("Empregado removido!");
        } else {
            System.out.println("Não existe nenhum empregado com essa identificação!");
        }
    }

    public void employeeConversion(int id) {
        System.out.println("Para qual tipo você deseja converter?");
        
        if (employees[id] instanceof Salaried) {
            Salaried employee = (Salaried) employees[id];
            
            System.out.println("(1) - Horista");
            System.out.println("(2) - Comissionado");
            int op = input.nextInt();

            if (op == 1) {
                employees[id] = employees[id].SalariedToHourly(employee);
            } else {
                employees[id] = employees[id].SalariedToComissioned(employee);
            }

        } else if (employees[id] instanceof Hourly) {
            Hourly employee = (Hourly) employees[id];
            
            System.out.println("(1) - Salariado");
            System.out.println("(2) - Comissionado");
            int op = input.nextInt();

            if (op == 1) {
                employees[id] = employees[id].HourlyToSalaried(employee);
            } else {
                employees[id] = employees[id].HourlyToComissioned(employee);
            }
        } else {
            Comissioned employee = (Comissioned) employees[id];
            
            System.out.println("(1) - Salariado");
            System.out.println("(2) - Horista");
            int op = input.nextInt();

            if (op == 1) {
                employees[id] = employees[id].ComissionedToSalaried(employee);
            } else {
                employees[id] = employees[id].ComissionedToHourly(employee);
            }
        }
    }
}
