package proyectofmc;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;

public class SimulaDet {

    static boolean maquinaLista = false;
    static int dfa[][] = new int[100][3];

    public static boolean esAceptada(String cadena) throws Exception {
        if (maquinaLista == false || cadena == null) {
            throw new Exception("No se a leído ninguna máquina ó la cadena es inválida.");
        } else {
            // Aquí se procede a la simulación
            char cadena_char[] = cadena.toCharArray();
            
            // Para cada uno de los estados iniciales hacer la simulación
            for(int inicial = 0 ; inicial < 100 ; inicial++){
                // Si es un estado inicial
                if(dfa[inicial][0] == 1 || dfa[inicial][0] == 3){
                    // Llevar a cabo la simulación
                    int estado = inicial;
                    int pos;
                    for(pos = 0 ; pos < cadena_char.length ; pos ++){
                        // Se modifica el estado de acuerdo con el movimiento
                        int idx;
                        if (cadena_char[pos] == '0'){
                            idx = 1;
                        }
                        else if (cadena_char[pos] == '1'){
                            idx = 2;
                        }
                        else{
                            throw new Exception("La cadena no proviene de un alfabeto binario.");
                        }
                        // El estado -1 significa inválido (ninguno)
                        if(dfa[estado][idx] == -1){
                            break;
                        }
                        estado = dfa[estado][idx];
                        // Como es un DFA sólo hay una opción, en caso de que llegue a algo indeterminado le paramos
                    }
                    // Si llega al final de una cadena y está en un estado inicial, la aceptamos
                    if(pos >= cadena_char.length && (dfa[estado][0] == 2 || dfa[estado][0] == 3) ){
                        return true;
                    }
                }
            }
            // Si llegó a este punto sin reconocer a la cadena es porque no es reconocible y regresamos false
            return false;
        }
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
                    if(columnas[2].contains("*")){
                        dfa[i][1] = -1;
                    }else{
                        dfa[i][1] = Integer.parseInt(columnas[2]);
                    }
                    if(columnas[3].contains("*")){
                        dfa[i][1] = -1;
                    }
                    else{
                        dfa[i][2] = Integer.parseInt(columnas[3]);
                    }
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
