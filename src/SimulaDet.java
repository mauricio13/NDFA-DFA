/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package proyectofmc;

import java.io.*;

public class SimulaDet {

    static boolean maquinaLista = false;
    static int dfa[][] = new int[100][3];

    public static boolean esAceptada(String cadena) throws Exception {
        if (maquinaLista == false) {
            throw new Exception("No se a leído ninguna máquina");
        } else {
        }
        return true;
    }

    public static void leeMaquina(String nombreArchivo) {
        String linea;
        String columnas[];
        int i = 0, tipo_int = 0;
        String tipo;
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            linea = br.readLine();
            while (linea != null) {
                try {
                    columnas = linea.split("\t");
//                        for(int aa = 0 ; aa < 4 ;aa++)
//                            System.out.print(columnas[aa] + " ");
//                        System.out.println("");
                    i = Integer.parseInt(columnas[0]);
                    tipo = columnas[1];
                    tipo_int = 0;
                    tipo_int += (tipo.contains(">")) ? 1 : 0;
                    tipo_int += (tipo.contains("@")) ? 2 : 0;
                    dfa[i][0] = tipo_int;
                    dfa[i][1] = Integer.parseInt(columnas[2]);
                    dfa[i][2] = Integer.parseInt(columnas[3]);
//                       lectura.close(); 
                    linea = br.readLine();
                } catch (EOFException Fin) {
                    System.out.println("Error fue: " + Fin.toString() + " " + Fin.getMessage());
                    maquinaLista = false;
                    return;
                }
            }
        } catch (IOException error) {
            System.out.println("Error" + error.getMessage());
            maquinaLista = false;
            return;
        }
        maquinaLista = true;
    }

    public static void prueba() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(dfa[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
