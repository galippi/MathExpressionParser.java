package lippiWare.mathEval;

abstract class MathEvalStackItemBase
{
    MathEvalStackItemBase(MathExpressionExecuterBase _parent) {
        parent = _parent;
    }

    abstract boolean isNumber();
    abstract int getInt() throws Exception;
    abstract double getDouble() throws Exception;
    abstract int executeInt() throws Exception;
    abstract int executeInt(int val) throws Exception;
    abstract int executeInt(int intLeft, int intRight) throws Exception;
    abstract double executeDouble() throws Exception;
    abstract double executeDouble(double val) throws Exception;

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

    @Override
    int executeInt(int intLeft, int intRight) {
        throw new Error("Invalid expression executeInt intLeft" + intLeft + " intRight=" + intRight);
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

    public MathOperatorBase operator;

    @Override
    int executeInt(int val) {
        return operator.execute(val);
    }

    @Override
    double executeDouble(double val) {
        throw new Error("Not yet implemented!");
    }

    @Override
    int executeInt(int intLeft, int intRight) {
        return operator.execute(intLeft, intRight);
    }
}

class MathEvalStackNumber extends MathEvalStackItemBase
{
    MathEvalStackNumber(MathExpressionExecuterBase _parent, String _number) {
       super(_parent);
       number = _number;
   }

    MathEvalStackNumber(MathExpressionExecuterBase _parent, int val) {
        super(_parent);
        number = "" + val;
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

    @Override
    int executeInt(int val) {
        throw new Error("Not yet implemented!");
    }

    @Override
    double executeDouble(double val) {
        throw new Error("Not yet implemented!");
    }

    @Override
    int executeInt(int intLeft, int intRight) {
        throw new Error("Invalid expression executeInt intLeft" + intLeft + " intRight=" + intRight);
    }

    String number;
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

    @Override
    int executeInt(int val) {
        throw new Error("Not yet implemented!");
    }

    @Override
    double executeDouble(double val) {
        throw new Error("Not yet implemented!");
    }

    @Override
    int executeInt(int intLeft, int intRight) {
        throw new Error("Invalid expression executeInt intLeft" + intLeft + " intRight=" + intRight);
    }

    String variableName;
}

public class MathExpressionParser
{
    int idx = 0;
    public MathExpressionParser(MathExpressionExecuterBase parent, String result, String _expression)
        throws Exception
    {
        process(parent, result, _expression);
    }

    public MathExpressionParser(MathExpressionExecuterBase parent, String expression) throws Exception {
        int eqIdx = expression.indexOf('=');
        if (eqIdx < 1)
            throw new Exception("MathExpressionParser.ctor invalid expression=" + expression + " (no =)!");
        String resultVar = expression.substring(0, eqIdx).trim();
        if (resultVar.isEmpty())
            throw new Exception("MathExpressionParser.ctor invalid expression=" + expression + " (missing result variable)!");
        String expressionRest = expression.substring(eqIdx + 1).trim();
        if (expressionRest.isEmpty())
            throw new Exception("MathExpressionParser.ctor invalid expression=" + expression + " (missing expression)!");
        process(parent, resultVar, expressionRest);
    }

    void process(MathExpressionExecuterBase parent, String result, String _expression) throws Exception
    {
        expression = _expression;
        stack.put(new MathEvalStackItemVar(parent, result));
        String lastItem = "";
        int bracketDeep = 0;
        while(idx < expression.length())
        {
            NextChar nc = getNextChar(expression);
            if (nc == null)
            {
                if (idx >= expression.length())
                    break;
                throw new Error("Invalid char at idx " + idx + " expression=" + expression);
            }
            if (nc.c == '(')
                bracketDeep++;
            else if (nc.c == ')')
            {
                if (bracketDeep == 0)
                    throw new Error("Invalid closing bracket at position " + idx);
                bracketDeep--;
            }
            if (isOperator(nc.c))
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
                stack.put(new MathEvalStackItemOperator(parent, MathOperatorPrecedenceDatabase.get(nc.c)));
            }else
            {
                if ((!lastItem.isEmpty()) && (nc.spaceIsSkipped))
                    throw new Exception("Space separated numbers '" + expression + "'!");
                lastItem = lastItem + nc.c;
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
        if (bracketDeep != 0)
            throw new Error("Error: missing closing bracket '" + expression +"' idx=" + idx);
    }

    private boolean isOperator(char c) {
        return MathOperatorPrecedenceDatabase.get(c) != null;
    }

    private boolean isNumber(String lastItem) {
        if (lastItem.isEmpty())
            throw new Error("Error: invalid call of isNumber!");
        for (int i = 0; i < lastItem.length(); i++)
            if (!Character.isDigit(lastItem.charAt(i)))
                return false;
        return true;
    }

    private boolean isVariable(String lastItem) {
        throw new Error("Not yet implemented!");
    }

    class NextChar
    {
        char c;
        boolean spaceIsSkipped;
    }
    NextChar nc = new NextChar();
    private NextChar getNextChar(String expression) {
        nc.spaceIsSkipped = false;
        while(idx < expression.length())
        {
            nc.c = expression.charAt(idx++);
            if (nc.c != ' ')
                return nc;
            nc.spaceIsSkipped = true;
        }
        return null;
    }

    public int executeInt() throws Exception
    {
        return stack.executeInt();
    }

    public String getExpression() {
        return expression;
    }

    MathEvalStack stack = new MathEvalStack(this);
    String expression;
}
