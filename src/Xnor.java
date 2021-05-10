import java.util.Map;

public class Xnor extends BinaryExpression {
    private static final String STRING = "#";

    Xnor(Expression expression, Expression expressionTwo) {
        super(expression, expressionTwo);
        initializeMap();
    }

    @Override
    protected void initializeMap() {
        Expression expTwo = getExpressionTwo();
        String expTwoStr = expTwo.toString();
        //XNOR
        addToMap(new String("(" + expTwoStr + " " + "#" + " "
                + expTwoStr + ")"), new Val(true));
    }

    @Override
    protected String getString() {
        return STRING;
    }

    @Override
    public Expression norify() {
        Expression a = super.norify();
        Expression b = super.norify(getExpressionTwo());
        return new Nor(new Nor(a, new Nor(a, b)), new Nor(b, new Nor(a, b)));
    }


    @Override
    public Expression nandify() {
        Expression a = super.nandify();
        Expression b = super.nandify(getExpressionTwo());
        return new Nand(new Nand(new Nand(a, a), new Nand(b, b)), new Nand(a, b));
    }

    @Override
    public Expression assign(String var, Expression expressions) {
        return new Xnor(super.assign(var, expressions), super.assign(var, expressions, getExpressionTwo()));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        boolean a = super.evaluate(assignment);
        boolean b = super.evaluate(assignment, getExpressionTwo());
        return !((a || b) && !(a && b));
    }

    @Override
    public Boolean evaluate() throws Exception {
        boolean a = super.evaluate();
        boolean b = super.evaluate(getExpressionTwo());
        return !((a || b) && !(a && b));
    }

    @Override
    public Expression simplify() {
        Expression e;
        e = super.simplify();
        if (e != null) {
            return e.simplify();
        }
        return new Xnor(getExpression().simplify(), getExpressionTwo().simplify()).simplify();
    }
}
