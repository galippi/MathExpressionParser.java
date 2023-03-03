package lippiWare.mathEval;

abstract class MathEvalStackItemBase
{
    MathEvalStackItemBase(MathExpressionExecuterBase _parent) {
        parent = _parent;
    }

    abstract boolean isNumber();
    abstract int getInt();
    abstract double getDouble();
    abstract int executeInt();
    abstract int executeInt(int val);
    abstract double executeDouble();
    abstract double executeDouble(double val);

    MathExpressionExecuterBase parent;
}

class MathEvalStackItemVar extends MathEvalStackItemBase
{
    MathEvalStackItemVar(MathExpressionExecuterBase _parent, String _varName) {
        super(_parent);
        varName = _varName;
    }

    @Override
    boolean isNumber() {
        throw new Error("Not yet implemented!");
    }

    @Override
    int getInt() {
        return parent.getIntVar(varName);
    }

    @Override
    double getDouble() {
        return parent.getDoubleVar(varName);
    }

    @Override
    int executeInt() {
        int val = 0;
        return parent.setIntVar(varName, val);
    }

    @Override
    double executeDouble() {
        double val = 0;
        return parent.setDoubleVar(varName, val);
    }

    @Override
    int executeInt(int val) {
        parent.setIntVar(varName, val);
        return val;
    }

    @Override
    double executeDouble(double val) {
        parent.setDoubleVar(varName, val);
        return val;
    }

    String varName;
}

class MathEvalStackItemOperator extends MathEvalStackItemBase
{
    MathEvalStackItemOperator(MathExpressionExecuterBase _parent, MathOperatorBase _operator) {
        super(_parent);
        operator = _operator;
    }

    @Override
    boolean isNumber() {
        return false;
    }
    @Override
    int getInt() {
        throw new Error("Invalid command getInt!");
    }
    @Override
    double getDouble() {
        throw new Error("Invalid command getDouble!");
    }
    @Override
    int executeInt() {
        throw new Error("Not yet implemented!");
    }
    @Override
    double executeDouble() {
        throw new Error("Not yet implemented!");
    }

    MathOperatorBase operator;

    @Override
    int executeInt(int val) {
        throw new Error("Not yet implemented!");
    }

    @Override
    double executeDouble(double val) {
        throw new Error("Not yet implemented!");
    }
}

class MathEvalStackNumber extends MathEvalStackItemBase
{
    MathEvalStackNumber(MathExpressionExecuterBase _parent, String _number) {
       super(_parent);
       number = _number;
   }

   @Override
   boolean isNumber() {
       return true;
   }
   @Override
   int getInt() {
       return Integer.parseInt(number);
   }
   @Override
   double getDouble() {
       return Double.parseDouble(number);
   }
   @Override
   int executeInt() {
       throw new Error("Not yet implemented!");
   }
   @Override
   double executeDouble() {
       throw new Error("Not yet implemented!");
   }

   String number;

    @Override
    int executeInt(int val) {
        throw new Error("Not yet implemented!");
    }

    @Override
    double executeDouble(double val) {
        throw new Error("Not yet implemented!");
    }
}

class MathEvalStackVariable extends MathEvalStackItemBase
{
    MathEvalStackVariable(MathExpressionExecuterBase _parent, String _variableName) {
       super(_parent);
       variableName = _variableName;
   }

   @Override
   boolean isNumber() {
       return true;
   }
   @Override
   int getInt() {
       return parent.getIntVar(variableName);
   }
   @Override
   double getDouble() {
       return parent.getDoubleVar(variableName);
   }
   @Override
   int executeInt() {
       throw new Error("Not yet implemented!");
   }
   @Override
   double executeDouble() {
       throw new Error("Not yet implemented!");
   }

   String variableName;

    @Override
    int executeInt(int val) {
        throw new Error("Not yet implemented!");
    }

    @Override
    double executeDouble(double val) {
        throw new Error("Not yet implemented!");
    }
}

public class MathExpressionParser
{
    int idx = 0;
    public MathExpressionParser(MathExpressionExecuterBase parent, String result, String _expression) {
        expression = _expression;
        stack.put(new MathEvalStackItemVar(parent, result));
        String lastItem = "";
        while(idx < expression.length())
        {
            char c = getNextChar(expression);
            if (c == 0)
            {
                if (idx >= expression.length())
                    break;
                throw new Error("Invalid char at idx " + idx + " expression=" + expression);
            }
            if (isOperator(c))
            {
                if (!lastItem.isEmpty())
                {
                    if (isNumber(lastItem))
                    {
                        stack.put(new MathEvalStackNumber(parent, lastItem));
                    }else if (isVariable(lastItem))
                    {
                        stack.put(new MathEvalStackVariable(parent, lastItem));
                    }else
                        throw new Error("Error: unable to process expression '" + expression +"' idx=" + idx);
                    lastItem = "";
                }
                stack.put(new MathEvalStackItemOperator(parent, MathOperatorPrecedenceDatabase.get(c)));
            }else
            {
                lastItem = lastItem + c;
            }
        }
        if (!lastItem.isEmpty())
        {
            if (isNumber(lastItem))
            {
                stack.put(new MathEvalStackNumber(parent, lastItem));
            }else if (isVariable(lastItem))
            {
                stack.put(new MathEvalStackVariable(parent, lastItem));
            }else
                throw new Error("Error: unable to process expression '" + expression +"' idx=" + idx);
        }
    }

    private boolean isOperator(char c) {
        return MathOperatorPrecedenceDatabase.get(c) != null;
    }

    private boolean isNumber(String lastItem) {
        //throw new Error("Not yet implemented!");
        // TODO Auto-generated method stub
        return true;
    }

    private boolean isVariable(String lastItem) {
        throw new Error("Not yet implemented!");
    }

    private char getNextChar(String expression) {
        while(idx < expression.length())
        {
            char c = expression.charAt(idx++);
            if (c != ' ')
                return c;
        }
        return 0;
    }

    public int executeInt() {
        return stack.executeInt();
    }

    public String getExpression() {
        return expression;
    }

    MathEvalStack stack = new MathEvalStack(this);
    String expression;
}
