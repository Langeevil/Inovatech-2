package br.com.faculdadeinovatech.inovatech2.repository;

public interface EmailRepository {
    void sendPasswordResetEmail(String to, String token);

}
