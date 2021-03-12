package CadastroS12;


public class ContaCorrente extends Conta{
    
    public ContaCorrente(int id, String idCliente) {
        super(id, idCliente);
        this.type = "CC";
    }
    
    @Override
    public void attMensal(){
        this.saldo -= 20;
    }
}