import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseExpression implements Expression {
    private Expression expression;

    public BaseExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return evaluate(assignment, this.expression);
    }

    public Boolean evaluate(Map<String, Boolean> assignment, Expression exp) throws Exception {
        if (assignment.containsKey(exp.toString())) {
            return assignment.get(exp.toString());
        }
        return exp.evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return evaluate(this.expression);
    }

    public Boolean evaluate(Expression exp) throws Exception {
        return exp.evaluate();
    }

    @Override
    public List<String> getVariables() {
        return removeDuplicates(expression.getVariables());
    }

    protected List<String> getVariables(Expression exp) {
        return exp.getVariables();
    }

    protected List<String> removeDuplicates(List<String> list) {
        // Create a new LinkedHashSet
        // Add the elements to set
        Set<String> set = new LinkedHashSet<>(list);
        // Clear the list
        list.clear();
        // add the elements of set
        // with no duplicates to the list
        list.addAll(set);
        // return the list
        return list;
    }

    @Override
    public Expression assign(String var, Expression expressions) {
        return assign(var, expressions, this.expression);
    }

    protected Expression assign(String var, Expression expressions, Expression exper) {
        Expression exp = exper.assign(var, expressions);
        return exp;
    }

    protected Expression getExpression() {
        return expression;
    }

    protected abstract String getString();

    @Override
    public Expression nandify() {
        return nandify(this.expression);
    }

    protected Expression nandify(Expression exp) {
        return exp.nandify();
    }

    @Override
    public Expression norify() {
        return norify(this.expression);
    }

    protected Expression norify(Expression exp) {
        return exp.norify();
    }

}
