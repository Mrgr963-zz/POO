package tarifas;
import java.util.*;

class Operacoes {
    int id;
    String desc;
    float valor;
    float saldo;
    
    public Operacoes (int id, String desc, float valor, float saldo) {
        this.id = id;
        this.desc = desc;
        this.valor = valor;
        this.saldo = saldo;
    }
    
    public String toString() {
        return id +": " + desc + ": " + valor + ": " + saldo;
    }
}


class Tarifas {
    int idNext = 0;
    float saldo;
    int numero;
    ArrayList<Operacoes> extrato = new ArrayList<Operacoes>();
    
    void AddOp(String descricao, float valor) {
        Operacoes op = new Operacoes(idNext, descricao, valor, this.saldo);
        idNext += 1;
        extrato.add(op);
    }
    
    void depositar(float valor) {
        if (valor > 0) {
            this.saldo += valor;
            AddOp("deposito", valor);
            System.out.println("Você depositou: " + valor + " || Seu saldo: " + saldo);
        } else {
            System.out.println("Valor Inválido!");
        }
    }

    void Saque(float valor) {
        if (valor > 0 && valor < saldo) {
            this.saldo -= valor;
            AddOp("saque", -valor);
            System.out.println("Você sacou: " + valor + " || Seu saldo: " + saldo);
        } else {
            System.out.println("Valor Inválido!");
        }
    }
    
    void Tarifa(float valor) {
        if (valor > 0 && valor < saldo) {
            this.saldo -= valor;
            AddOp("tarifa", -valor);
            System.out.println("Conta tarifada em: " + valor + " || Seu saldo: " + saldo);
        } else {
            System.out.println("Valor Inválido!");
        }
    }
    
    void Extornar(int id) {
        if (id > 0 && id <= extrato.size() && extrato.get(id).desc.equals("tarifa")) {
            saldo = -(extrato.get(id).saldo);
            AddOp("extorno", id);
            System.out.println("Extorno efetuado || Seu saldo: " + saldo);
        } else {
            System.out.println("ID Inválido!");
        }
    }
    
    void GetExtrato() {
        for (int i = 0; i < extrato.size(); i++) {
            System.out.println(extrato.get(i));
            
        }
    }
    
    void GetExtratoN(int qnt) {
        ArrayList<Operacoes> aux = new ArrayList<Operacoes>();
        aux = extrato;
        Collections.reverse(aux);
        for (int i = 0; i < qnt; i++) {
            System.out.println(extrato.get(i));
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Operacoes op1 = new Operacoes(0,"",0,0);
        Tarifas t1 = new Tarifas();
        
        while(true) {
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("init")) {
                t1 = new Tarifas();
                op1 = new Operacoes(Integer.parseInt(ui[1]), "", 0,0);
            } else if (ui[0].equals("deposito")) {
                t1.depositar(Float.parseFloat(ui[1]));
            } else if (ui[0].equals("saque")) {
                t1.Saque(Float.parseFloat(ui[1]));
            } else if (ui[0].equals("tarifa")) {
                t1.Tarifa(Float.parseFloat(ui[1]));
            } else if (ui[0].equals("extornar")) {
                t1.Extornar(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("extrato")) {
                t1.GetExtrato();
            } else if (ui[0].equals("extratoN")) {
                t1.GetExtratoN(Integer.parseInt(ui[1]));
            } else {
                System.out.println("Comando Inválido!");
            }
        }
    }
}
