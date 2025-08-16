package br.puc.game2d;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        System.out.println("Rodando com Java: " + System.getProperty("java.version"));
    }
}
