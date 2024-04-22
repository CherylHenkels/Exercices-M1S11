package br.fullstack.education.exerciciosm1s11.infra.exception;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String message) {
        super(message);
    }
}