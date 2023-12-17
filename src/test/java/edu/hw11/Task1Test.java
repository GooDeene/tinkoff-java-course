package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    public void byteBuddyToStringWorksCorrectly()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(
                getClass().getClassLoader()
            )
            .getLoaded();

        var toStringResult = dynamicType.getDeclaredConstructor().newInstance().toString();
        Assertions.assertEquals("Hello, ByteBuddy!", toStringResult);
    }
}
