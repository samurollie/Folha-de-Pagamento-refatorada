package src.employee;

import src.utilities.Input;
import src.utilities.Sale;

public class EmployeeList {
    private Employee employees[];
    private int maxCapacity;
    public static int size;

    public EmployeeList(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.employees = new Employee[maxCapacity];
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

            } else {
                Salaried aux = (Salaried)employeeList.employees[i];

                newList.employees[i] = new Salaried(aux.name, aux.address, aux.card, aux.getPaymentMethod(), aux.getSalary(), aux.getComissionPercentage());
                
                if (aux.hasSales()) {
                    for (Sale sale : aux.getSales()) {
                        ((Salaried)newList.employees[i]).addSale(sale.date, sale.value, sale.description, sale.employeeId);
                    }
                }
            
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
        if (id < maxCapacity && employees[id] != null) {
            return true;
        } else {
            return false;
        }
    }

    public Employee getEmployee(int index) {
        return this.employees[index];
    }
    
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
        int type = Input.readInt();
        
        if (type == 1) {
            System.out.println("Insira o valor do coeficiente salario/hora: ");
            double hourSalary =  Input.readDouble();
            this.employees[id] = new Hourly(name, address, id, payment, hourSalary);
        } else {
            int comissioned;
            System.out.println("Será um empregado comissionado?");
            System.out.println("(1) - Sim");
            System.out.println("(2) - Não");
            comissioned = Input.readInt();
            
            System.out.println("Qual será o salário inicial?");
            double salary =  Input.readDouble();
            double taxa = 1;
            
            if (comissioned == 1) {
                System.out.println("Qual a taxa de comissão?");
                taxa = Input.readDouble();
            }
            
            this.employees[id] = new Salaried(name, address, id, payment, salary, taxa);
        }

        System.out.println("Adicionando empregado " + id);

        System.out.println("Empregado adicionado!\n");
        System.out.println(this.employees[id].showEmployeeInfo());
        EmployeeList.size++;
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
            if (((Salaried) employees[id]).getComissionPercentage() > 1) {
                System.out.println("(1) - Salariado");
                System.out.println("(2) - Horista");
                int op = Input.readInt();
    
                if (op == 1) {
                    employees[id] = ((Salaried)employees[id]).toSalaried();
                } else {
                    employees[id] = ((Salaried)employees[id]).toHourly();
                }
            } else {
                System.out.println("(1) - Horista");
                System.out.println("(2) - Comissionado");
                int op = Input.readInt();
                
                if (op == 1) {
                    employees[id] = ((Salaried)employees[id]).toHourly();
                } else {
                    employees[id] = ((Salaried)employees[id]).toComissioned();
                }
            }
            
            
        } else if (employees[id] instanceof Hourly) {            
            System.out.println("(1) - Salariado");
            System.out.println("(2) - Comissionado");
            int op = Input.readInt();

            if (op == 1) {
                employees[id] = ((Hourly)employees[id]).toSalaried();

            } else {
                employees[id] = ((Hourly)employees[id]).toComissioned();
            }
        }
    }
}
