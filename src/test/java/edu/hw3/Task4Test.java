package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task4Test {
    @ParameterizedTest
    @CsvSource({
        "1, I",
        "2, II",
        "4, IV",
        "5, V",
        "9, IX",
        "399, CCCXCIX",
        "500, D",
        "900, CM",
        "1001, MI",
        "3999, MMMCMXCIX"
    })
    public void correctWorksWithCorrectArabicIntegers(Integer arabic, String roman) {
        Assertions.assertEquals(roman, Task4.convertToRoman(arabic));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 0, 4000, 1234567})
    public void throwsExceptionWithIncorrectArabicIntegers(Integer arabic) {
        assertThatThrownBy(() -> Task4.convertToRoman(arabic))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
