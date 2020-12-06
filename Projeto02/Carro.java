package Carro;

import java.util.Scanner;       

public class Carro {
    //Atributos
    int km;
    int gas;
    int maxGas;
    int pass;
    int maxPass;

    //Construtor
    Carro(int km, int gas, int maxGas, int pass, int maxPass){
        this.km = km;
        this.gas = gas;
        this.maxGas = maxGas;
        this.pass = pass;
        this.maxPass = maxPass;
        
    }
    
    //Métodos
    void embarque(){
        if(pass<2){
            pass += 1;
            System.out.println("Passageiro embarcou");
        }else{
            System.out.println("O carro está lotado");   
        }
    }
    
    void desembarque(){
        if(pass>0){
            pass -= 1;
            System.out.println("Passageiro desembarcou");
        }else{
            System.out.println("Não há ninguém no carro");
        }
    }
    
    void abastecer(int inp){  
        if(gas<maxGas){
            if(maxGas<inp){
                System.out.println("Abastecido somente "+(maxGas-gas)+" litros");
                gas = maxGas;
                return;
            }else if((gas+inp)>maxGas){
                int aux = gas;
                gas = maxGas - aux;
                System.out.println("Carro abastecido apenas "+gas+" litros");
                gas += aux;
                return;
            }
            gas+=inp;
            System.out.println("Carro abastecido");
        }else{
            System.out.println("Tanque cheio");
        }
    }
    
    void dirigir(int dist){
        if(pass==0){
            System.out.println("Não há passageiros no carro");
            return;
        }
        if(gas>0){
            if(gas<dist){
                System.out.println("O carro percorreu apenas "+gas+" km");
                km += gas;
                gas = 0;
                return;
            }
            gas-=dist;
            km += dist;
            System.out.println("Caminho percorrido");
        }else{
            System.out.println("Carro sem gasolina");
        }
    }
   
    public String toString(){
        return "pass: "+pass+" gas: "+gas+"/"+maxGas+ " km: "+km;
    }
    
    public static void main(String[]args){

        
        Carro fusca = new Carro(0,0,100,0,2);
        System.out.println(fusca);
        
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            String line = scanner.nextLine();
            String[] inp = line.split(" ");
            if(line.equals("end")){
                break;
            }else if(line.equals("show")){
                System.out.println(fusca);
            }else if(line.equals("embarque")){
                fusca.embarque();
            }else if(line.equals("desembarque")){
                fusca.desembarque();
            }else if(inp[0].equals("abastecer")){
                fusca.abastecer(Integer.parseInt(inp[1]));
            }else if(inp[0].equals("dirigir")){
                fusca.dirigir(Integer.parseInt(inp[1]));
            }else{
                System.out.println("Comando inválido");
            }
        }
        
    }
}
