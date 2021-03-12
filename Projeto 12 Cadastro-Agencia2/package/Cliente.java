package CadastroS12;

import java.util.ArrayList;

public class Cliente{
    String id;
    ArrayList<Conta> contas;
    
    public Cliente(String id){
        this.id = id;
        this.contas = new ArrayList<>();
    }
    
    public ArrayList<Conta> getContas(){
        return contas;
    }    
    
    @Override
    public String toString(){
        String saida = "";
        for(Conta contas: contas){
            saida += contas.id+":"+contas.idCliente+":"+contas.saldo+":"+contas.type+"\n";
       }
        return saida;
    }
}