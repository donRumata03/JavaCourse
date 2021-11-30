package expression.myTests;

import expression.Add;
import expression.Const;
import expression.Divide;
import expression.Multiply;
import expression.Variable;

public class MyTests {

    private static Const c(final Integer c) {
        return new Const(c);
    }


    public static void main(String[] args) {
        final Variable vx = new Variable("x");
        final Const c1 = c(1);
        final Const c2 = c(2);


        var expression = new Multiply(new Divide(new Add(c2, c1), c1), vx);
        System.out.println(expression.evaluate(10));
        System.out.println(expression.toMiniString());
    }
}
