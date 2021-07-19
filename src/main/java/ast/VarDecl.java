package ast;

// For simplicity all vars are initialized for now as grammar rule by parser
public class VarDecl extends Expression {

    String name;
//    boolean isInitialized;
    Expression initialExpression;

    public VarDecl(int line, String name, Type type, Expression initialExpression) {
        super(line);
        this.name = name;
        this.type = type;
        this.initialExpression = initialExpression;
        typeCheck();
//        if (initialExpression == null) isInitialized = false;
    }
    public VarDecl typeCheck() {
        initialExpression = ((IntegerLiteral) initialExpression).typeCheck();

        if (!this.type.equals(initialExpression.type)) {
            System.err.println("Semantic Error: required type: " + this.type + ", provided: " + initialExpression.type + " line: " + getLine());
        }
        return this;
    }
    @Override
    public String toString() {
        return "VarDecl{" +
                "name='" + name + '\'' +
                ", initialExpression=" + initialExpression +
                '}';
    }
}
