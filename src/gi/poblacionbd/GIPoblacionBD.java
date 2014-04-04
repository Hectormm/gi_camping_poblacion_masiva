/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gi.poblacionbd;

import static com.sun.org.apache.xalan.internal.lib.ExsltDynamic.map;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author saraer
 */
public class GIPoblacionBD {

    private static ArrayList<String> nombres;
    /**
     * @param args the command line arguments
     * @throws java.io.UnsupportedEncodingException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) 
        throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

        nombres = new ArrayList<>();
        
         //construimos una instancia de la clase
        LeerEscribirFicheroCsv le = new LeerEscribirFicheroCsv(); 
        //leemos un fichero
        ArrayList<String[]>f = le.LeerFicheroCsv("nombresHombresEsp.csv");
        
        double totalInserciones = generar(f, 586450);
        
        f = le.LeerFicheroCsv("nombresMujeresEsp.csv");
        totalInserciones += generar(f, 605168);
        
        insertar(totalInserciones);
        
    }
    
    
    public static double generar(ArrayList<String[]> f, double numeroMagico) throws FileNotFoundException, ParseException, IOException{
            
        int totalNombres = f.size();
        double inserciones, total = 0;
        float porcentaje;
        String nombre;
        
        for(int i = 0; i < totalNombres; i++){
            
            NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE); // Looks like a US format
            porcentaje = nf.parse(f.get(i)[1]).floatValue();
            
            inserciones = Math.ceil(porcentaje*numeroMagico);
            
            nombre = f.get(i)[0];
            
            System.out.print(nombre + " -> ");
            System.out.printf("%.0f\n", inserciones);
            
            for(int j=0; j<inserciones; j++)
                nombres.add(nombre);
            
            total += inserciones;
        }
        
        System.out.print("Total de inerciones: ");
        System.out.printf("%.0f\n", total); 
        
        return total;
    }
    
    public static void insertar(double totalInserciones){
        int randomNum;
        double cant;
        double totalInserciones2 = totalInserciones;
        
        for(int i=0; i< totalInserciones; i++){
            randomNum = (int)(Math.random()*totalInserciones2); 
            
            System.out.println(nombres.get(randomNum) + " " + totalInserciones2);
            nombres.remove(randomNum);
            totalInserciones2--;
        }
    }
    
}
