package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task1Test {
    @ParameterizedTest
    @CsvSource({
        "aaa, zzz",
        "abc, zyx",
        "hello, svool",
        "A, Z",
        "HeLLo, SvOOl",
        "Я РУССКИЙ, Я РУССКИЙ",
        "Я ИДУ to THE end, Я ИДУ gl GSV vmw"
    })
    public void correctWorksWithSimpleStringsWithoutSymbols(String str, String answer) {
        Assertions.assertEquals(answer, Task1.atbash(str));
    }

    @ParameterizedTest
    @CsvSource({
        "Hello world!, Svool dliow!",
        "1234aAa!?&!, 1234zZz!?&!"
    })
    public void correctWorksWithStringsWithSymbols(String str, String answer) {
        Assertions.assertEquals(answer, Task1.atbash(str));
    }

    @Test
    public void correctWorksWithEmptyString() {
        Assertions.assertEquals("", Task1.atbash(""));
    }

    @Test
    public void correctWorksWithNull() {
        Assertions.assertNull(Task1.atbash(null));
    }

    @Test
    public void correctWorksWithHardStringFromExample() {
        String str = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        String answer = "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";
        Assertions.assertEquals(answer, Task1.atbash(str));
    }
}
