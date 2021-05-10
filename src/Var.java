import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {
    private String string;
    private Val val = null;

    Var(String string) {
        this.string = string;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(string)) {
            return assignment.get(string);
        }
        throw new Exception("Expression not found in Map keys");
    }

    @Override
    public Boolean evaluate() throws Exception {
        if (this.val == null) {
            throw new Exception("string isn't assigned value");
        }
        return val.evaluate();
    }

    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.add(this.string);
        return list;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        String newString = this.string.replaceAll(var, expression.toString());
        Var newVar = new Var(newString);
        if (var.matches(this.string) && expression instanceof Val) {
            newVar.setVal((Val) expression);
        }
        return newVar;
    }

    @Override
    public Expression nandify() {
        return createCopy();
    }

    @Override
    public Expression norify() {
        return createCopy();
    }

    public void setVal(Val value) {
        this.val = val;
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public Expression simplify() {
        return createCopy();
    }

    private Expression createCopy() {
        Var newVar = new Var(string);
        if (val != null) {
            try {
                newVar.setVal(new Val(val.evaluate()));
            } catch (Exception ex) {
                ex.toString();
            }
        }
        return newVar;
    }
}
