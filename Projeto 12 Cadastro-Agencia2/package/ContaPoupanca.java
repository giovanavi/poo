package CadastroS12;

public class ContaPoupanca extends Conta{
    
    public ContaPoupanca(int id, String idCliente) {
        super(id, idCliente);
        this.type = "CP";
    }
    
    @Override    
    public void attMensal(){
        this.saldo*=1.01;
    }
}