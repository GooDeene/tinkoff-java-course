package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
@SuppressWarnings("InnerTypeLast")
public class ReflectionBenchmarking {
    private static final int WARMUP_DURATION = 5;
    private static final int MEASUREMENT_TIME = 60;
    private static final String TEST_METHOD_NAME = "name";

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) throws RunnerException {
        var options = new OptionsBuilder()
            .include(ReflectionBenchmarking.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(WARMUP_DURATION))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(MEASUREMENT_TIME))
            .build();

        new Runner(options).run();
    }

    private record Student(String name, String surname) {
    }

    private Student student;
    private Method method;
    private Function<Student, String> lMetaFactory;
    private MethodHandle methodHandle;

    @Setup
    public void setup() throws Throwable {
        student = new Student("Бенджамин", "Лайнус"); // LOST?! :O

        method = Student.class.getMethod(TEST_METHOD_NAME);

        MethodHandles.Lookup publicLookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(String.class);
        methodHandle = publicLookup.findVirtual(Student.class, TEST_METHOD_NAME, methodType);

        lMetaFactory = (
            Function<Student, String>) LambdaMetafactory.metafactory(
            publicLookup,
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            methodHandle,
            MethodType.methodType(String.class, Student.class)
        )
            .getTarget()
            .invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        var name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflectionAccess(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        var name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandleAccess(Blackhole bh) throws Throwable {
        var name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetaFactoryAccess(Blackhole bh) {
        var name = lMetaFactory.apply(student);
        bh.consume(name);
    }
}
