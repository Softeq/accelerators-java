package com.softeq.wiremock.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CityInputParserTest {

    private CityInputParser parser = new CityInputParser();

    public static final String VALID_INPUT = "Str1, Str2, Str3, Str4";
    public static final String INVALID_INPUT = "Str1, Str2; Str3.Str4";
    public static final List<String> EXPECTED = Arrays.asList("Str1", "Str2", "Str3", "Str4");

    @Test
    public void testParseWithValidInputShouldSucceed() {
        //when
        List<String> actualValid = parser.parse(VALID_INPUT);
        List<String> actualInvalid = parser.parse(INVALID_INPUT);

        //then
        assertThat(actualValid).isEqualTo(EXPECTED);
        assertThat(actualInvalid).isNotEqualTo(EXPECTED);
    }
}
