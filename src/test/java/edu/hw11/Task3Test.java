package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    public void fibonacciProcessorWorksCorrectlyWithCorrectData() throws Exception {
        for (var i = 1; i <= 10; i++) {
            var processorResult = calculateFibonacciNumber(i);
            Assertions.assertEquals(FIRST_TEN_FIBONACCI_NUMBERS[i - 1], processorResult);
        }
    }

    @Test
    public void fibonacciProcessorWorksCorrectlyWithIncorrectData() throws Exception {
        Assertions.assertEquals(-1L, calculateFibonacciNumber(-1));
        Assertions.assertEquals(0L, calculateFibonacciNumber(0));
    }

    private static final long[] FIRST_TEN_FIBONACCI_NUMBERS = {
        1, 1, 2, 3, 5, 8, 13, 21, 34, 55
    };
    private static final String FIBONACCI_CLASS_NAME = "FibonacciProcessor";
    private static final String CALCULATION_METHOD_NAME = "process";
    private static final String METHOD_SIGNATURE = "(I)J";

    private Object calculateFibonacciNumber(int n)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var calculator = new ByteBuddy()
            .subclass(Object.class)
            .name(FIBONACCI_CLASS_NAME)
            .defineMethod(
                CALCULATION_METHOD_NAME,
                long.class,
                Modifier.PUBLIC
                    | Modifier.STATIC
            )
            .withParameters(int.class)
            .intercept(new Implementation.Simple(new CustomFibonacciAppender()))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        return calculator
            .getMethod(CALCULATION_METHOD_NAME, int.class)
            .invoke(null, n);
    }

    private static class CustomFibonacciAppender implements ByteCodeAppender {
        @Override
        public @NotNull Size apply(
            MethodVisitor visitor,
            Implementation.@NotNull Context context,
            @NotNull MethodDescription methodDescription
        ) {
            visitor.visitCode();
            var label = new Label();

            visitor.visitVarInsn(Opcodes.ILOAD, 0);
            visitor.visitInsn(Opcodes.ICONST_1);
            visitor.visitJumpInsn(Opcodes.IF_ICMPGT, label);

            visitor.visitVarInsn(Opcodes.ILOAD, 0);
            visitor.visitInsn(Opcodes.I2L);
            visitor.visitInsn(Opcodes.LRETURN);

            visitor.visitLabel(label);
            visitor.visitFrame(
                Opcodes.F_SAME,
                0,
                null,
                0,
                null
            );

            visitor.visitVarInsn(Opcodes.ILOAD, 0);
            visitor.visitInsn(Opcodes.ICONST_1);
            visitor.visitInsn(Opcodes.ISUB);
            visitor.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                FIBONACCI_CLASS_NAME,
                CALCULATION_METHOD_NAME,
                METHOD_SIGNATURE
            );

            visitor.visitVarInsn(Opcodes.ILOAD, 0);
            visitor.visitInsn(Opcodes.ICONST_2);
            visitor.visitInsn(Opcodes.ISUB);
            visitor.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                FIBONACCI_CLASS_NAME,
                CALCULATION_METHOD_NAME,
                METHOD_SIGNATURE
            );

            visitor.visitInsn(Opcodes.LADD);
            visitor.visitInsn(Opcodes.LRETURN);
            visitor.visitEnd();

            return new ByteCodeAppender.Size(4, 1);
        }
    }
}
