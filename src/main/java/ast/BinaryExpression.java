package ast;
enum Operator {
    ADD,
    SUB,
    ASSIGN,
    EQUAL,
}
public class BinaryExpression extends Expression {

    Expression lhs;
    String operator;
    Expression rhs;

    public BinaryExpression(int line, Expression lhs, String operator, Expression rhs) {
        super(line);
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
    }


    @Override
    public String toString() {
        return "BinaryExpression{" +
                "lhs=" + lhs +
                ", operator='" + operator + '\'' +
                ", rhs=" + rhs +
                '}';
    }

    @Override
    public Expression typeCheck() {
        return null;  // Todo: use a better design pattern;
    }

    //    Operator operator;
}

