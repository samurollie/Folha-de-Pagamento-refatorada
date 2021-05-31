# Sistema de Folha de Pagamento

## Especificações

O objetivo do projeto é construir um sistema de folha de pagamento. O sistema consiste do
gerenciamento de pagamentos dos empregados de uma empresa. Além disso, o sistema deve
gerenciar os dados destes empregados, a exemplo os cartões de pontos. Empregados devem receber
o salário no momento correto, usando o método que eles preferem, obedecendo várias taxas e
impostos deduzidos do salário.

* Alguns empregados são horistas. Eles recebem um salário por hora trabalhada. Eles
submetem "cartões de ponto" todo dia para informar o número de horas trabalhadas naquele
dia. Se um empregado trabalhar mais do que 8 horas, deve receber 1.5 a taxa normal
durante as horas extras. Eles são pagos toda sexta-feira.

* Alguns empregados recebem um salário fixo mensal. São pagos no último dia útil do mês
(desconsidere feriados). Tais empregados são chamados "assalariados".

* Alguns empregados assalariados são comissionados e portanto recebem uma comissão, um
percentual das vendas que realizam. Eles submetem resultados de vendas que informam a
data e valor da venda. O percentual de comissão varia de empregado para empregado. Eles
são pagos a cada 2 sextas-feiras; neste momento, devem receber o equivalente de 2 semanas
de salário fixo mais as comissões do período.

  * Empregados podem escolher o método de pagamento.
  * Podem receber um cheque pelos correios
  * Podem receber um cheque em mãos
  * Podem pedir depósito em conta bancária
 
* Alguns empregados pertencem ao sindicato (para simplificar, só há um possível sindicato).

O sindicato cobra uma taxa mensal do empregado e essa taxa pode variar entre
empregados. A taxa sindical é deduzida do salário. Além do mais, o sindicato pode
ocasionalmente cobrar taxas de serviços adicionais a um empregado. Tais taxas de serviço
são submetidas pelo sindicato mensalmente e devem ser deduzidas do próximo
contracheque do empregado. A identificação do empregado no sindicato não é a mesma da
identificação no sistema de folha de pagamento.

* A folha de pagamento é rodada todo dia e deve pagar os empregados cujos salários vencem
naquele dia. O sistema receberá a data até a qual o pagamento deve ser feito e calculará o
pagamento para cada empregado desde a última vez em que este foi pago.


## Code Smells encontrados no projeto

### Duplicated Code

1) Na classe [Main](https://github.com/samurollie/Folha-de-Pagamento/blob/0309bd8fd4d68166938542cb3882632fcea88574/src/Main.java), assim como nas classes Employee, EmployeeList, Salaried, Syndicate e SyndicateList, toda vez que um número (Inteiro,double, etc) é lido logo após uma String será lida, o método `nextLine()` é chamado para a limpeza do buff e a correta leitura da String.

2) O método `ShowEmployeeInfo()` da Classe Employee e das suas subclasses é identico até certo ponto.

3) Como o atributo `paymentMethod` da Classe Employee é salvo como String para melhor visualização, mas em outros pontos do código é tratado como um inteiro, toda vez que esse atributo é utilizado torna-se necessário realizar a conversão da String em Inteiro e Vice-Versa.

### Feature Envy

1) Os métodos de conversão de Employee, `SalariedToHourly(), ComissionedToHourly(), HourlyToSalaried(), ComissionedToSalaried()` e `SalariedToComissioned()` da classe [Employee](https://github.com/samurollie/Folha-de-Pagamento/blob/0309bd8fd4d68166938542cb3882632fcea88574/src/employee/Employee.java#L66) estão mais interessados nas subclasses do que na superclasse. Esse caso também se encaixa no Code Smell Duplicated Code, pois o codigo entre esses métodos está repitido.

### Lazy Class

1) A classe [Salaried](https://github.com/samurollie/Folha-de-Pagamento/blob/main/src/employee/Salaried.java) possui apenas uma subclasse, [Comissioned](https://github.com/samurollie/Folha-de-Pagamento/blob/main/src/employee/Comissioned.java) logo, a existência dessa subclasse não faz sentido

### Speculative Generality

1) Como o projeto não foi 100% implementado,as classes MyCalendar, Deposit, CheckHand e CheckMail não possuem uma função muito clara. A classe MyCalendar possui nada e as outras possuem apenas alguns atributos, seus construtores e métodos get e set genéricos

### Data Class

1) As classes Deposit, CheckHand e CheckMail possuem apenas atributos, construtores e métodos getters e setters para esses atributos.

2) As classes Timecard e Sale servem apenas para guardar os dados.

## Solução para os Code Smells encontrados

### Duplicated Code

