package utils;

public class Sessao {
    private static String nome;
    private static String email;
    private static boolean isAdmin;

    public static void iniciarSessao(String nomeUser, String emailUser, boolean admin) {
        nome = nomeUser;
        email = emailUser;
        isAdmin = admin;
    }

    public static String getNome() {
        return nome;
    }

    public static String getEmail() {
        return email;
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void encerrarSessao() {
        nome = null;
        email = null;
        isAdmin = false;
    }
}
