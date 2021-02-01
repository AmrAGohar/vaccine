package com.vaccnow.vaccine.exception;

public class NotExistVaccineException extends RuntimeException{
    public NotExistVaccineException(String message) {
        super(message);
    }
}
