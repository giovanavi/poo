package ContatoS10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Agenda {
    TreeMap<String, ContatoPlus> contatos;
    
    public Agenda(){
        this.contatos = new TreeMap<>();
    }

    public void addContact(Contato contato){
        ArrayList<Fone> numeros = new ArrayList<>(contato.fones);
        if(!(contatos.containsKey(contato.nome))){
            ContatoPlus cont = new ContatoPlus(contato.nome, contato.fones);
            contatos.put(cont.nome, cont);
            System.out.println("contato add");
            return;
        }else{
            //se o contato ja existir, incluir novos fones
            ContatoPlus cont = getContato(contato.nome);
            for(Fone fone : numeros)
                cont.addFone(fone.id, fone.number);
            contatos.put(cont.nome, cont);  
            System.out.println("fone add");
            return;
        }
    }
        
    public boolean rmContato(String nome){
        if(!(contatos.containsKey(nome))){
            throw new NullPointerException();
        }else{
            contatos.remove(nome);
            System.out.println("contato removido");
            return true;            
        }
    }
    
    public TreeMap<String, Contato> search(String pattern){
        TreeMap<String, Contato> pess = new TreeMap<>();
        for(Contato contato : contatos.values()){
            if(contato.getName().contains(pattern))
                pess.put(pattern, contato);
        }
        for(Contato contato : contatos.values()){
            for(Fone fone : contato.getFones()){
                if(fone.id.contains(pattern))
                    pess.put(pattern, contato);
            }
        }
        for(Contato contato : contatos.values()){
            for(Fone fone : contato.getFones()){
                if(fone.number.contains(pattern))
                    pess.put(pattern, contato);
            }
        }
        
        return pess;
    }
  
    ContatoPlus getContato(String name){
        if(contatos.containsKey(name)){
            return contatos.get(name);
        }
            return null;
    }
    
    public TreeMap<String, ContatoPlus> getContatos(){
        return contatos;
    }

    public boolean rmFone(String id, int index){
        if(!(contatos.containsKey(id))){
            throw new NullPointerException("fail: contato não encontrado");         
        }else{
            ContatoPlus contato = contatos.get(id);
            contato.rmFone(index);
            return true;
        }
    }
    
    @Override
    public String toString(){
        String saida = "";
        for(ContatoPlus contato : contatos.values()){
            saida+= contato+"\n";
        }
        return saida;
    }    
}
