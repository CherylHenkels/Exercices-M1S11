package br.fullstack.education.exerciciosm1s11.infra.exception;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}