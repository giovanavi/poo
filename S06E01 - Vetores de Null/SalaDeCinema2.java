package Vetores;

import java.util.ArrayList;
import java.util.Arrays;

class Cliente{
    String fone;
    String nome;
    
    public Cliente(String nome, String fone){
        this.nome = nome;
        this.fone = fone;
    }
    
    public String toString(){
        return " "+nome+":"+fone+"";
    }
}

class Sala{
    ArrayList<Cliente> clientes;
    
    public Sala(int qtdAssentos){
        this.clientes = new ArrayList<>();
        Cliente cliente = null;
        for(int i=0; i<qtdAssentos; i++){
            this.clientes.add(cliente);
        }
    }
    
    public void reservar(Cliente pessoa, int index){
        //se index é valido
        if(index<0 || index>=clientes.size()){
            System.out.println("fail: indice nao existe");
            return;
        }
        //se nao tem outra pessoa na cadeira
        if(clientes.get(index)!=null){
            System.out.println("fail: cadeira esta ocupada");
        }
        //se essa pessoa ja esta na sala
        for(Cliente cliente : clientes){
            if(cliente!=null && cliente.nome.equals(pessoa.nome)){
                System.out.println("fail: essa pessoa ja esta na sala");
                return;
            }
        }
        System.out.println("Reservado");
        clientes.set(index, new Cliente(pessoa.nome, pessoa.fone));
        return;
            
}
    
    public boolean cancelar(String id){
        for(int i=0; i<clientes.size(); i++){
            if(clientes.get(i) != null && clientes.get(i).nome.equals(id)){
                clientes.set(i,null);
                System.out.println("reserva cancelada");
                return true;
            }
        }
        System.out.println("fail: cliente nao esta no cinema");
        return false;
    }
    
    public String toString(){
        String saida = "[";
        for(Cliente cliente : clientes){
            if(cliente == null){
                saida += "- ";
            }else{
                saida += cliente + " ";
            }
        }
        return saida+"]";
    }
}


public class Cinema {
    public static void main(String[]args){
        Sala sala = new Sala(4);
        System.out.println(sala);
        
        sala.reservar(new Cliente("davi","7272"), 0);
        sala.reservar(new Cliente("joao","7878"), 3);
        sala.reservar(new Cliente("maria","7878"), 2);
        System.out.println(sala);
        sala.reservar(new Cliente("rute","2020"),0);
        sala.reservar(new Cliente("davi","0909"),2);
        sala.cancelar("davi");
        sala.cancelar("rute");
        System.out.println(sala);
        sala.reservar(new Cliente("giovana","9876"), 1);
        sala.cancelar("rita");
        System.out.println(sala);
    }
}
