import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class BinaryExpression extends BaseExpression{
    private Expression expressionTwo;
    private  Map<String, Expression> simple = new HashMap<>();

    public BinaryExpression(Expression expression, Expression expressionTwo) {
        super(expression);
        this.expressionTwo = expressionTwo;
    }

    protected abstract void initializeMap();

    protected void addToMap(String key, Expression value) {
        simple.put(key, value);
    }

    @Override
    protected abstract String getString();

    @Override
    public String toString() {
        String string = new String("(" + getExpression().toString() + " " + getString() + " "
                + expressionTwo.toString() + ")");
        return string;
    }

    @Override
    public List<String> getVariables() {
        List<String> list = super.getVariables();
        list.addAll(expressionTwo.getVariables());
        return removeDuplicates(list);
    }

    public Expression getExpressionTwo() {
        return expressionTwo;
    }

    @Override
    public Expression simplify() {
        if (getVariables().isEmpty()) {
            try {
                return new Val(this.evaluate());
            } catch (Exception ex) {
                System.err.println("Couldn't Evaluate expression with no map");
            }
        }
        if (simple.containsKey(toString())) {
            return simple.get(toString()).simplify();
        }
        return null;
    }
}
