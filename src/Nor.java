import java.util.Map;

public class Nor extends BinaryExpression {
    private static final String STRING = "↓";

    Nor(Expression expression, Expression expressionTwo) {
        super(expression, expressionTwo);
        initializeMap();
    }

    @Override
    protected void initializeMap() {
        Expression exp = getExpression();
        Expression expTwo = getExpressionTwo();
        String expStr = exp.toString();
        String expTwoStr = expTwo.toString();
        //NORS
        addToMap(new String("(" + expStr + " " + "↓" + " "
                + "T" + ")"), new Val(false));
        addToMap(new String("(" + "T" + " " + "↓" + " "
                + expTwoStr + ")"), new Val(false));
        addToMap(new String("(" + expStr + " " + "↓" + " "
                + "F" + ")"), new Not(exp));
        addToMap(new String("(" + "F" + " " + "↓" + " "
                + expTwoStr + ")"), new Not(expTwo));
        addToMap(new String("(" + expTwoStr + " " + "↓" + " "
                + expTwoStr + ")"), new Not(expTwo));
    }

    @Override
    protected String getString() {
        return STRING;
    }

    @Override
    public Expression norify() {
        return new Nor(super.norify(), super.norify(getExpressionTwo()));
    }


    @Override
    public Expression nandify() {
        Expression nandOr = new Or(getExpression(), getExpressionTwo()).nandify();
        return new Nand(nandOr, nandOr);
    }

    @Override
    public Expression assign(String var, Expression expressions) {
        return new Nor(super.assign(var, expressions), super.assign(var, expressions, getExpressionTwo()));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(super.evaluate(assignment) || super.evaluate(assignment, getExpressionTwo()));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !(super.evaluate() || super.evaluate(getExpressionTwo()));
    }

    @Override
    public Expression simplify() {
        Expression e;
        e = super.simplify();
        if (e != null) {
            return e.simplify();
        }
        return new Nor(getExpression().simplify(), getExpressionTwo().simplify()).simplify();
    }
}
