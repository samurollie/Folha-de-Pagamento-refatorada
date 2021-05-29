package src.payment.payrolls;

public abstract class Payroll {
    String[][] dias = new String[500][3]; // Coluna 0 -> nome, coluna 1 -> dia, coluna 2 -> Método

    public void showPayroll(int d) {
        System.out.println("Empregados que recebem no dia " + d);
        for (int i = 0; i < 500; i++) {
            if(Integer.parseInt(dias[i][1]) == d) {
                System.out.println(dias[i]);
            }
        }
    }

    public abstract void Pay();
}
