package Vetores;

import java.util.ArrayList;
import java.util.Scanner;

class Espiral{
    String nome;
    int qtd;
    float preco;
    
    public Espiral(String nome, int qtd, float preco){
        this.nome = nome;
        this.qtd = qtd;
        this.preco = preco;
    }
    
    public String toString(){
        return nome+" : "+qtd+" U"+" : "+preco+" RS";
    }    
}    

class Maquina{
    ArrayList<Espiral> espirais;
    float saldoCliente;
    float lucro;
    int maxProdutos;
    
    public Maquina(int qtdEspirais, int maxProdutos){
        this.maxProdutos = maxProdutos;
        this.espirais = new ArrayList<>();
        for(int i=0; i<qtdEspirais; i++){
            this.espirais.add(new Espiral("empty", 0, 0f));
        }
    }
    
    public float getSaldo(){
        return saldoCliente;
    }
    
    public float inserirDinheiro(float valor){
        saldoCliente+=valor;
        return saldoCliente;
    }
    
    public float pedirTroco(){
        float aux = saldoCliente;
        saldoCliente = 0;
        System.out.println("voce recebeu "+aux+" RS");
        return aux;
    }
    
    public boolean comprar(int i){
        if(i>=0 && i<espirais.size()){
            if(espirais.get(i).qtd>0){
                if(saldoCliente>espirais.get(i).preco){
                    espirais.get(i).qtd-=1;
                    saldoCliente-=espirais.get(i).preco;
                System.out.println("voce comprou um "+espirais.get(i).nome);
                return true;
                }
                System.out.println("fail: saldo insuficiente");
                return false;
            }
            System.out.println("fail: aspiral sem produtos");
            return false;
        }
        System.out.println("fail: indice não existente");
        return false;
    }
    
    public boolean alterarEspiral(int i, Espiral espiral){
        if(i>=0 && i<espirais.size()){
            if(espiral.qtd<this.maxProdutos){
                espirais.set(i,espiral);
                //System.out.println("Alterado");
                return true;
            }else{
                System.out.println("fail: limite excedido");
                return false;
            }
        }
        System.out.println("fail: indice nao existe");
        return false;
    }
        
    public boolean limparEspiral(int i){
        if(i>0 && i<espirais.size()){
            espirais.set(i, new Espiral("empty", 0, 0));
            //System.out.println("Excluido");
            return true;
        }
        System.out.println("fail: indice nao existe");
        return false;
    }
    
    
    public void saida(){
        System.out.println("saldo: "+saldoCliente);
        for(int i=0; i<espirais.size(); i++){
            System.out.println(i+"[ "+espirais.get(i)+" ]");
        }
    }
    
    public void comandos(){
        System.out.println("Para encerrar a execução use 'end'\nPara "
                + "mostrar a máquina use 'show'\nPara inserir um produto "
                + "na maquina use 'set'+'indice'+'nome'+'quantidade'+'valor'\n"
                + "Para limpar as informacoes de uma espiral use 'limpar'+'indice'"
                + "\nPara adicionar dinheiro na maquina use 'dinheiro'+'valor'"
                + "\nPara receber o troco use 'troco'"
                + "\nPara comprar use 'comprar'+'indice do produto'");
    }
}


public class JunkFood {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        
        // QTD DE ESPIRAIS + MAX DE PRODUTOS
        System.out.println("Digite 'init'+'qtd de espirais'+'max de produtos' da máquina");
        String init = scanner.nextLine();
        String[] nit = init.split(" ");
        
        Maquina maq = new Maquina(Integer.parseInt(nit[1]), 
                                  Integer.parseInt(nit[2]));
        
        maq.comandos();
        
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(line.equals("end")){
                break;
            }else if(line.equals("show")){
                maq.saida();
            }else if(ui[0].equals("set")){
                maq.alterarEspiral(Integer.parseInt(ui[1]), 
                    new Espiral(ui[2], Integer.parseInt(ui[3]), 
                                       Float.parseFloat(ui[4])));
            }else if(ui[0].equals("limpar")){
                maq.limparEspiral(Integer.parseInt(ui[1]));
            }else if(ui[0].equals("dinheiro")){
                maq.inserirDinheiro(Integer.parseInt(ui[1]));
            }else if(ui[0].equals("troco")){
                maq.pedirTroco();
            }else if(ui[0].equals("comprar")){
                maq.comprar(Integer.parseInt(ui[1]));
            }else{
                System.out.println("fail: comando invalido");
            }
            
        }
    }
}






















