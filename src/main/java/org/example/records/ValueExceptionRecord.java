package org.example.records;

/**
 * Record for holding the user answers.
 * @param value user answer.
 * @param e exception caught on validation.
 * @param <T> content type.
 */
public record ValueExceptionRecord<T>(T value, Exception e) {
}