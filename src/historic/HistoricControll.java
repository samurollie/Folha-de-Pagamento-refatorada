package src.historic;

import src.employee.EmployeeList;
import src.syndicate.SyndicateList;

public class HistoricControll {
    //TODO: Adicionar Payroll
    Historic<EmployeeList> employee;
    Historic<SyndicateList> syndicate;
    Historic<Integer> actions;

    // 1 = Employee
    // 2 = Employee
    // 3 = Employee
    // 4 = Employee
    // 5 = Syndicate
    // 6 = Employee
    // 7 = Payroll

    public HistoricControll() {
       this.employee = new Historic<EmployeeList>();
       this.syndicate = new Historic<SyndicateList>();
       this.actions = new Historic<Integer>();
    }

    public void addAction(int action, EmployeeList employees, SyndicateList syndicate) {
        this.actions.add(action);
        if (action == 1 || action == 2 || action == 3 || action == 4 || action == 6) {
            this.employee.add(employees);
        } else if (action == 5) {
            this.syndicate.add(syndicate);
        }
    }

    public EmployeeList undoEmployeeList() {
        return this.employee.undo();
    }

    public EmployeeList redoEmployeeList() {
        return this.employee.redo();
    }

    public SyndicateList undoSyndicateList() {
        return this.syndicate.undo();
    }

    public SyndicateList redoSyndicateList() {
        return this.syndicate.redo();
    }

    public int undoAction() {
        return this.actions.undo();
    }

    public int redoAction() {
        return this.actions.redo();
    }

}
