package CadastroS12;

import java.util.ArrayList;
import java.util.Arrays;

public class Agencia{
    ArrayList<Conta> contas;
    ArrayList<Cliente> clientes;
    int nextConta;
    int nextCli;
    
    public Agencia(){
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
        this.nextConta = 0;
        this.nextCli = 0;
    }
        
    public Cliente findCliente(String nome){
        for(Cliente var : clientes){
            if(var.id.equals(nome))
                return var;
        }
        return null;
    }
    
    public void addCliente(String nome){
        if(findCliente(nome)!=null){
            throw new IllegalArgumentException();   
        }else{
            Cliente cliente = new Cliente(nome);
            clientes.add(nextCli, cliente);
            nextCli++;
            ContaCorrente cc = new ContaCorrente(nextConta, nome);
            contas.add(nextConta, cc);
            nextConta++;
            ContaPoupanca cp = new ContaPoupanca(nextConta, nome);
            contas.add(nextConta, cp);
            nextConta++;       
            cliente.getContas().addAll(new ArrayList<Conta>(Arrays.asList(cc, cp)));        
        }
    }
    
    public Conta getConta(int id){
        for(Conta var : contas){
            if(id==var.id)
                return var;
        }
        return null;
    }

    
    public void sacar(int idConta,float valor){
        if(idConta<0 && idConta>contas.size()){
            throw new NullPointerException();
        }else{
            getConta(idConta).sacar(valor);
        }
    }
    
    public void depositar(int idConta, float valor){
         if(idConta<0 && idConta>contas.size()){
            throw new NullPointerException();
        }else{
             getConta(idConta).depositar(valor);
        }
    }

    public void transferir(int From, int To, float valor){
        if(From<0 && From>contas.size() && (To<0 && To>contas.size())){
           throw new NullPointerException();
        }else{
            getConta(From).transferir(getConta(To), valor);
        }
    }

    public void attMensalContas(){
        for(Conta contas : contas){
            contas.attMensal();
        }
    }    
    public String toString(){
        String saida = "";
        for(Conta var : contas){
            saida+=var+"\n";
        }
        return saida;
    }

}
