/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

/**
 *
 * @author COMPUTADOR
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64; // Para codificar/decodificar o salt e o hash para armazenamento

public class PasswordHasher {

    public static byte[] gerarSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // Um salt de 16 bytes é geralmente suficiente
        random.nextBytes(salt);
        return salt;
    }

    // Fazer o Hash da Senha com Salt
    public static String hashSenha(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt); // Adiciona o salt antes da senha
            byte[] hashedPassword = md.digest(password.getBytes());

            // Converter o hash para uma representação em String (ex: Base64) para armazenamento
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao fazer o hash da senha", e);
        }
    }

    // Verificação da Senha
    public static boolean verificarSenha(String senha, String senhadb, byte[] saltdb) {
        String enteredHashedPassword = hashSenha(senha, saltdb);
        return enteredHashedPassword.equals(senhadb);
    }
}
