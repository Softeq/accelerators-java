package com.example.pdfmerger.utils;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<A,B,C,R> {

    R apply(A a, B b, C c) throws IOException, DocumentException;

    default <V> TriFunction<A, B, C, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (A a, B b, C c) -> after.apply(apply(a, b, c));
    }
}
