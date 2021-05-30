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

1) Na classe Main, assim como nas classes Employee, EmployeeList, Salaried, Syndicate e SyndicateList, toda vez que um número (Inteiro,double, etc) é lido logo após uma String será lida, o método nextLine() é chamado para a limpeza do buff e a correta leitura da String

*

## Solução para os Code Smells encontrados

### Duplicated Code

Para o problema de leitura, foi criada a Classe Input (ADICIONAR LINK), esta classe contém metodos com o código que antes estava duplicado:

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

(Antes, arquivo main.java linhas 45 e 46)
```java
int cmd = input.nextInt();
input.nextLine();
```

(Depois)
```java
int cmd = Input.readInt();
```

---
(Antes, arquivo syndicate.java, linhas 42 e 43)

```java
double value = input.nextDouble();
input.nextLine();
```

(Depois)
```java
double value =  Input.readDouble();
```