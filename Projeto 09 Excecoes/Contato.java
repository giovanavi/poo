package Vetores;
import java.util.ArrayList;
import java.util.Scanner;

class Fone{
    String label;
    String number;
    
    public Fone(String label, String number){
        this.label = label;
        this.number = number;
    }
  
    public static boolean validade(String number){
        String validos = "0123456789()-";
        for(int i=0; i<number.length(); i++){
            char c = number.charAt(i);//.charAt -> caractere que está
            //na posição i
            if(validos.indexOf(c)==-1){//.indexOf -> função de busca 
//dentro de vetores, diz qual a posição da string, se ele encontrar 
//ele retorna a posição, se não ele retorna -1
                return false;
            }
        }
        return true;
    }
    
    public String toString(){
        return label+":"+number;
    }
}

public class Contato{
    String nome;
    ArrayList<Fone> fones;
    int nextFone = 0;
    
    public Contato(String nome){
        this.nome = nome;
        this.fones = new ArrayList<>();
    }
    
    
    public void addFone(String label, String number){
        if(Fone.validade(number)){
            fones.add(nextFone,new Fone(label, number));
            nextFone ++;
            return;
        }
        throw new IllegalArgumentException();
    }
    
    //pega o fone pelo indice
    public Fone getFone(int index){       
        if(index>=0 && index<fones.size()){//verifica se o index é válido
            return fones.get(index);
        }
        //nao encontra o fone procurado por indice
        throw new NullPointerException();
    }
    
    //pega o fone pela label/operadora
    public Fone getFone(String label){
        for(Fone fone: fones){
            if(fone.label.equals(label)){
                return fone;
            }
        }    
        //nao encontra o fone procurado pelo nome
        throw new NullPointerException();
    }
    //remover pelo indice
    public boolean rmFone(int index){
        if(index>=0 && index<fones.size()){//verifica se o index é válido
            fones.remove(index);
            return true;
        }
        //nao encontra o indice do fone e nao remove
        throw new NullPointerException();
    }
    //remover pelo numero
    public boolean rmFone(String number){
        for(Fone fone : fones){
            if(fone.number.equals(number)){
                fones.remove(fone);
                return true;
            }
        }
        //nao encontra o fone e nao remove
        throw new NullPointerException();
    }
    
    public String toString(){
        String saida = "- "+this.nome;
        //int i=0;
        for(int i=0; i<fones.size(); i++){
            saida += "["+i+":"+fones.get(i)+"]";
            //i++;
        }
        return saida;
    }
    
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        
        String init = scanner.nextLine();
        String[] ui = init.split(" ");
        Contato contato = new Contato(ui[1]);
        
        while(true){
            String line = scanner.nextLine();
            String[] in = line.split(" ");
            if(line.equals("end")){
                break;
            }else if(line.equals("show")){
                System.out.println(contato);
            }else if(in[0].equals("add")){
                try{
                    contato.addFone(in[1], in[2]);
                }catch(IllegalArgumentException e){
                    System.out.println("fail: fone invalido para cadastro");
                }
            }else if(in[0].equals("rm") && in[1].equals("ind")){
                try{
                    contato.rmFone(Integer.parseInt(in[2]));
                }catch(NullPointerException n){
                    System.out.println("fail: indice do fone digitado nao existe");
                }
            }else if(in[0].equals("rm") && in[1].equals("number")){
                try{
                    contato.rmFone(in[2]);
                }catch(NullPointerException n){
                    System.out.println("fail: fone digitado nao existe");
                }
            }else{
                System.out.println("fail: comando invalido");
            }
        }
    scanner.close();
    }
}
