package Vetores;
import java.util.ArrayList;
import java.util.Scanner;

class Operacao{
    int id;
    String descricao;
    float valor;
    float saldoO;
    
    public Operacao(int id, String descricao, float valor, float saldoO){
        this.id = id; 
        this.descricao = descricao;
        this.valor = valor;
        this.saldoO = saldoO;
    }
    
    public String toString(){
        return id+": "+descricao+": "+valor+": "+saldoO;
    }
}

class Conta{
    int idNext;
    float saldoC;
    int numero;
    ArrayList<Operacao> extrato;
    
    public Conta(int numero){
        this.idNext = 0;
        this.numero = numero;
        this.extrato = new ArrayList();
        this.saldoC = saldoC;
    }
    
//    public float setExtrato(float valor){
//        this.saldoC = valor;
//        return saldoC;
//    }
    
    public float getSaldo(){
        return saldoC;
    }
    
    public void addOperacao(String descricao, float valor){
            Operacao operacao = new Operacao(idNext, descricao, valor, getSaldo());
            extrato.add(operacao);
            idNext++;
            return;
    }
    
    public boolean statusConta(int num){
        if(numero==num){
            System.out.println("fail: conta ja existe");
            return false;
        }else{
            System.out.println("conta criada");
            return true;
        }
    }
    
    public boolean deposito(float valor){
        if(valor>=0){
            saldoC+=valor;
            addOperacao("deposito", valor);  
            System.out.println("depositado");
            return true;
        }
        System.out.println("fail: valor invalido");
        return false;
    }
    
    public boolean tarifa(float tarifa){
        saldoC-=tarifa;
        addOperacao("tarifa", -tarifa);
        System.out.println("tarifa descontada");
        return true;
    }
    
    public boolean saque(float valor){
        if(valor<saldoC && valor>=0){
            saldoC-=valor;
            addOperacao("saque", -valor);
            System.out.println("valor sacado");
            return true;
        }   
        System.out.println("fail: saldo insuficiente");
        return false;
    }
    
    public void extornar(int i){
        if(i>=0 && i<=extrato.size()){
            if(extrato.get(i).descricao.equals("tarifa")){
                float aux = extrato.get(i).valor*(-1);
                saldoC+=aux;
                addOperacao("extorno", aux);
                return;
            }
        }
        System.out.println("fail: indice nao existe");
            return;
    }
    
    public void getExtratoN(int qtd){
        int aux = extrato.size()-qtd;
        for(int i=aux; i<extrato.size(); i++)
            System.out.println(extrato.get(i));
//        return true;
    }
    
    public void getExtrato(){
        for(int i=0; i<extrato.size(); i++)
            System.out.println(extrato.get(i));
//        return extrato;
    }
        
    public String toString(){
        return "conta:"+numero+" saldo:"+saldoC;
    }
}

public class Tarifas {
    public static void main(String[]args){
        Conta conta = new Conta(0);
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(line.equals("end")){
                break;
            }else if(line.equals("show")){
                System.out.println(conta);
            }else if(ui[0].equals("init")){
                conta = new Conta(Integer.parseInt(ui[1]));
                conta.addOperacao("abertura", 0);     
            }else if(ui[0].equals("depositar")){
                conta.deposito(Integer.parseInt(ui[1]));
            }else if(ui[0].equals("sacar")){
                conta.saque(Integer.parseInt(ui[1]));
            }else if(ui[0].equals("tarifa")){
                conta.tarifa(Integer.parseInt(ui[1]));
            }else if(ui[0].equals("extrato")){
                conta.getExtrato();
            }else if(ui[0].equals("extratoN")){
                conta.getExtratoN(Integer.parseInt(ui[1]));
            }else if(ui[0].equals("extornar")){
                conta.extornar(Integer.parseInt(ui[1]));
            }else{
               System.out.println("fail: comando invalido");
            }
        }    
    scanner.close();
    }
}
