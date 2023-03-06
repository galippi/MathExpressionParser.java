package lippiWare.mathEval;

import java.util.TreeMap;

abstract class MathOperatorBase {
    abstract char getOperator();
    abstract int getOperatorPrecedenceVal();
    abstract int getOperandNum();
    abstract int execute(MathEvalStack stack);
    abstract int execute(int val);
    abstract int execute(int valLeft, int valRight);
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

    @Override
    int execute(int val) {
        return val;
    }

    @Override
    int execute(int valLeft, int valRight) {
        return valLeft + valRight;
    }
}

class MathOperatorMinus extends MathOperatorBase {
    @Override
    char getOperator() {
        return '-';
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

    @Override
    int execute(int val) {
        return -val;
    }

    @Override
    int execute(int valLeft, int valRight) {
        return valLeft - valRight;
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

    @Override
    int execute(int val) {
        throw new Error("Not yet implemented!"); // TODO
    }

    @Override
    int execute(int valLeft, int valRight) {
        return valLeft * valRight;
    }
}

class MathOperatorDiv extends MathOperatorBase {
    @Override
    char getOperator() {
        return '/';
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

    @Override
    int execute(int val) {
        throw new Error("Not yet implemented!"); // TODO
    }

    @Override
    int execute(int valLeft, int valRight) {
        return valLeft / valRight;
    }
}

class MathOperatorRemain extends MathOperatorBase {
    @Override
    char getOperator() {
        return '%';
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

    @Override
    int execute(int val) {
        throw new Error("Not yet implemented!"); // TODO
    }

    @Override
    int execute(int valLeft, int valRight) {
        return valLeft % valRight;
    }
}

class MathOperatorPrecedenceDatabaseStorage {
    static TreeMap<Integer, MathOperatorBase> operators = new TreeMap<>();

    MathOperatorPrecedenceDatabaseStorage() {
        //add(')',  0);
        add(new MathOperatorPlus());
        add(new MathOperatorMinus());
        //add('|',  5);
        //add('&',  5);
        //add('!',  5);
        add(new MathOperatorMul());
        add(new MathOperatorDiv());
        add(new MathOperatorRemain());
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
