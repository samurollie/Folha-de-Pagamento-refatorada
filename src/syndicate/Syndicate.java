package src.syndicate;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Syndicate {
    protected double syndicalCharge;
    protected ArrayList<ServiceCharge> serviceCharges;
    protected int syndicalId;
    private Random random = new Random();
    private Scanner input = new Scanner(System.in);

    public Syndicate(double syndicalCharge, int syndicalId) {
        this.syndicalCharge = syndicalCharge;
        this.syndicalId = syndicalId;
        this.serviceCharges = new ArrayList<ServiceCharge>();
    }

    public int getsyndicalId() {
        return syndicalId;
    }

    public void setsyndicalId(int syndicalId) {
        this.syndicalId = syndicalId;
    }

    public int changesyndicalId(int max) {
        return random.nextInt(max);
    }

    public double getsyndicalCharge() {
        return syndicalCharge;
    }

    public void setsyndicalCharge(double syndicalCharge) {
        this.syndicalCharge = syndicalCharge;
    }

    public void addServiceCharge() {
        System.out.println("Insira a Porcentagem: ");
        double value = input.nextDouble();
        input.nextLine();

        System.out.println("Insira a descrição dessa taxa: ");
        String description = input.nextLine();

        serviceCharges.add(new ServiceCharge(value, description));

        System.out.println("Taxa adicionada!");
    }

    @Override
    public String toString() {
        return "\nIdentificação no Sindicato:" + this.syndicalId +
        "\nTaxa Sindical:" + this.syndicalCharge
        +"\n----------";
    }
}
