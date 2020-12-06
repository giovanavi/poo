package calango;
import java.util.Scanner;

public class Calango {
    //Atributos
    int bucho;
    int maxBucho;
    int nPernas;
    boolean alive;
    
    //Construtor
    Calango(int maxBucho, boolean vivo){//paramametros
        this.bucho = maxBucho; //atributos
        this.maxBucho = maxBucho;
        this.nPernas = 4;
        this.alive = vivo; 
    }
    
    void comer(int quant, boolean vivo){
        if(nPernas<2 && bucho == 0){
            alive = false;
            vivo = false;
            System.out.println("Não tenho mais energia e nem pernas para procurar comida.\nGame Over");
            return;
        }
        if(bucho<maxBucho){
            if(maxBucho<quant){
                System.out.println("Comi apenas "+maxBucho);
                bucho = maxBucho;
                return;
            }else if((bucho+quant)>maxBucho){
                int aux = bucho;
                bucho = maxBucho - aux;
                System.out.println("Comi apenas "+bucho+" já estou cheio");
                bucho += aux;
                return;
            }   
            bucho += quant;
            System.out.println("Comi");
        }else{
            System.out.println("Estou cheio");
        }
    }
    
    void andar(int dist, boolean vivo){
        if(nPernas<2){
            if(bucho==0){
                alive = false;
                vivo = false;
                System.out.println("Não consigo andar e perdi todas minhas energias.\nGame Over");
                return;
            }
            System.out.println("Não consigo andar");
        }
        if(bucho>0){
            if(bucho<dist){
                System.out.println("Andei só "+bucho);
                bucho = 0;
                return;
            }
            bucho -= dist;
            System.out.println("Passeando");
        }else{
            System.out.println("Tô cansado");
        }
    }
    
    void acidentar(boolean vivo){
        if(nPernas>0){
            nPernas -= 1;
            if(nPernas<2 && bucho==0){
                alive = false;
                vivo = false;
                System.out.println("Não tenho mais energia para me regenerar e não tenho pernas suficientes para ir procurar comida.\nGame Over");
                return;
            }
            System.out.println("Perdi uma perna");
        }else{
            System.out.println("Tô lascado");
        }
    }
    
    void regenerar(boolean vivo){
        if(nPernas == 4){
            System.out.println("Tô de boa");
        }else if(bucho > 0){
            nPernas += 1;
            bucho -= 1;
            if(nPernas<2 && bucho == 0){
                alive = false;
                vivo = false;
                System.out.println("Ainda não consigo me movimentar e estou ssem energia.\nGame Over");
                return;
            }
            System.out.println("Recuperei uma perna");
        }else{
            System.out.println("Não tenho energia para regenerar");
        }
    }
    
    public String toString(){
        return "Bucho: "+bucho+"/"+maxBucho+"   Pernas:"+nPernas+"  "+"Alive: "+alive;       
    }

    public static void main(String[]args){
        Calango calango = new Calango(10,true);
        Scanner scanner = new Scanner(System.in);
        System.out.println(calango);
        
        boolean vivo = true;
        
        while(vivo == true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(line.equals("end")){//equals = "perguntando se a linha é igual a "end""
                break;
            }else if(line.equals("show")){
                System.out.println(calango);
            }else if(ui[0].equals("andar")){
                calango.andar(Integer.parseInt(ui[1]), vivo);
            }else if(line.equals("regenerar")){
                calango.regenerar(vivo);
            }else if(line.equals("acidentar")){
                calango.acidentar(vivo);
            }else if(ui[0].equals("comer")){//ui[0]->a palavra digitada/ui[1]->segunda coisa digitada "comer 4";    
                calango.comer(Integer.parseInt(ui[1]), vivo);
            }else{
                System.out.println("fail: Comando inválido");
            }
        }
    }
}
