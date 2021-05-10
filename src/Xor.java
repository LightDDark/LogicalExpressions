import java.util.Map;

public class Xor extends BinaryExpression {
    private static final String STRING = "^";

    Xor(Expression expression, Expression expressionTwo) {
        super(expression, expressionTwo);
        initializeMap();
    }

    @Override
    protected void initializeMap() {
        Expression exp = getExpression();
        Expression expTwo = getExpressionTwo();
        String expStr = exp.toString();
        String expTwoStr = expTwo.toString();
        //XORS
        addToMap(new String("(" + expStr + " " + "^" + " "
                + "T" + ")"), new Not(exp));
        addToMap(new String("(" + "T" + " " + "^" + " "
                + expTwoStr + ")"), new Not(expTwo));
        addToMap(new String("(" + expStr + " " + "^" + " "
                + "F" + ")"), exp);
        addToMap(new String("(" + "F" + " " + "^" + " "
                + expTwoStr + ")"), expTwo);
        addToMap(new String("(" + expTwoStr + " " + "^" + " "
                + expTwoStr + ")"), new Val(false));
    }

    @Override
    protected String getString() {
        return STRING;
    }

    @Override
    public Expression norify() {
        Expression a = super.norify();
        Expression b = super.norify(getExpressionTwo());
        return new Nor(new And(a, b).norify(), new Nor(a, b));
    }

    @Override
    public Expression nandify() {
        Expression a = super.nandify();
        Expression b = super.nandify(getExpressionTwo());
        return new Nand(new Nand(a, new Nand(a, b)), new Nand(b, new Nand(a, b)));
    }

    @Override
    public Expression assign(String var, Expression expressions) {
        return new Xor(super.assign(var, expressions), super.assign(var, expressions, getExpressionTwo()));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        boolean a = super.evaluate(assignment);
        boolean b = super.evaluate(assignment, getExpressionTwo());
        return (a || b) && !(a && b);
    }

    @Override
    public Boolean evaluate() throws Exception {
        boolean a = super.evaluate();
        boolean b = super.evaluate(getExpressionTwo());
        return (a || b) && !(a && b);
    }

    @Override
    public Expression simplify() {
        Expression e;
        e = super.simplify();
        if (e != null) {
            return e.simplify();
        }
        return new Xor(getExpression().simplify(), getExpressionTwo().simplify()).simplify();
    }
}
