package src.payment.methods;

public class Deposit {
    private int agencyNumber;
    private int accountNumber;
    private int bankNumber;
    private double value;
    private int cpf;
    
    public Deposit(int agencyNumber, int accountNumber, int bankNumber, int cpf) {
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.bankNumber = bankNumber;
        this.cpf = cpf;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(int bankNumber) {
        this.bankNumber = bankNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAgencyNumber() {
        return agencyNumber;
    }

    public void setAgencyNumber(int agencyNumber) {
        this.agencyNumber = agencyNumber;
    }
}
