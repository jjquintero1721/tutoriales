public class HiloPrueba implements Runnable {
    @Override
    public void run() {
        System.out.println("Hola mundo");
    }

    public static void main(String[] args) {
        HiloPrueba hp = new HiloPrueba();
        hp.run();
    }
}


