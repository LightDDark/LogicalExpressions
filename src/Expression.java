import java.util.List;
import java.util.Map;

public interface Expression {
    // Evaluate the expression using the variable values provided
    // in the assignment, and return the result. If the expression
    // contains a variable which is not in the assignment, an exception
    // is thrown.
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    // A convenience method. Like the `evaluate(assignment)` method above,
    // but uses an empty assignment.
    Boolean evaluate() throws Exception;

    // Returns a list of the variables in the expression.
    List<String> getVariables();

    // Returns a nice string representation of the expression.
    String toString();

    // Returns a new expression in which all occurrences of the variable
    // var are replaced with the provided expression (Does not modify the
    // current expression).
    Expression assign(String var, Expression expression);

    // Returns the expression tree resulting from converting all the operations to the logical Nand operation.
    Expression nandify();
    // Returns the expression tree resulting from converting all the operations to the logical Nor operation.
    Expression norify();

    // Returned a simplified version of the current expression.
    Expression simplify();
}
