package Vetores;

import java.util.ArrayList;
import java.util.Scanner;

class Pass{
    String id;
    int idade;
    
    public Pass(String nome, int idade){
        this.id = nome;
        this.idade = idade;
    }
    
    public String toString(){
        return id+":"+idade;
    }
}

public class Topic {
    ArrayList<Pass> cadeiras;
    int qtdPref;
    
    public Topic(int lotacao, int qtdPref){
        this.qtdPref = qtdPref;
        this.cadeiras = new ArrayList<>();
        for(int i=0; i<lotacao; i++){
            cadeiras.add(i, null);
        }
    }
    
    public boolean idoso(int i){
        if(i>=50){
            return true;
        }
        return false;
    }    
    
    public boolean subir(Pass pass){       
        for(Pass passa: cadeiras){
            if(passa!=null && passa.id.equals(pass.id)){
                System.out.println("fail: pass ja esta na topic");
                return false;
            }    
        }
        if(pass.idade>=65){
            for(int i=0; i<this.qtdPref; i++){
                if(cadeiras.get(i)==null){
                    cadeiras.set(i, pass);
                    System.out.println("Idoso cadeira preferencial");
                    return true;
                }
            }
            for(int k=this.qtdPref; k<cadeiras.size();k++){
                if(cadeiras.get(k)==null){
                    cadeiras.set(k, pass);
                    System.out.println("Idoso cadeira normal");
                    return true;
                }   
            }     
        }else if(pass.idade>=65){
            for(int k=this.qtdPref; k<cadeiras.size();k++){
                if(cadeiras.get(k)==null){
                    cadeiras.set(k, pass);
                    System.out.println("Jovem cadeira normal");
                    return true;
                }    
            }
            for(int i=0;i<qtdPref;i++){
                if(cadeiras.get(i)==null){
                    cadeiras.set(i, pass);
                    System.out.println("Jovem cadeira preferencial");
                    return true;
                }
            }
        }
        System.out.println("fail: topic lotada");
        return false; 
    }
        
    
    public Pass descer(String id){
        for(int i=0; i<cadeiras.size(); i++){
            if(cadeiras.get(i)!=null && cadeiras.get(i).id.equals(id)){
                cadeiras.set(i,null);
                System.out.println("desembarque");
                return cadeiras.get(i);
            }
        }
        System.out.println("fail: pass nao esta na topic");
        return null;
    }
    
    public String toString(){
        String saida = "[ ";
        int aux;
        for(int i=0; i<qtdPref; i++){
            if(cadeiras.get(i) == null)
                saida += "@ ";
            else
                saida += "@"+cadeiras.get(i)+" ";
        }
        for(aux = qtdPref; aux<cadeiras.size(); aux++){
            if(cadeiras.get(aux) == null)
                saida += "= ";
            else
                saida += "="+cadeiras.get(aux)+" ";
        }
        return saida+"]";        
    }
    
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Para iniciar digite: init+'qtd de "
                + "cadeiras'+'qts de cadeiras preferenciais'");
        
        String init = scanner.nextLine();
        String[] nit = init.split(" ");
        Topic pass = new Topic(Integer.parseInt(nit[1]), 
                               Integer.parseInt(nit[2]));
  
        System.out.println("Para encerrar a execução use 'end'\nPara "
                + "mostrar a topic use 'show'\nPara embarcar um "
                + "passageiro use 'in'+'nome'+'idade'\nPara desembarcar um "
                + "um passageiro use 'out'+'nome'");
        
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            
            if(line.equals("end")){
                break;
            }else if(line.equals("show")){
                System.out.println(pass);
            }else if(ui[0].equals("in")){
                pass.subir(new Pass(ui[1], Integer.parseInt(ui[2])));
            }else if(ui[0].equals("out")){
                pass.descer(ui[1]);
            }else{
                System.out.println("fail: comando invalido");
            }
        }
    }
}    
