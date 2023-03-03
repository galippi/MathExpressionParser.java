package lippiWare.mathEval;

import java.util.TreeMap;

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

class MathOperatorPrecedenceDatabaseStorage {
    static TreeMap<Integer, MathOperatorBase> operators = new TreeMap<>();

    MathOperatorPrecedenceDatabaseStorage() {
        //add(')',  0);
        add(new MathOperatorPlus());
        //add('-',  5);
        //add('|',  5);
        //add('&',  5);
        //add('!',  5);
        add(new MathOperatorMul());
        //add('/', 10);
        //add('%', 10);
        //add('^', 20);
        //add('(', 90);
    }

    static void add(MathOperatorBase operator) {
        operators.put(new Integer(operator.getOperator()), operator);
    }
    static public MathOperatorBase get(char operator) {
        return operators.get(new Integer(operator));
    }
}

public class MathOperatorPrecedenceDatabase {
    static MathOperatorPrecedenceDatabaseStorage database = new MathOperatorPrecedenceDatabaseStorage();
    static public MathOperatorBase get(char operator) {
        return MathOperatorPrecedenceDatabaseStorage.get(operator);
    }
}
