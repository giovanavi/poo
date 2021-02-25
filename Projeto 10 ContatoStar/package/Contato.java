package ContatoS10;

import java.util.ArrayList;
import java.util.List;

public class Contato{
    String nome;
    public ArrayList<Fone> fones;
    
    public Contato(String nome, List<Fone> fones){
        this.nome = nome;
        this.fones = new ArrayList<>(fones);
    }
    
    public boolean addFone(String id, String number){
        if(!(Fone.validade(number))){
           throw new IllegalArgumentException("fail: fone informado é inválido");
        }
        fones.add(new Fone(id, number));
        return true;        

    }
    
    public boolean rmFone(int index){
        if(index<0 && index>fones.size()){
            throw new IndexOutOfBoundsException();
        }
        fones.remove(index);
        return true;
    }
    
    public String getName(){
        return nome;
    }
    
    public ArrayList<Fone> getFones(){
        return fones;
    }

    @Override
    public String toString(){
        String saida = "- "+getName();
        int i=0;
        for(Fone fone : fones){
            saida += "["+i+":"+fone.toString()+"]";
            i++;
        }
        return saida;
    }
}
