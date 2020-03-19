package matriz;

import java.util.ArrayList;
import java.util.List;

public class MatrizHash {

    private int bkfr;
    private int numeroBuckets;
    private String[][] datos;
    private List<String> overflow;

    public MatrizHash(int bkfr) {
        this.bkfr = bkfr;
        this.numeroBuckets = 10;
        datos = new String[numeroBuckets][bkfr];
        overflow = new ArrayList();

    }

    public MatrizHash() {
        this.bkfr = 2;
        this.numeroBuckets = 10;
        datos = new String[numeroBuckets][bkfr];
        overflow = new ArrayList();

    }

    public void almacenarCadena(String cadena) {

        int bucket = obtenerBucket(cadena);
        int j;
        for (j = 0; j < bkfr; j++) {
            if (this.datos[bucket][j] == null) {
                System.out.println("Dato almacenado en " + bucket + ", " + j + " -> " + cadena);
                this.datos[bucket][j] = cadena;
                break;
            }
            System.out.println("Dato sinónimo");
        }

        if (j == bkfr) {
            System.out.println("Dato colisionado");
            System.out.println("Guardando en área de overflow" + " -> " + cadena);
            this.overflow.add(cadena);
        }
    }

    public boolean consultarCadena(String cadena) {
        int bucket = obtenerBucket(cadena);
        int j;
        for (j = 0; j < bkfr; j++) {
            if (this.datos[bucket][j] == null) {
                System.out.println("Dato no encontrado");
                return false;
            } else if (cadena.equals(this.datos[bucket][j])) {
                System.out.println("Dato encontrado en: " + bucket + ", " + j);
                return true;
            }
        }

        if (j == bkfr) {
            for (String dato : overflow) {
                if (dato.equals(cadena)) {
                    System.out.println("Encontrado en área de overflow "+ " -> " + cadena);
                    return true;
                }
            }
        }
        System.out.println("Dato no encontrado");
        return false;

    }

    private int obtenerBucket(String cadena) {
        int suma = 0;
        for (int i = 0; i < cadena.length(); i++) {
            suma += cadena.charAt(i);
        }
        String digito = String.valueOf(suma).substring(0, 1);
        int digitoEntero = Integer.parseInt(digito);
        return digitoEntero;
    }
}
