import java.util.Map;

public class Or extends BinaryExpression {
    private static final String STRING = "|";

    public Or(Expression x, Expression y) {
        super(x, y);
        initializeMap();
    }

    @Override
    protected void initializeMap() {
        Expression exp = getExpression();
        Expression expTwo = getExpressionTwo();
        String expStr = exp.toString();
        String expTwoStr = expTwo.toString();
        //ORS
        addToMap(new String("(" + expStr + " " + "|" + " "
                + "T" + ")"), new Val(true));
        addToMap(new String("(" + "T" + " " + "|" + " "
                + expTwoStr + ")"), new Val(true));
        addToMap(new String("(" + expStr + " " + "|" + " "
                + "F" + ")"), exp);
        addToMap(new String("(" + "F" + " " + "|" + " "
                + expTwoStr + ")"), expTwo);
        addToMap(new String("(" + expTwoStr + " " + "|" + " "
                + expTwoStr + ")"), expTwo);
    }

    @Override
    protected String getString() {
        return STRING;
    }

    @Override
    public Expression norify() {
        return new Not(new Nor(getExpression(), getExpressionTwo())).norify();
    }

    @Override
    public Expression nandify() {
        Expression a = super.nandify();
        Expression b = super.nandify(getExpressionTwo());
        return new Nand(new Nand(a, a), new Nand(b, b));
    }

    @Override
    public Expression assign(String var, Expression expressions) {
        return new Or(super.assign(var, expressions), super.assign(var, expressions, getExpressionTwo()));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return super.evaluate(assignment) || super.evaluate(assignment, getExpressionTwo());
    }

    @Override
    public Boolean evaluate() throws Exception {
        return super.evaluate() || super.evaluate(getExpressionTwo());
    }

    @Override
    public Expression simplify() {
        Expression e;
        e = super.simplify();
        if (e != null) {
            return e.simplify();
        }
        return new Or(getExpression().simplify(), getExpressionTwo().simplify()).simplify();
    }
}
