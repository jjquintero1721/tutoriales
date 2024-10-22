import javax.swing.*;

public class SumaHilos {
    public static void main(String[] args) {
        // Pedir el primer número
        String num1Str = JOptionPane.showInputDialog("Ingresa el primer número:");

        // Pedir el segundo número
        String num2Str = JOptionPane.showInputDialog("Ingresa el segundo número:");

        // Convertir las entradas de String a int
        int numero1 = Integer.parseInt(num1Str);
        int numero2 = Integer.parseInt(num2Str);

        // Crear un hilo para realizar la suma
        Suma sumaTarea = new Suma(numero1, numero2);
        Thread hiloSuma = new Thread(sumaTarea);

        // Iniciar el hilo
        hiloSuma.start();
    }
}


