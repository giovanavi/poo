package projeto04;

import java.util.Scanner;

class Tamagotchi{
    //Atributos
    private int energia;  //get e set
    private int maxE;     //get e set
    private int saciedade;  //set 
    private int maxS;      //get e set
    private int limpeza;  //get e set
    private int maxL;     //get e set
    private int diamante; 
    private int idade;
    public boolean alive = true;

    Tamagotchi(int maxE, int maxS, int maxL){
        this.energia = maxE;
        this.maxE = maxE;
        this.saciedade = maxS;
        this.maxS = maxS;
        this.limpeza = maxL;
        this.maxL = maxL;
        this.alive = true;
    }
    
    
    public int getEnergia(){
        return this.energia;
    }
    private void setEnergia(int e){
        if(e+this.energia > maxE){
            this.energia = maxE;
            //System.out.println("Seu pet está cheio de energia");
            return;
        }
        if(e+this.energia <= 0){
            this.energia = 0;
            System.out.println("fail: seu pet morreu de fraqueza");
            this.alive = false;
            return;
        }
        this.energia+=e;
    }
    
    public int getMaxE(){
        return this.maxE;
    }
    public void setMaxE(int valor){
        this.maxE = valor; 
        return;
    }
    
    public int getSaciedade(){
        return this.saciedade;
    }
    private void setSaciedade(int valor){
        if((this.saciedade+valor) > this.maxS){
            this.saciedade = this.maxS;
            //System.out.println("Seu pet está saciado");
            return;
        }
        if(valor+this.saciedade <=0){
            this.saciedade = 0;
            System.out.println("fail: seu pet morreu de fome");
            this.alive = false;
            return;
        }
        this.saciedade +=valor;        
        //System.out.println("Seu pet foi alimentado");
    }
    public int getMaxS(){
        return this.maxS;
    }
    public void setMaxS(int valor){
        this.maxS = valor;
    }
    
    
    
    public int getMaxL(){
        return this.maxL;
    }
    public void setMaxL(int valor){
        this.maxL = valor;
    }
    public int getLimpeza(){
        return this.limpeza;
    }
    private void setLimpeza(int valor){
        if((this.limpeza+valor) > this.maxL){
            this.limpeza = this.maxL;
            //System.out.println("Seu pet está limpo");
            return;
        }if(valor + this.limpeza == 0){
            this.limpeza = 0;
            this.alive = false;
            System.out.println("fail: seu pet morreu de sujeira");
            return;
        }
        this.limpeza +=valor;        
        //System.out.println("Seu pet tomou banho");
    }
    
    private int getIdade(){
        return this.idade;
    }
    private void setIdade(int v){
        this.idade += v;
    }
    
    private int getDiamantes(){
        return this.diamante;
    }
    private void setDiamantes(int v){
        this.diamante+=v;
    }
    
    
    public boolean testMorto(){
        if(this.alive){
            return false;
        }else{
            System.out.println("fail: seu pet está morto");
            return true;
        }
    }
    
    
    
    public void brincar(){
        if(testMorto()){
            return;
        }else{  
            setEnergia(-2);
            setSaciedade(-1);
            setLimpeza(-3);
            setDiamantes(1);
            setIdade(1); 
        }
    }
    
    
    public void comer(){
        if(testMorto()){ 
            return;
        }else{
            setEnergia(-1);
            setSaciedade(4);
            setLimpeza(-2);
            setDiamantes(0);
            setIdade(1);
        }
    }
    
    public boolean sono(){
        if((this.maxE - this.energia) >=5)
            return true;
        else{
            return false;
        }
    }
    
    public void dormir(){
        if(testMorto()){ 
            return;
        }
        if(sono()){
            setIdade(this.maxE-this.energia);
            setEnergia(this.maxE);
            setSaciedade(-1);
            setLimpeza(0);
            setDiamantes(0); 
        }else{
            System.out.println("fail: seu pet não está com sono");   
        }
    }
    
    public void banho(){
        if(testMorto()){ 
            return;
        }else{
            setIdade(2);
            setEnergia(-3);
            setSaciedade(-1);
            setLimpeza(this.maxL);
            setDiamantes(0);
        }
    }
 
    
    
    public String toString() {
        return "E:"+energia + "/" + maxE +", S:"+saciedade+"/"+maxS+", L:"+
                limpeza+"/"+maxL+", D:"+diamante+", I:"+idade;
    }
    
}

public class Jogo {
    public static void main(String[]args){
        System.out.println("Escreva:\nPara mostrar status: 'show'\n"
                + "Para fechar: 'end'\nPara brincar: 'brincar'\n"
                + "Para comer: 'comer'\nPara tomar banho: 'banho'\n"
                + "Para dormir: 'dormir'");
        
        System.out.println("Entre passando energia, saciedade e limpeza");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] in = line.split(" ");
        
        Tamagotchi tata = new Tamagotchi(Integer.parseInt(in[0]), 
                Integer.parseInt(in[1]), Integer.parseInt(in[2]));        
        
        while(true){
            String entrada = scanner.nextLine();
            if(entrada.equals("end")){
                break;
            }else if(entrada.equals("show")){
                System.out.println(tata);
            }else if(entrada.equals("comer")){
                tata.comer();
            }else if(entrada.equals("banho")){
                tata.banho();
            }else if(entrada.equals("brincar")){
                tata.brincar();
            }else if(entrada.equals("dormir")){
                tata.dormir();
            }else{
                System.out.println("fail: comanado inválido");
            }
        }
    }
}
