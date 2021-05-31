package src.payment.methods;

public class Deposit implements PaymentMethod{
    private int agencyNumber;
    private int accountNumber;
    private int bankNumber;
    private double value;
    private String cpf;

    @Override
    public int toInt() {
        return 2;
    }

    @Override
    public String toString() {
        return "Depósito";
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
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
