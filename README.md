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

2) O método `ShowEmployeeInfo()` da Classe Employee e das suas subclasses é identico até certo ponto, logo deve ser organizado.

### Feature Envy

1) Os métodos de conversão de Employee, `SalariedToHourly(), ComissionedToHourly(), HourlyToSalaried(), ComissionedToSalaried()` e `SalariedToComissioned()` da classe [Employee](https://github.com/samurollie/Folha-de-Pagamento/blob/0309bd8fd4d68166938542cb3882632fcea88574/src/employee/Employee.java#L66) estão mais interessados nas subclasses do que na superclasse. Esse problema também se encaixa no Code Smell Duplicated Code, pois o codigo entre esses métodos está repitido.

### Lazy Class

1) A classe [Salaried](https://github.com/samurollie/Folha-de-Pagamento/blob/main/src/employee/Salaried.java) possui apenas uma subclasse, [Comissioned](https://github.com/samurollie/Folha-de-Pagamento/blob/main/src/employee/Comissioned.java) logo, a existência dessa subclasse não faz sentido

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

(Antes, arquivo [Main.java](https://github.com/samurollie/Folha-de-Pagamento/blob/0309bd8fd4d68166938542cb3882632fcea88574/src/Main.java#L45))
```java
int cmd = input.nextInt();
input.nextLine();
```

(Depois)
```java
int cmd = Input.readInt();
```

---
(Antes, arquivo [Syndicate.java](https://github.com/samurollie/Folha-de-Pagamento/blob/0309bd8fd4d68166938542cb3882632fcea88574/src/syndicate/Syndicate.java#L42))

```java
double value = input.nextDouble();
input.nextLine();
```

(Depois)
```java
double value =  Input.readDouble();
```

### Feature Envy

1) Aqui foi aplicado o padrão "Extract Method", ou seja, as Funções foram levadas cada uma para sua respectiva Classe de interesse.

(Antes)

(Depois, Hourly.java)
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
(Depois, Salaried.Java)
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

1) As classes foram unidas, tornando-se uma só:

(Antes: [Salaried](https://github.com/samurollie/Folha-de-Pagamento/blob/main/src/employee/Salaried.java) e [Comissioned](https://github.com/samurollie/Folha-de-Pagamento/blob/main/src/employee/Comissioned.java))

(Depois) 
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

