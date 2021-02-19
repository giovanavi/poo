package ContatoS10;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Agenda {
    TreeMap<String, ContatoPlus> contatos;
    
    public Agenda(){
        this.contatos = new TreeMap<>();
    }

    public void addContato(String name, List<Fone> fone){
        ArrayList<Fone> fones = new ArrayList<>(fone); 
        if(!(contatos.containsKey(name))){
            ContatoPlus contato = new ContatoPlus(name);
            for(Fone num : fones)
                contato.addFone(num.id, num.number);
            contatos.put(name, contato);
            System.out.println("contato add");
            return;
        }else{
            //se o contato ja existir, incluir os novos numeros;
            ContatoPlus contato = contatos.get(name);
            for(Fone tele : fones)
                contato.addFone(tele.id, tele.number); 
            contatos.put(name, contato);
            System.out.println("fone add");
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
//    public ArrayList<Contato> search(String pattern){
//        ArrayList<Contato> pess = new ArrayList<>();
//        for(Contato contato : contatos){
//            if(contato.nome.contains(pattern))
//                pess.add(contato);
//        }
//        for(Contato contato : contatos){
//            for(Fone fone : contato.getFones()){
//                if(fone.id.contains(pattern))
//                    pess.add(contato);
//            }
//        }
//        for(Contato contato : contatos){
//            for(Fone fone : contato.getFones()){
//                if(fone.number.contains(pattern))
//                    pess.add(contato);
//            }
//        }
//        return pess;
//    }
    
    
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
        for(Contato contato : contatos.values()){
            saida+= contato+"\n";
        }
        return saida;
    }    
}
