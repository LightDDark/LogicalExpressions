import java.util.Map;

/**
 * fdfdfd.
 */
public class Not extends UnaryExpression{
    private static final String STRING = "~";

    public Not(Expression expression) {
        super(expression);
    }

    @Override
    protected String getString() {
        return STRING;
    }

    @Override
    public Expression assign(String var, Expression expressions) {
        return new Not(super.assign(var, expressions));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !super.evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !super.evaluate();
    }

    @Override
    public Expression nandify() {
        return new Nand(super.nandify(), super.nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(super.norify(), super.norify());
    }

    @Override
    public Expression simplify() {
        return new Not(getExpression().simplify()).simplify();
    }
}
