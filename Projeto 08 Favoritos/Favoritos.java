package Maps;
   
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

class Fone{
    String number;
    String id;
    
    public Fone(String id, String number){
        this.id = id;
        this.number = number;
    }
    public Fone(String serial){
        String vetor[] = serial.split(":");
        id = vetor[0];
        number = vetor[1];
    }
    public static boolean validade(String number){
        String validos = "123456789()-";
        for(int i=0; i<number.length(); i++){
            char c = number.charAt(i);
            if(validos.indexOf(c)==-1){
                return false;
            }
        }
        return true;
    }
    
    public String toString(){
        return id+":"+number;
    }
}

class Contato implements Comparable<Contato>{
    String nome;
    public ArrayList<Fone> fones;
    boolean starred;
    
    public Contato(String nome){
        this.nome = nome;
        this.fones = new ArrayList<>();
        this.starred = false;
    }
    
    public boolean addFone(String id, String number){
        if(Fone.validade(number)){
            fones.add(new Fone(id, number));
            return true;
        }
        return false;
    }
    
    public boolean rmFone(int index){
        if(index>=0 && index<=fones.size()){
            fones.remove(index);
            return true;
        }
        return false;
    }
    
    public void setBookmark(String value){
        
    }
    
    public String getName(){
        return nome;
    }
    
    public ArrayList<Fone> getFones(){
        return fones;
    }

    public String toString(){
        String saida = "";
        if(starred == true){
            saida = "@ "+getName();
        }else{
            saida = "- "+getName();
        }
        for(int i=0 ;i<fones.size(); i++){
            saida+= "{"+i+":"+fones.get(i)+"]";
        }
        return saida;
    }

    @Override
    public int compareTo(Contato o) {
        return nome.compareTo(o.getName());
    }
}

class Agenda{
    ArrayList<Contato> contatos;
    TreeMap<String, Contato> bookmarks;
    
    public Agenda(){
        this.contatos = new ArrayList<>();
        this.bookmarks = new TreeMap<>();
    }
    
    public int findContato(String id){
        for(int i=0; i<contatos.size(); i++){
            if(contatos.get(i).getName().equals(id)){
                System.out.println("achei "+id);
                return i;
            }
        }
        System.out.println("nao achei "+id);
        return -1;
    }
    
    public void addContato(String name, List<Fone> fone){
        ArrayList<Fone> fones = new ArrayList<>(fone);
        //int aux = findContato(name);
        if(findContato(name)==-1){
            Contato contato = new Contato(name);
            for(Fone num : fones){
                contato.addFone(num.id, num.number);
            }
            contatos.add(contato);
            System.out.println("contato add "+name);
            Collections.sort(contatos);
            return;
        }
        System.out.println("contato ja existe "+name);
        Collections.sort(contatos);
    }

    public boolean rmContato(String nome){
        int aux = findContato(nome);
        Contato contato = contatos.get(aux);
        if(aux==-1){
            System.out.println("fail: contato nao encontrado");
            return false;
        }
        //Contato contato = contatos.get(aux);
        if(contato.starred == true){
            bookmarks.remove(nome);
            contatos.remove(aux);
            System.out.println("contato removidofav");
            return true;
        }
        contatos.remove(aux);
        System.out.println("contato removido");
        return true;
    }
    
    public ArrayList<Contato> search(String pattern){
        ArrayList<Contato> pess = new ArrayList<>();
        for(Contato contato : contatos){
            if(contato.nome.contains(pattern))
                pess.add(contato);
        }
        for(Contato contato : contatos){
            for(Fone fone : contato.getFones()){
                if(fone.id.contains(pattern))
                    pess.add(contato);
            }
        }
        for(Contato contato : contatos){
            for(Fone fone : contato.getFones()){
                if(fone.number.contains(pattern))
                    pess.add(contato);
            }
        }
        return pess;
    }
    
    public Contato getContato(String name){
        for(Contato conta : contatos){
            if(conta.nome.equals(name)){
                return conta;
            }
        }
        return null;
    }
    
    public ArrayList<Contato> getContatos(){
        return contatos;
    }
    
    public void bookmark(String id){
        Contato contato = getContato(id);
        int aux = findContato(id);
        if(aux!=-1){
            if(contato.starred == false){
                contato.starred = true;
                bookmarks.put(id, contato);
                System.out.println("favoritado");
                return;
            }else{
                System.out.println("ja eh favorito");
                return;
            }
        }
        System.out.println("contato nao existente");
    }
    
    public void unBookmark(String id){
        Contato contato = getContato(id);
        if(contato.starred == true){
            contato.starred = false;
            bookmarks.remove(id);
            System.out.println("removido");
            return;
        }
        System.out.println("nao eh favorito");
    }
    
    public ArrayList<Contato> getBookmarks(){
        ArrayList<Contato> fav = new ArrayList<>();
            for(Contato contato : bookmarks.values()){
                fav.add(contato);
            }
        return fav;
    }

    public boolean rmFone(String id, int index){
        int aux = findContato(id);
        if(aux == -1){
            System.out.println("fail: contato nao encontrado");
            return false;
        }
        Contato contato = contatos.get(aux);
        if(contato.rmFone(index))
            return true;
        return false;
    }

    public String toString(){
        String saida = "";
        for(Contato contato : contatos){
            saida+= contato+"\n";
        }
        return saida;
    }       
}

public class Favoritos{
    public static void main(String[]args){
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
            
        while(true){
            String line = scanner.nextLine();
            String[] in = line.split(" ");
            
            if(line.equals("end")){
                break;
            }else if(line.equals("show")){
                System.out.println(agenda);
            }else if(in[0].equals("add")){
                ArrayList<Fone> fones = new ArrayList<Fone>();
                for(int i=2; i<in.length; i++){
                    fones.add(new Fone(in[i]));
                }
                agenda.addContato(in[1], fones);
            }else if(in[0].equals("rmFone")){
                agenda.rmFone(in[1], Integer.parseInt(in[2]));
            }else if(in[0].equals("rmContato")){
                agenda.rmContato(in[1]);
            }else if(in[0].equals("search")){
                for(Contato contato : agenda.search(in[1])){
                    System.out.println(contato);
                }
            }else if(in[0].equals("star")){
                agenda.bookmark(in[1]);
            }else if(in[0].equals("unstar")){
                agenda.unBookmark(in[1]);
            }else if(in[0].equals("starred")){
                for(Contato fav : agenda.getBookmarks()){
                    System.out.println(fav);
                }
            }else{
                System.out.println("fail: comando invalido");
            }
        }
    }
}