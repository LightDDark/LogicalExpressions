import java.util.Map;

public class Nand extends BinaryExpression {
    private static final String STRING = "↑";

    Nand(Expression expression, Expression expressionTwo) {
        super(expression, expressionTwo);
        initializeMap();
    }

    @Override
    protected void initializeMap() {
        Expression exp = getExpression();
        Expression expTwo = getExpressionTwo();
        String expStr = exp.toString();
        String expTwoStr = expTwo.toString();
        //NANDS
        addToMap(new String("(" + expStr + " " + "↑" + " "
                + "T" + ")"), new Not(exp));
        addToMap(new String("(" + "T" + " " + "↑" + " "
                + expTwoStr + ")"), new Not(expTwo));
        addToMap(new String("(" + expStr + " " + "↑" + " "
                + "F" + ")"), new Val(true));
        addToMap(new String("(" + "F" + " " + "↑" + " "
                + expTwoStr + ")"), new Val(true));
        addToMap(new String("(" + expTwoStr + " " + "↑" + " "
                + expTwoStr + ")"), new Not(expTwo));
    }

    @Override
    protected String getString() {
        return STRING;
    }

    @Override
    public Expression norify() {
        return new Not(new And(getExpression(), getExpressionTwo())).norify();
    }


    @Override
    public Expression nandify() {
        Expression a = super.nandify();
        Expression b = super.nandify(getExpressionTwo());
        return new Nand(a, b);
    }

    @Override
    public Expression assign(String var, Expression expressions) {
        return new Nand(super.assign(var, expressions), super.assign(var, expressions, getExpressionTwo()));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(super.evaluate(assignment) && super.evaluate(assignment, getExpressionTwo()));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !(super.evaluate() && super.evaluate(getExpressionTwo()));
    }

    @Override
    public Expression simplify() {
        Expression e;
        e = super.simplify();
        if (e != null) {
            return e.simplify();
        }
        return new Nand(getExpression().simplify(), getExpressionTwo().simplify()).simplify();
    }
}
