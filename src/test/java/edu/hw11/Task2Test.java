package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    public void fakeSumWorksCorrectly() {
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(Ersatz.class))
            .make()
            .load(
                ArithmeticUtils.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent()
            );

        var sumResult = ArithmeticUtils.sum(6, 7);
        Assertions.assertEquals(42, sumResult);
        // ОТВЕТ НА ГЛАВНЫЙ ВОПРОС ЖИЗНИ, ВСЕЛЕННОЙ И ВСЕГО ТАКОГО ?!
    }

    private final static class Ersatz {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
}
