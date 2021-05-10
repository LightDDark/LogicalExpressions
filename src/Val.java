import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Val implements Expression {
    private String string;
    private boolean val;
    public static final boolean F = false;
    public static final boolean T = true;

    Val(boolean val) {
        this.val = val;
        if (val) {
            this.string = new String("T");
        } else {
            this.string = new String("F");
        }
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return evaluate();
    }

    @Override
    public Boolean evaluate() throws Exception {
        return val;
    }

    @Override
    public List<String> getVariables() {
        List<String> emptyList = new ArrayList<>();
        return emptyList;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        /*this.string = string.replaceAll(var, expression.toString());
        if (var.matches(this.string) && expression instanceof Val) {
            try {
                this.val = expression.evaluate();
            } catch (Exception ex) {
                throw new RuntimeException("Problem with assigment to new bool to val: " + ex.toString());
            }
        }*/
        return new Val(val);
    }

    @Override
    public Expression nandify() {
        return new Val(val);
    }

    @Override
    public Expression norify() {
        return new Val(val);
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public Expression simplify() {
        return new Val(val);
    }
}
