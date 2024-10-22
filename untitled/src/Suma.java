import javax.swing.JOptionPane;

// Implementamos Runnable para manejar el hilo de la suma
class Suma implements Runnable {
    private int numero1;
    private int numero2;

    public Suma(int numero1, int numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public int getNumero1() {
        return numero1;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public int getNumero2() {
        return numero2;
    }

    public void setNumero2(int numero2) {
        this.numero2 = numero2;
    }

    @Override
    public void run() {
        // Realizar la suma en el hilo
        int resultado = numero1 + numero2;
        JOptionPane.showMessageDialog(null, "La suma es: " + resultado);
    }
}
