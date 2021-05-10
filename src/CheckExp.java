import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CheckExp {
    private static void checkString() {
        Expression e1 = new Not(new Var("a"));
        System.out.println(e1.toString());
        Expression e2 = new Or(new Var("x"), new Var("y"));
        System.out.println(e2.toString());
        Expression e3 = new Not(new Or(new Var("p"), new Val(true)));
        System.out.println(e3.toString());
        Expression e4 = new Not(new And(new Var("p"), new Val(true)));
        System.out.println(e4.toString());
        Expression e5 = new Xor(new And(new Var("x"), new Var("y")), new Val(true));
        System.out.println(e5.toString());
        Expression e6 = new Not(
                new Xor(
                        new And(
                                new Val(true),
                                new Or(
                                        new Var("x"),
                                        new Var("y")
                                )
                        ),
                        new Var("x")
                )
        );
        System.out.println(e6);
        System.out.println(e6.getClass().getSimpleName());
    }

    private static void checkGetVar() {
        List<String> vars;
        Expression e1 = new Not(new Var("a"));
        vars = e1.getVariables();
        for (String v : vars) {
            System.out.print(v + " "); //should print a
        }
        System.out.println();
        Expression e2 = new Or(new Var("x"), new Var("y"));
        vars = e2.getVariables();
        for (String v : vars) {
            System.out.print(v + " "); //should print x, y
        }
        System.out.println();
        Expression e3 = new Not(new Or(new Var("p"), new Val(true)));
        vars = e3.getVariables();
        for (String v : vars) {
            System.out.print(v + " "); //should print p
        }
        System.out.println();
        Expression e4 = new Not(new And(new Var("p"), new Val(true)));
        vars = e4.getVariables();
        for (String v : vars) {
            System.out.print(v + " "); //should print p
        }
        System.out.println();
        Expression e5 = new Xor(new And(new Var("x"), new Var("y")), new Val(true));
        vars = e5.getVariables();
        for (String v : vars) {
            System.out.print(v + " "); //should print x, y
        }
        System.out.println();
        Expression e6 = new Not(
                new Xor(
                        new And(
                                new Val(true),
                                new Or(
                                        new Var("x"),
                                        new Var("y")
                                )
                        ),
                        new Var("x")
                )
        );
        vars = e6.getVariables();
        for (String v : vars) {
            System.out.print(v + " "); //should print x, y
        }
        System.out.println();
    }

    private static void checkAssign() {
        Expression e1 = new Not(new Var("a"));
        Expression e2 = new Xor(new And(new Var("x"), new Var("y")), new Val(true));
        Expression e4 = e1.assign("a", e2);
        System.out.println(e4);
        // ~(((x & y) ^ T))
        Expression e3 = e2.assign("y", e2);
        System.out.println(e3);
        // ((x & ((x & y) ^ T)) ^ T)
        e3 = e3.assign("x", new Val(false));
        System.out.println(e3);
        // ((F & ((F & y) ^ T)) ^ T)
    }

    private static void checkEvaluate() {
        Expression e2 = new Xor(new And(new Var("x"), new Var("y")), new Val(true));
        Map<String, Boolean> assignment = new TreeMap<>();
        assignment.put("x", true);
        assignment.put("y", false);
        Boolean value = null;
        try {
            value = e2.evaluate(assignment);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("The result is: " + value);
    }

    public static void main(String[] args) {
        checkString();
        checkGetVar();
        checkAssign();
        checkEvaluate();
        checkNs();
        checkSimple();
    }

    private static void checkSimple() {
        Expression e1 = new And(new Xnor(new Var("x"), new Var("x")), new Var("y"));
        System.out.println(e1.simplify());
        //should print: y
        Expression e2 = new Xor(new Or(new And(new Val(true), new Val(true)), new Val(false)), new Val(true));
        System.out.println(e2.simplify());
        //should print: F
    }

    private static void checkNs() {
        Expression e = new Xor(new Var("x"), new Var("y"));
        System.out.println(e.nandify());
        System.out.println(e.norify());
        // should print:
        // ((x ↑ (x ↑ y)) ↑ (y ↑ (x ↑ y)))
        // (((x ↓ x) ↓ (y ↓ y)) ↓ (x ↓ y))
    }
}
