package br.puc.pi6.client;

public class Main {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new Main().getGreeting());
        System.out.println("Rodando com Java: " + System.getProperty("java.version"));
    }
}
