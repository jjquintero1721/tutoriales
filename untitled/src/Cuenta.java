import javax.swing.JOptionPane;

// Clase Cuenta con getters y setters
class Cuenta {
    private double saldo;

    // Constructor
    public Cuenta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Getter para obtener el saldo
    public double getSaldo() {
        return saldo;
    }

    // Setter para modificar el saldo
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Método para depositar dinero
    public synchronized void depositar(double monto) {
        saldo += monto;
        JOptionPane.showMessageDialog(null, "Has depositado: " + monto + ". Saldo actual: " + saldo);
    }

    // Método para retirar dinero
    public synchronized void retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            JOptionPane.showMessageDialog(null, "Has retirado: " + monto + ". Saldo actual: " + saldo);
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para retirar: " + monto);
        }
    }

    // Método para consultar saldo
    public void consultarSaldo() {
        JOptionPane.showMessageDialog(null, "Saldo actual: " + saldo);
    }
}

// Clase para manejar las transacciones
class Transaccion implements Runnable {
    private Cuenta cuenta;
    private String tipoTransaccion;
    private double monto;

    // Constructor para especificar el tipo de transacción y la cuenta
    public Transaccion(Cuenta cuenta, String tipoTransaccion, double monto) {
        this.cuenta = cuenta;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
    }

    @Override
    public void run() {
        switch (tipoTransaccion) {
            case "consultar":
                cuenta.consultarSaldo();
                break;
            case "depositar":
                cuenta.depositar(monto);
                break;
            case "retirar":
                cuenta.retirar(monto);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Transacción inválida.");
                break;
        }
    }
}

class CajeroAutomatico {
    public static void main(String[] args) {
        // Crear una cuenta con un saldo inicial
        Cuenta cuenta = new Cuenta(500.0);  // saldo inicial de 500

        // Menú de opciones para el usuario
        String[] opciones = {"Consultar saldo", "Depositar", "Retirar", "Salir"};
        boolean salir = false;

        while (!salir) {
            // Mostrar el menú de opciones
            String seleccion = (String) JOptionPane.showInputDialog(null, "Selecciona una opción:",
                    "Cajero Automático", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (seleccion == null || seleccion.equals("Salir")) {
                salir = true;
                break;
            }

            Thread hiloTransaccion = null;

            // Manejo de las opciones seleccionadas
            switch (seleccion) {
                case "Consultar saldo":
                    hiloTransaccion = new Thread(new Transaccion(cuenta, "consultar", 0));
                    break;

                case "Depositar":
                    String depositoStr = JOptionPane.showInputDialog("Ingresa la cantidad a depositar:");
                    if (depositoStr != null && !depositoStr.isEmpty()) {
                        double deposito = Double.parseDouble(depositoStr);
                        hiloTransaccion = new Thread(new Transaccion(cuenta, "depositar", deposito));
                    }
                    break;

                case "Retirar":
                    String retiroStr = JOptionPane.showInputDialog("Ingresa la cantidad a retirar:");
                    if (retiroStr != null && !retiroStr.isEmpty()) {
                        double retiro = Double.parseDouble(retiroStr);
                        hiloTransaccion = new Thread(new Transaccion(cuenta, "retirar", retiro));
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }

            // Si se creó un hilo, se inicia y se espera a que termine
            if (hiloTransaccion != null) {
                hiloTransaccion.start();
                try {
                    // Esperar a que el hilo termine antes de mostrar el menú nuevamente
                    hiloTransaccion.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
