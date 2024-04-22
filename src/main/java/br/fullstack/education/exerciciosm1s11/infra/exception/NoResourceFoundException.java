package br.fullstack.education.exerciciosm1s11.infra.exception;


public class NoResourceFoundException extends RuntimeException {
    public NoResourceFoundException(String message) {
        super(message);
    }
}