package Vetores;

import java.util.ArrayList;
import java.util.Arrays;

class Cliente{
    int id;
    String fone;
    String nome;
    
    public Cliente(String nome, String fone, int id){
        this.nome = nome;
        this.fone = fone;
        this.id = id;
    }
    
    public String toString(){
        return " "+nome+":"+fone+"";
    }
}

class Sala{
    int qtdAssentos;
    ArrayList<Cliente> cliente;
    
    public Sala(int qtdAssentos){
        this.qtdAssentos = qtdAssentos;
        this.cliente = new ArrayList<>();
        for(int i=0; i<qtdAssentos; i++){
            this.cliente.add(new Cliente("-", "", i));
        }
    }
    
    public void reservar(Cliente pessoa){
        int i = pessoa.id;
        if(i>=0 && i<cliente.size()){
            for(int k=0; k<cliente.size(); k++){
                if(pessoa.nome.equals(cliente.get(k).nome)){
                    System.out.println("fail: cliente ja esta no cinema");
                    return;
                }
            }
            if(i==cliente.get(i).id && cliente.get(i).nome!="-"){
                System.out.println("fail: cadeira ja esta ocupada");
                return;
            }else{
                cliente.set(i, pessoa);
                System.out.println("Reservado");
                return;
            }
        }
        System.out.println("NAO Reservado");
        return;
    }
    
    public boolean cancelar(String nome){
        for(int i=0; i<cliente.size(); i++){
            if(nome.equals(cliente.get(i).nome)){
                cliente.set(i,new Cliente("-", "", i));
                System.out.println("Cancelado");
                return true;
            }
        }
        System.out.println("fail: cliente nao esta no cinema");
        return false;
    }
    
    public void saida(){
        System.out.print("[");
        for(int i=0; i<cliente.size(); i++){
            System.out.print(cliente.get(i));
        }
        System.out.print("]");
        System.out.println("");
        //System.out.println(Arrays.asList(cliente));
    }
}


public class Cinema {
    public static void main(String[]args){
        Sala sala = new Sala(4);
        sala.saida();
        
        sala.reservar(new Cliente("davi", "3232", 0));
        sala.reservar(new Cliente("joao", "3131", 3));
        sala.saida();
        sala.reservar(new Cliente("rute", "3030", 0));
        sala.reservar(new Cliente("davi", "3234", 2));
        sala.cancelar("davi");
        sala.saida();
        sala.cancelar("rita");
        sala.saida();
    }
}
