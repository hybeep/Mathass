/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathass;

import java.util.Scanner;

/**
 *
 * @author tutus
 */
public class binario {
    
    public static void binario(String[] args){
        Scanner scan = new Scanner(System.in);
        String operacion;
        String regex = "[0-9]+[&|^][0-9]+";
        String regex2 = "[~][0-9]+";
        int index = 0;
        String operador = "";
        String resultado = "";
        do{
            System.out.println("escribe los dos numeros separados por el simbolo de BITWISE(&,|,~,^)");
            operacion = scan.nextLine();

            if(operacion.matches(regex)){
                for (Character i :operacion.toCharArray()){
                    try{    
                        Integer.parseInt(i.toString());
                    }catch(NumberFormatException ex){
                        operador = i.toString();
                        index = operacion.indexOf(operador);
                    }
                }
                resultado = resultado(operacion, operador, index);
            }else if(operacion.matches(regex2)){
                int decimal = Integer.parseInt(operacion.substring(1));
                resultado = resultado(operacion,decimal);
            }else{
                System.out.println("Syntax error e.g. 234|35");
            }
        }while(!(operacion.matches(regex) || operacion.matches(regex2)));
        System.out.println("el resultado en binario es: " + resultado + ". En decimal es: " + decimal(resultado));
    }
    
    public static String resultado(String operacion, String operador, int index){
        String primerNumero = operacion.substring(0, index), 
                segundoNumero = operacion.substring(index+1);
        
        String primerBin = binario(Integer.parseInt(primerNumero)),
               segundoBin = binario(Integer.parseInt(segundoNumero));
        
        String mayorLongitud = "", mayorFinal = "", menorLongitud = "", mayorInicio="";
        int menorStrCant = 0, mayorStrCant = 0;
        
        boolean igualLongitud = primerBin.length() == segundoBin.length() ? true : false; 
        if (!igualLongitud) {
            menorLongitud = primerBin.length() < segundoBin.length() ? primerBin : segundoBin;
            mayorLongitud = primerBin.length() > segundoBin.length() ? primerBin : segundoBin;
            menorStrCant = primerBin.length() < segundoBin.length() ? primerBin.length() : segundoBin.length();
            mayorStrCant = primerBin.length() > segundoBin.length() ? primerBin.length() : segundoBin.length();
        }else{
            menorLongitud = primerBin;
            mayorLongitud = segundoBin;
            menorStrCant = primerBin.length();
            mayorStrCant = primerBin.length();
        }
        
        mayorInicio += mayorLongitud.substring(0, mayorLongitud.length()-menorStrCant);
        mayorFinal += mayorLongitud.substring(mayorLongitud.length()-menorStrCant, mayorLongitud.length());
        
        String resultado = "";
        
        switch(operador){
            case "&":
                for (int i = 0; i < menorStrCant; i++) {
                    if (menorLongitud.charAt(i) == mayorFinal.charAt(i) && menorLongitud.charAt(i) == '1') {
                        resultado += "1"; 
                    }else{
                        resultado += "0";
                    }
                }
                break;
                
            case "|":
                resultado = mayorInicio;
                for (int i = 0; i < menorStrCant; i++) {
                    if (menorLongitud.charAt(i) == '1' || mayorFinal.charAt(i) == '1') {
                        resultado += "1"; 
                    }else{
                        resultado += "0";
                    }
                }
                break;
                
            case "^":
                resultado = mayorInicio;
                for (int i = 0; i < menorStrCant; i++) {
                    if (menorLongitud.charAt(i) != mayorFinal.charAt(i)) {
                        resultado += "1";
                    }else{
                        resultado += "0";
                    }
                }
                break;
                
            default:
                resultado = "operador invalido";
                break;
        }
        return resultado;
    }
    
    public static String resultado(String operacion, int decimal){
        String resultado = "";
        String binario = binario(decimal);
        for (int i = 0; i < binario.length(); i++) {
            if (binario.charAt(i) == '1') {
                resultado += "0";
            }else{
                resultado += "1";
            }
        }
        return resultado;
    }
    
    public static String binario(int decimal){
        final int base = 2;
        int potencia = 0;
        String binario = "";
        
        while(Math.pow(base, potencia) < decimal || (potencia+1)%4 != 0  ){
            potencia++;
        }
        
        while(potencia >= 0){
            if(Math.pow(base, potencia)<=decimal){
                decimal -= Math.pow(base, potencia);
                binario += "1";
            }else{
                binario += "0";
            }
            potencia--;
        }
        return binario;
    }
    
    public static int decimal(String binario){
        int decimal = 0;
        final int base = 2;
        int potencia = binario.length()-1;
        for (int i = 0; i < binario.length(); i++) {
            if (binario.charAt(i)=='1') {
                decimal += Math.pow(base, potencia);
            }
            potencia--;
        }
        return decimal;
    }
}