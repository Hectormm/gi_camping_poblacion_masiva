/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gi.poblacionbd;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

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
            
            for(int j=0; j<inserciones; j++)
                nombres.add(nombre);
            
            total += inserciones;
        }
        
        System.out.print("Total de inerciones: ");
        System.out.printf("%.0f\n", total); 
        
        return total;
    }
    
    public static void insertar(double totalInserciones) throws FileNotFoundException, UnsupportedEncodingException, IOException{
        int randomNum;
        double cant;
        double totalInserciones2 = totalInserciones;
        
        try (OutputStream fout = new FileOutputStream("salida.csv"); 
                OutputStreamWriter out = new OutputStreamWriter(fout, "UTF8")) {
            
            for(int i=0; i< totalInserciones; i++){
                randomNum = (int)(Math.random()*totalInserciones2); 

                out.write(nombres.get(randomNum)+";Martinez Rodriguez\n");
                
                nombres.remove(randomNum);
                totalInserciones2--;
            }
        }
    }
    
}
