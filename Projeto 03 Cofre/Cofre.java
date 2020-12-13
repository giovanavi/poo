package projeto03;

import java.util.Scanner;

enum Moeda{
    M10(0.10f, 1),
    M25(0.25f, 2),
    M50(0.50f, 3),
    M100(1f, 4);
    
    float valorM;
    int volumeM;
    
    Moeda(float valorM, int volumeM){
        this.valorM = valorM;
        this.volumeM = volumeM;
    }
    
    public String toString(){
        return "Valor: "+valorM+"  Volume: "+volumeM;
    }
}

class Item{
    String descricao;
    int volumeI;
    
    Item(String descricao, int volumeI){
        this.descricao = descricao;
        this.volumeI = volumeI;
    }
}

class Porco{
    String itens = "";
    float valorP = 0;
    int volumeP = 0;
    int volumeMaxP;
    boolean estaQuebrado = false;
    
    Porco(int volumeMaxP){
        this.volumeMaxP = volumeMaxP;
    }
    
    boolean addMoeda(Moeda moeda){
        if(estaQuebrado){
            System.out.println("O cofre está quebrado");
            return false;
        }
        if(this.volumeP + moeda.volumeM > this.volumeMaxP){
            System.out.println("Essa moeda não cabe no cofre");
            return false;
        }
        this.valorP += moeda.valorM;
        this.volumeP += moeda.volumeM;
        System.out.println("Moeda adicionada ao cofre");
        return true;
    }
    
    boolean addItem(Item item){
        if(estaQuebrado){
            System.out.println("O cofre está quebrado");
            return false;
        }
        if(this.volumeP + item.volumeI > this.volumeMaxP){
            System.out.println("Esse item não cabe no cofre");
            return false;
        }
        if(this.itens.equals("")){
            this.itens += item.descricao;
        }else{
            this.itens += ", "+item.descricao;
        }
        this.volumeP += item.volumeI;
        System.out.println("Item adicionado no cofre");
        return true;
    }
    
    boolean quebrar(){
        if(!estaQuebrado){
            estaQuebrado = true;
            return true;
        }
        return false;
    }
    
    float pegarMoedas(){
        if(!estaQuebrado){
            System.out.println("Você deveria quebralo antes");
            return 0;
        }
        float aux;
        aux = this.valorP;
        this.valorP = 0;
        System.out.println("Voce pegou as moedas do cofre");
        return aux;
    }
    
    String pegarItens(){
        if(!estaQuebrado){
            System.out.println("Você deveria quebralo antes");
            return "";
        }
        String aux;
        aux = this.itens;
        this.itens = null;
        System.out.println("Voce pegou os itens do cofre");
        return aux;
    }
    
    public String toString(){
        return "Valor: "+valorP+"/ Volume: "+volumeP+"/ Itens: "+itens
                +"/ Volume Max: "+volumeMaxP+"/ Esta quebrado? "+estaQuebrado;
    }
}

public class Cofre {
    public static void main(String[]args){
        Porco porco = new Porco(20);
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Para addMoeda use 'add moeda'+'moeda'\nPara "
                + "addItem use 'add item'+'item'+'volume do item'\n"
                + "Para pegar moedas use 'pegar moedas'\n"
                + "Para pegar itens use 'pegar itens'\n"
                + "Para quebar o cofre use 'quebrar'\n");
        System.out.println(porco+"\n");
 
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(line.equals("end")){
                break;
            }else if(line.equals("show")){
                System.out.println(porco);
            }else if(ui[0].equals("add") && ui[1].equals("moeda")){
                if(ui[2].equals("M10")){
                    porco.addMoeda(Moeda.M10);
                }else if(ui[2].equals("M25")){
                    porco.addMoeda(Moeda.M25);
                }else if(ui[2].equals("M50")){
                    porco.addMoeda(Moeda.M25);
                }else if(ui[2].equals("M100")){
                    porco.addMoeda(Moeda.M100);
                }else{
                    System.out.println("fail: Essa moeda não existe");
                }
            }else if(ui[0].equals("add") && ui[1].equals("item")){
                porco.addItem(new Item(ui[2], Integer.parseInt(ui[3])));
            }else if(ui[0].equals("pegar") && ui[1].equals("moedas")){
                porco.pegarMoedas();
            }else if(ui[0].equals("pegar") && ui[1].equals("itens")){
                porco.pegarItens();
            }else if(line.equals("quebrar")){
                porco.quebrar();
            }else{
                System.out.println("fail: Comando inválido");
            }
        }        
    }
}