package lippiWare.mathEval;

import java.util.TreeMap;

class MathEvalStack {
    
}

abstract class MathOperatorBase {
    abstract char getOperator();
    abstract int getOperatorPrecedenceVal();
    abstract int getOperandNum();
    abstract int execute(MathEvalStack stack);
}

class MathOperatorPlus extends MathOperatorBase {
    @Override
    char getOperator() {
        return '+';
    }

    @Override
    int getOperatorPrecedenceVal() {
        return 5;
    }

    @Override
    int getOperandNum() {
        return 2;
    }

    @Override
    int execute(MathEvalStack stack) {
        throw new Error("Not yet implemented!"); // TODO
    }
}

class MathOperatorMul extends MathOperatorBase {
    @Override
    char getOperator() {
        return '*';
    }

    @Override
    int getOperatorPrecedenceVal() {
        return 10;
    }

    @Override
    int getOperandNum() {
        return 2;
    }

    @Override
    int execute(MathEvalStack stack) {
        throw new Error("Not yet implemented!"); // TODO
    }
}

class MathOperatorPrecedenceDatabase {
    TreeMap<Integer, Integer> operators = new TreeMap<>();
    MathOperatorPrecedenceDatabase() {
        add(')',  0);
        add('+',  5);
        add('-',  5);
        add('|',  5);
        add('&',  5);
        add('!',  5);
        add('*', 10);
        add('/', 10);
        add('%', 10);
        add('^', 20);
        add('(', 90);
    }

    void add(char operator, int val) {
        operators.put(new Integer(operator), new Integer(val));
    }
    int get(char operator) {
        return operators.get(new Integer(operator));
    }
}

public class MathOperatorPrecedence {
    static MathOperatorPrecedenceDatabase database = new MathOperatorPrecedenceDatabase();
    static public int get(char operator) {
        return database.get(operator);
    }
}
