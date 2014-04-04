/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gi.poblacionbd;

import java.io.*;
import java.util.ArrayList;


public class LeerEscribirFicheroCsv {
    
   
public ArrayList<String[]> LeerFicheroCsv(String fichero_a_leer) 
    throws UnsupportedEncodingException, FileNotFoundException, IOException{

    ArrayList<String[]> datos=new ArrayList<>();
    try (FileInputStream fis = new FileInputStream(fichero_a_leer); InputStreamReader isr = new InputStreamReader(fis, "UTF8"); BufferedReader br = new BufferedReader(isr)) {
        
        String linea=br.readLine();
        while(linea!=null){
            datos.add(linea.split(";"));
            linea=br.readLine();
        }
    }

    return datos;

}

public void EscribirFicheroCsv(String fichero_a_escribir, ArrayList<String[]> d) 
    throws UnsupportedEncodingException, FileNotFoundException, IOException{
    try (OutputStream fout = new FileOutputStream(fichero_a_escribir); OutputStreamWriter out = new OutputStreamWriter(fout, "UTF8")) {
        
        for(int i=0; i<d.size(); i++){
            
            String[] fila=d.get(i);
            for (String fila1 : fila) {
                out.write(fila1 + ";");
            }
            
            out.write("\n");
        }
    }
}
    
}
