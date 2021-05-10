public abstract class UnaryExpression extends BaseExpression {


    public UnaryExpression(Expression expression) {
        super(expression);
    }

    @Override
    protected abstract String getString();

    @Override
    public String toString() {
        String string = new String(getString() + "(" + getExpression().toString() + ")");
        return string;
    }


}
