import java.util.Map;

public class And extends BinaryExpression {
    private static final String STRING = "&";

    And(Expression expression, Expression expressionTwo) {
        super(expression, expressionTwo);
        initializeMap();
    }

    @Override
    protected void initializeMap() {
        Expression exp = getExpression();
        Expression expTwo = getExpressionTwo();
        String expStr = exp.toString();
        String expTwoStr = expTwo.toString();
        //ANDS
        addToMap(new String("(" + expStr + " " + "&" + " "
                + "T" + ")"), exp);
        addToMap(new String("(" + "T" + " " + "&" + " "
                + expTwoStr + ")"), expTwo);
        addToMap(new String("(" + expStr + " " + "&" + " "
                + "F" + ")"), new Val(false));
        addToMap(new String("(" + "F" + " " + "&" + " "
                + expTwoStr + ")"), new Val(false));
        addToMap(new String("(" + expTwoStr + " " + "&" + " "
                + expTwoStr + ")"), expTwo);
    }

    @Override
    protected String getString() {
        return STRING;
    }

    @Override
    public Expression norify() {
        return new Nor(new Not(getExpression()).norify(), new Not(getExpressionTwo()).norify());
    }

    @Override
    public Expression nandify() {
        Expression a = super.nandify();
        Expression b = super.nandify(getExpressionTwo());
        return new Nand(new Nand(a, b), new Nand(a, b));
    }

    @Override
    public Expression assign(String var, Expression expressions) {
        return new And(super.assign(var, expressions), super.assign(var, expressions, getExpressionTwo()));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return super.evaluate(assignment) && super.evaluate(assignment, getExpressionTwo());
    }

    @Override
    public Boolean evaluate() throws Exception {
        return super.evaluate() && super.evaluate(getExpressionTwo());
    }

    @Override
    public Expression simplify() {
        Expression e;
        e = super.simplify();
        if (e != null) {
            return e.simplify();
        }
        return new And(getExpression().simplify(), getExpressionTwo().simplify()).simplify();
    }
}
