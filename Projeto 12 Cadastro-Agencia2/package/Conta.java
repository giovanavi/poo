package CadastroS12;

public class Conta{
    int id;
    float saldo;
    String idCliente;
    String type;
    
    public Conta(int id, String idCliente){
        this.id = id;
        this.idCliente = idCliente;
        this.saldo = 0;
    }
    
    public void sacar(float valor){
        if(valor<0 || valor>this.saldo)
            throw new IllegalArgumentException();
        else    
            this.saldo-=valor;
    }
    
    public void depositar(float valor){
        if(valor<0){
            System.out.println();
            throw new IllegalArgumentException();
        }else{
            this.saldo+=valor;
        }
    }
    
    public void transferir(Conta other, float valor){
        if(valor<this.saldo || valor>0){
            sacar(valor);
            other.depositar(valor);
        }else{
            System.out.println();
            throw new IllegalArgumentException();
            
        }
    }
    
    public void attMensal(){
    }

    @Override
    public String toString() {
        return id+":"+idCliente+":"+saldo+":"+type;
    }
}
