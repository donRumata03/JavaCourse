package expression.exceptions;

import expression.common.Selector;

import static expression.parser.Operations.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class ExceptionsTest {
    public static final Selector<?> SELECTOR = Selector.create(ExceptionsTest.class, ExceptionsTester::new, "easy", "hard")
            .variant("Base", X, Y, Z, ADD, SUBTRACT, MULTIPLY, DIVIDE, NEGATE);

    public static void main(final String... args) {
        SELECTOR.main(args);
    }
}
