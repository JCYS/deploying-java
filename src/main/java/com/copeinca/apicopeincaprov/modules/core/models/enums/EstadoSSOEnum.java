package com.copeinca.apicopeincaprov.modules.core.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum EstadoSSOEnum {
    PRELIMINAR("ST_SSO_0001", "PRELIMINAR", "Estado preliminar para SSO"),
    APTO("ST_SSO_0002", "APTO", "Apto para SSO"),
    NO_APTO("ST_SSO_0003", "NO APTO", "No apto para SSO");

    private final String code;
    private final String name;
    private final String desc;

    // 🔥 Lookup cache O(1)
    private static final Map<String, EstadoSSOEnum> BY_NAME =
            Stream.of(values())
                    .collect(Collectors.toUnmodifiableMap(
                            EstadoSSOEnum::getName,
                            Function.identity()
                    ));

    public static String getCodeByName(String name) {
        return BY_NAME.get(name).getCode();
    }

    public static EstadoSSOEnum fromName(String name) {
        return BY_NAME.get(name);
    }

    public static Optional<String> findCodeByName(String name) {
        return Optional.ofNullable(name)
                .map(String::trim)
                .map(String::toUpperCase)
                .map(BY_NAME::get)
                .map(EstadoSSOEnum::getCode);
    }
}
