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
    private static ArrayList<String> apellidos;
    private static ArrayList<String> apellidos2;
    private static ArrayList<String> dnis;
    private static ArrayList<String> telefonos;
    private static ArrayList<String> calles;
    /**
     * @param args the command line arguments
     * @throws java.io.UnsupportedEncodingException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) 
        throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

        nombres = new ArrayList<>();
        apellidos = new ArrayList<>();
        apellidos2 = new ArrayList<>();
        dnis = new ArrayList<>();
        calles = new ArrayList<>();
        telefonos = new ArrayList<>();
        
        boolean nom=true;
        
         //construimos una instancia de la clase
        LeerEscribirFicheroCsv le = new LeerEscribirFicheroCsv(); 
        //leemos un fichero
        ArrayList<String[]>f = le.LeerFicheroCsv("nombresHombresEsp.csv");
        
        ArrayList<String[]>a = le.LeerFicheroCsv("apellidosEspanyoles.csv");

        ArrayList<String[]>d = le.LeerFicheroCsv("dni.csv");
        
         int numerodnis = d.size();
        String numero;
        String letra;
        
        for(int i = 0; i < numerodnis; i++){
         
            numero = d.get(i)[0];
            letra = d.get(i)[1];
            String dni = numero+letra;
            dnis.add(dni);
        }
        
        ArrayList<String[]>c = le.LeerFicheroCsv("calles.csv");
        c = le.LeerFicheroCsv("callesExt.csv");
        
          int numerocalles = c.size();
        String calle;
        
        for(int i = 0; i < numerocalles; i++){
         
            calle = c.get(i)[0];
            calles.add(calle);
        }
        
        ArrayList<String[]>t = le.LeerFicheroCsv("telefonos.csv");
        
         int numerotelefonos = t.size();
         String telefono;
        
        for(int i = 0; i < numerotelefonos; i++){
         
            telefono = t.get(i)[0];
            telefonos.add(telefono);
        }
        
        double totalInserciones = generar(f, 586450/2, nom);
        
        f = le.LeerFicheroCsv("nombresMujeresEsp.csv");
       totalInserciones += generar(f, 605168/2, nom);
        
        f = le.LeerFicheroCsv("nombresMujeresExt.csv");
        totalInserciones += generar(f, 35095, nom);
        
        f = le.LeerFicheroCsv("nombresHombresExt.csv");
        totalInserciones += generar(f, 32833, nom);
        
        nom=false;
        
        double totalApellidos = generar(a, 655000, nom);
        
        a = le.LeerFicheroCsv("apellidosExtranjeros.csv");
       totalApellidos += generar(a, 594000, nom);
        
        insertar(totalInserciones, totalApellidos);
        
    }
    
    
    public static double generar(ArrayList<String[]> f, double numeroMagico, boolean nom) throws FileNotFoundException, ParseException, IOException{
            
        int totalNombres = f.size();
        double inserciones, total = 0;
        float porcentaje;
        String nombre;
        String apellido;
        
        for(int i = 0; i < totalNombres; i++){
            
            NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE); // Looks like a US format
            porcentaje = nf.parse(f.get(i)[1]).floatValue();
            
            inserciones = Math.ceil(porcentaje*numeroMagico);
            
            nombre = f.get(i)[0];
            apellido = f.get(i)[0];
            
            if(nom==true){
               for(int j=0; j<inserciones; j++)
                nombres.add(nombre);
            
            total += inserciones; 
            }
            else{
                for(int j=0; j<inserciones; j++)
                apellidos.add(apellido);
            
            total += inserciones;
            }
            
            
        }
        
        System.out.print("Total de inerciones: ");
        System.out.printf("%.0f\n", total); 
        
        return total;
    }
    
    public static void insertar(double totalInserciones, double totalApellidos) throws FileNotFoundException, UnsupportedEncodingException, IOException{
        int randomNum;
        double cant;
        double totalInserciones2 = totalInserciones;
        int k=0;

        
        try (OutputStream fout = new FileOutputStream("salida.csv"); 
                OutputStreamWriter out = new OutputStreamWriter(fout, "UTF8")) {
            
            for(int i=0; i< totalInserciones; i++){
                randomNum = (int)(Math.random()*totalInserciones2);
               
               String nomFinal = nombres.get(randomNum);
               char l0=nomFinal.charAt(0);
               
               String apellidoFinal = apellidos.get(randomNum);
               char l1=apellidoFinal.charAt(0);
                
                out.write(dnis.get(k)+";"+nomFinal+";"+apellidoFinal+";"+calles.get(k)+";"+telefonos.get(k)+";"+l0+l1+"@gmail.com"+"\n");
                
                nombres.remove(randomNum);
                apellidos.remove(randomNum);
                k++;
                totalInserciones2--;
 
            }
        }
    }
    
}
