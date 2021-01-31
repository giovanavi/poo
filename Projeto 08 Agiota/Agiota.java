package Maps;

import java.util.ArrayList;
import java.util.TreeMap;

class Transaction{
    int id;
    String codename;
    float valor;
    
    public Transaction(int id, String codename, float valor){
        this.id = id;
        this.codename = codename;
        this.valor = valor;
    }
    
    @Override
    public String toString(){
        return ""+id+":"+codename+":"+valor;
    }
}

class Cliente{
    String codinome;
    float limite;
    float balance;
    
    public Cliente(String codinomeC, float limite){
        this.codinome = codinomeC;
        this.limite = limite;
        this.balance = balance;
    }
    
    public String toString(){
        return ""+codinome+" "+balance+"/"+limite;
    }
}

public class Agiota {
    int nextId;
    float balance;
    TreeMap<String, Cliente> repCliente;
    TreeMap<Integer, Transaction> repTrans;
    
    //TreeMap ou HashMap
    //TreeMap -> Arvore -> Chave ordenavel -> Menos memoria -> menor desempenho
    //HashMap -> Hash -> Chave Hashavel -> Mais memoria -> melhor desempenho
    
    public Agiota(float balance){
        this.balance = balance;
        this.repCliente = new TreeMap<>();
        this.repTrans = new TreeMap<>();
        this.nextId = 0;
    }
    
    public void addCli(String codename, float limite){
        if(this.repCliente.containsKey(codename)){
            System.out.println("fail: usuario existente");
            return;
        }
        this.repCliente.put(codename, new Cliente(codename, limite));
        return ;
    }
    
    public void lend(String codename, float valor){
        Cliente cliente = repCliente.get(codename);
        if(cliente == null){
            System.out.println("fail: cliente nao existe");
        }else if(this.balance < valor){
            System.out.println("fail: agiota sem dinheiro");
        }else if(valor > (cliente.limite-cliente.balance)){
            System.out.println("fail: limite de cliente excedido");
        }else{
            this.balance-= valor;
            cliente.balance+=valor;
        }
    }
    
    public void addTrans(String codename, float valor){
        this.repTrans.put(this.nextId, new Transaction(this.nextId, codename, valor));
        this.nextId++;
    }
    
    public void kill(String codename){
        if(this.repCliente.containsKey(codename)){
            this.repCliente.remove(codename);
            ArrayList<Integer> idToRemove = new ArrayList<>();
            for(Transaction trans : this.repTrans.values())
                if(trans.codename.equals(codename))
                    idToRemove.add(trans.id);
            for(Integer i : idToRemove)
                this.repTrans.remove(i);
        }
    }
    
    public void receive(String codename, float valor){
        Cliente cliente = repCliente.get(codename);
        if(cliente == null){
            System.out.println("fail: cliente nao existe");
        }else if(valor > cliente.balance){
            System.out.println("fail: muito dineheirp");
        }else{
            this.balance+= valor;
            cliente.balance-=valor;
            this.addTrans(codename, valor);
        }
    }
    
    public void juros(){
        for(Cliente cliente : this.repCliente.values()){
            cliente.balance*=1.10;
        }
    }
    
    @Override
    public String toString(){
        StringBuilder saida = new StringBuilder();
        saida.append("Clientes\n");
        for(Cliente client : this.repCliente.values())
            saida.append("- "+client+"\n");
        saida.append("Transacoes\n");
        for(Transaction trans : this.repTrans.values())
            saida.append("- "+trans+"\n");
        return saida.toString();
    }
    
    public static void main(String[]args){
        Agiota ag = new Agiota(500);
       
        ag.addCli("maria", 500);
        ag.addCli("josue", 600);
        ag.addCli("maria", 300); //fail

        ag.lend("maria", 300);
        ag.lend("josue", 50);
        ag.lend("maria", 100);

        System.out.println(ag); //check

        ag.lend("bruno", 30);//fail
        ag.lend("maria", 60);//fail
        ag.lend("josue", 30);//fail

        System.out.println(ag); //check

        ag.receive("maria", 1000);//fail
        ag.receive("maria", 350);
        ag.receive("josue", 1);
        ag.receive("maria", 10);

        System.out.println(ag); //check

        ag.kill("maria");

        System.out.println(ag); //check

    }
}
