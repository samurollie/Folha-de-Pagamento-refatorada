package src.syndicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import src.employee.Employee;

public class SyndicateList {
    private HashMap<Employee, Syndicate> employeeList; // Lista dos empregados que fazem parte do sindicato
    private Scanner input = new Scanner(System.in);
    private Random random = new Random();

    public SyndicateList() {
        this.employeeList = new HashMap<Employee, Syndicate>();
    }
    
    public SyndicateList(SyndicateList syndicateList) {
        this.employeeList = syndicateList.employeeList;
    }

    public void showAll() {
        this.employeeList.forEach((key,value) -> {
            System.out.println("id: ");
            System.out.println(key.showEmployeeInfo()); 
            System.out.println("Value: ");
            System.out.println(value.toString());
        });
    }

    public void removeEmployee(Employee employee) {
        if (!employeeList.containsKey(employee)) {
            System.out.println("Esse empregado não faz parte do sindicato!");
        } else {
            employeeList.remove(employee);
        }
    }
    
    public void addEmployee(Employee employee) {
        if (employeeList.containsKey(employee)) {
            System.out.println("Esse empregado já faz parte do sindicato!");
        } else {
            System.out.println("Insira a taxa sindical para " + employee.getName());
            double syndicalCharge = input.nextDouble();
            employeeList.put(employee, new Syndicate(syndicalCharge, employeeList.size() + 1));
            System.out.println("Empregado adicionado ao sindicato!");
        }
    }

    public boolean containsEmployee(Employee employee) {
        return employeeList.containsKey(employee);
    }

    public void changeId(Employee employee) {
        ArrayList<Integer> notAvailableIDs = new ArrayList<Integer>();
        employeeList.forEach((key, value) -> {
            notAvailableIDs.add(value.syndicalId);
        });
        
        int newId;
        while(true) {
            newId = random.nextInt(employeeList.size() + 1);
            boolean found = false;
            for (Integer integer : notAvailableIDs) {
                if (integer.equals(newId)) {
                    found = true;
                }
            }
            if (!found) {
                break;
            }
        }

        employeeList.get(employee).setsyndicalId(newId);
        System.out.println("Novo ID gerado: " + newId);
    }

    public Syndicate getSyndicate(Employee employee) {
        return employeeList.get(employee);
    }

    public void addSyndicalCharge(Employee employee) {
        employeeList.get(employee).addServiceCharge();
    }

    public String showInfoOnSyndicate(Employee employee) {
        return employee.showEmployeeInfo() + employeeList.get(employee).toString();
    }
}