1) Para o problema de leitura, foi criada a Classe [Input](https://github.com/samurollie/Folha-de-Pagamento-refatorada/blob/main/src/utilities/Input.java), utilizando o Padrão "Extract Method":

```java
public class Input {
    private static Scanner input = new Scanner(System.in);

    public static int readInt() {
        int n = input.nextInt();
        input.nextLine();
        return n;
    }

    public static double readDouble() {
        double n = input.nextDouble();
        input.nextLine();
        return n;
    }

    public static String readString() {
        String n = input.nextLine();
        return n;
    }    
}
```

Alguns exemplos:

- ANTES ([Main.java](https://github.com/samurollie/Folha-de-Pagamento/blob/0309bd8fd4d68166938542cb3882632fcea88574/src/Main.java#L45))
```java
int cmd = input.nextInt();
input.nextLine();
```

- DEPOIS
```java
int cmd = Input.readInt();
```

---
- ANTES ([Syndicate.java](https://github.com/samurollie/Folha-de-Pagamento/blob/0309bd8fd4d68166938542cb3882632fcea88574/src/syndicate/Syndicate.java#L42))

```java
double value = input.nextDouble();
input.nextLine();
```

- DEPOIS
```java
double value =  Input.readDouble();
```

2) Aqui, as partes dos métodos das subclasses que coincidiam com o método da super classe foram substituidas por uma chamada do método da super classe.

- ANTES (Hourly.Java)

```java
@Override
    public String showEmployeeInfo() {
        return "----------\n"+ 
        "Nome:" + this.name + 
        "\nEndereço:" + this.address + 
        "\nCard:" + this.card + 
        "\nMétodo de pagamento:" + this.paymentMethod + 
        "\nTipo: Horista"+
        "\nSalario/Hora:" + this.hourSalary +
        "\n----------";
    }
```
- DEPOIS (Hourly.Java):

```java
    @Override
    public String showEmployeeInfo() {
        return super.showEmployeeInfo() +
        "\nTipo: Horista"+
        "\nSalario/Hora:" + this.hourSalary +
        "\n----------";
    }
```

- ANTES (Salaried.Java):
```java
 @Override
    public String showEmployeeInfo() {
        return "----------\n"+ 
        "Nome:" + this.name + 
        "\nEndereço:" + this.address + 
        "\nCard:" + this.card + 
        "\nMétodo de pagamento:" + this.paymentMethod + 
        "\nTipo: " + this.getType() +
        "\nSalario:" + this.salary +
        "\nPorcentagem de Comissão: " + this.comissionPercentage + "(" + this.getType() + ")" +
        "\n----------";
    }
```
- DEPOIS:

```java
    @Override
    public String showEmployeeInfo() {
        return super.showEmployeeInfo() + 
        "\nTipo: " + this.getType() +
        "\nSalario:" + this.salary +
        "\nPorcentagem de Comissão: " + this.comissionPercentage + "(" + this.getType() + ")" +
        "\n----------";
    }
```

3) Aqui foi aplicado o Padrão **State**. A interface [PaymentMethod](https://github.com/samurollie/Folha-de-Pagamento-refatorada/blob/main/src/payment/methods/PaymentMethod.java) foi criada e as classes Deposit, HandCheck e HandMail, que antes não tinham função passaram a implementar essa interface, solucionando o Code Smell Speculative Generality e Data Class

- ANTES (Employee.java):
```java
package src.employee;

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
}
```


### Feature Envy

1) Aqui foi aplicado o padrão **Extract Method**, ou seja, as Funções foram levadas cada uma para sua respectiva Classe de interesse.

- ANTES (Employee.Java)
```java
    public Hourly SalariedToHourly (Salaried employee) {
        System.out.println("Insira o valor do coeficiente salario/hora: ");
        double hourSalary = input.nextDouble();
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

    public Hourly ComissionedToHourly (Comissioned employee) {
        System.out.println("Insira o valor do coeficiente salario/hora: ");
        double hourSalary = input.nextDouble();
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
        double salary = input.nextDouble();
        int method;
        if (employee.paymentMethod == "hand") {
            method = 1;
        } else if (employee.paymentMethod == "deposit") {
            method =  2;
        } else {
            method = 3;
        }

        return new Salaried(employee.name, employee.address, employee.card, method, salary);
    }

    public Salaried ComissionedToSalaried (Comissioned employee) {
        System.out.println("O salário atual é: " + employee.getSalary() + " deseja manter?");
        System.out.println("(1) - Sim");
        System.out.println("(2) - Não");
        int ans = input.nextInt();
        double newSalary;
        if (ans == 1) {
            newSalary = employee.getSalary();
        } else {
            System.out.println("Qual será o novo salário?");
            newSalary = input.nextDouble();
        }

        int method;
        if (employee.paymentMethod == "hand") {
            method = 1;
        } else if (employee.paymentMethod == "deposit") {
            method =  2;
        } else {
            method = 3;
        }

        return new Salaried(employee.name, employee.address, employee.card, method, newSalary);
    }

    public Comissioned SalariedToComissioned (Salaried employee) {
        System.out.println("O salário atual é: " + employee.getSalary() + " deseja manter?");
        System.out.println("(1) - Sim");
        System.out.println("(2) - Não");
        int ans = input.nextInt();
        double newSalary;
        if (ans == 1) {
            newSalary = employee.getSalary();
        } else {
            System.out.println("Qual será o novo salário?");
            newSalary = input.nextDouble();
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
        double taxa = input.nextDouble();

        return new Comissioned(employee.name, employee.address, employee.card, method, newSalary, taxa);
    }

    public Comissioned HourlyToComissioned (Hourly employee) {
        System.out.println("Qual será o salário inicial?");
        double salary = input.nextDouble();
        int method;
        if (employee.paymentMethod == "hand") {
            method = 1;
        } else if (employee.paymentMethod == "deposit") {
            method =  2;
        } else {
            method = 3;
        }

        System.out.println("Qual a taxa de comissão?");
        double taxa = input.nextDouble();
        return new Comissioned(employee.name, employee.address, employee.card, method, salary, taxa);
    }
```

- DEPOIS (Hourly.java)
```java
public Salaried toSalaried () {
    System.out.println("Qual será o salário inicial?");
    double salary = Input.readDouble();

    return new Salaried(this.name, this.address, this.card, this.getPaymentMethod(), salary, 1);
}

public Salaried toComissioned () {
    System.out.println("Qual será o salário inicial?");
    double salary =  Input.readDouble();

    System.out.println("Qual a taxa de comissão?");
    double taxa =  Input.readDouble();

    return new Salaried(this.name, this.address, this.card, this.getPaymentMethod(), salary, taxa);
}
```
- DEPOIS (Salaried.Java)
```java
public Hourly toHourly () {
    System.out.println("Insira o valor do coeficiente salario/hora: ");
    double hourSalary =  Input.readDouble();
    
    return new Hourly(this.name, this.address, this.card, this.getPaymentMethod(), hourSalary);
}

public Salaried toSalaried () {
    return new Salaried(this.name, this.address, this.card, this.getPaymentMethod(), changeSalary(), 1);
}

public Salaried toComissioned () {
    System.out.println("Qual a taxa de comissão?");
    double taxa =  Input.readDouble();

    return new Salaried(this.name, this.address, this.card, this.getPaymentMethod(), changeSalary(), taxa);
}
```

### Lazy Class

1) As classes foram unidas, aplicando o padrão **Extract Class** e tornando-as uma só:

- ANTES: ([Salaried](https://github.com/samurollie/Folha-de-Pagamento/blob/main/src/employee/Salaried.java) e [Comissioned](https://github.com/samurollie/Folha-de-Pagamento/blob/main/src/employee/Comissioned.java))

- DEPOIS
```java
package src.employee;

import java.util.ArrayList;
import java.util.Date;

import src.utilities.Sale;

public class Salaried extends Employee{
    private double salary;
    private double comissionPercentage;
    private ArrayList<Sale> sales = new ArrayList<Sale>();

    public Salaried(String name, String adress, int card, int paymentMethod, double salary, double comissionPercentage) {
        super(name, adress, card, paymentMethod);
        this.setSalary(salary);
        this.comissionPercentage = comissionPercentage;
    }

    public void addSale(Date date, double value, String description, int employeeId) {
        Sale sale = new Sale(date, value, description, employeeId); 
        this.sales.add(sale);
    }

    public void showSales() {
        System.out.println("Exibindo as vendas feitas por " + this.name);
        System.out.println("Porcentagem de comissão: " + comissionPercentage + "%");
        for (Sale sale : sales) {
            System.out.println("\n-----------------------\n");
            System.out.println("Data: " + sale.date);
            System.out.println("Descrição: " + sale.description);
            System.out.println("Valor: " + sale.value);
        }
    }

    public double getComissionPercentage() {
        return comissionPercentage;
    }

    public void setComissionPercentage(double comissionPercentage) {
        this.comissionPercentage = comissionPercentage;
    }

    public boolean hasSales() {
        return !sales.isEmpty();
    }

    public ArrayList<Sale> getSales() {
        return this.sales;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    private String getType() {
        return (this.comissionPercentage == 1) ? "Salariado" : "Comissionado";
    }

    @Override
    public String showEmployeeInfo() {
        return "----------\n"+ 
        "Nome:" + this.name + 
        "\nEndereço:" + this.address + 
        "\nCard:" + this.card + 
        "\nMétodo de pagamento:" + this.paymentMethod + 
        "\nTipo: " + this.getType() +
        "\nSalario:" + this.salary +
        "\nPorcentagem de Comissão: " + this.comissionPercentage + "(" + this.getType() + ")" +
        "\n----------";
    }
}

```

### Outros
Além das correções citadas acima, alguns erros menores, como métodos e atributos sem utilidade e insconcistências no código foram corrigidos