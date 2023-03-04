package lippiWare.mathEval;

import java.util.Vector;

class ParsingResultInt
{
    int idx;
    int result;
    public MathEvalStackItemBase lastOperator;
}

class MathEvalStack {
    public MathEvalStack(MathExpressionParser _parent)
    {
        parent = _parent;
    }

    public void put(MathEvalStackItemBase stackItem) {
        items.add(stackItem);
    }

    public MathEvalStackItemBase get() {
        throw new Error("Not yet implemented!");
        //return null;
    }

    public int executeInt() {
        if (items.size() < 2)
            throw new Error("Math expression evaluation error - too short! size=" + items.size());
        //MathEvalStackItemBase result = items.get(0);
        int idx = 1;
        MathEvalStackItemBase lastOperator = null;
        MathEvalStackItemBase lastItem0 = null;
        MathEvalStackItemBase lastItem1 = null;
        while(idx < items.size())
        {
            MathEvalStackItemBase item = items.get(idx++);
            if (item.isNumber())
            {
                if (lastItem0 == null)
                {
                    if (lastOperator != null)
                    {
                        item = new MathEvalStackNumber(null, lastOperator.executeInt(item.getInt()));
                        lastOperator = null;
                    }
                    lastItem0 = item;
                }else
                if (lastItem1 == null)
                    lastItem1 = item;
                else
                    throw new Error("Invalid expression e=" + parent.getExpression());
            }else
            { // operator evaluation
                if (lastOperator == null)
                {
                    if (lastItem1 != null)
                        throw new Error("Invalid expression e=" + parent.getExpression());
                    lastOperator = item;
                }else
                {
                    if (lastItem1 != null)
                    {
                        do {
                            MathEvalStackItemOperator lastOperatorMesio = (MathEvalStackItemOperator)lastOperator;
                            MathEvalStackItemOperator itemMesio = (MathEvalStackItemOperator)item;
                            if (lastOperatorMesio.operator.getOperatorPrecedenceVal() >= itemMesio.operator.getOperatorPrecedenceVal())
                            {
                                int result = lastOperator.executeInt(lastItem0.getInt(), lastItem1.getInt());
                                lastItem0 = new MathEvalStackNumber(null, result);
                                lastItem1 = null;
                                lastOperator = item;
                                item = null;
                            }else
                            {
                                //throw new Error("Invalid expression e=" + parent.getExpression());
                                ParsingResultInt pri = parsing(idx, lastItem1, item);
                                idx = pri.idx;
                                int result = pri.result;
                                lastItem1 = new MathEvalStackNumber(null, result);
                                item = pri.lastOperator;
                            }
                        }while (item != null);
                    }else
                    {
                        throw new Error("Not yet implemented!");
                    }
                }
            }
        }
        int result;
        if (lastOperator == null)
        {
            result = lastItem0.getInt();
        }else
        {
            result = lastOperator.executeInt(lastItem0.getInt(), lastItem1.getInt());
        }
        MathEvalStackItemBase resultItem = items.get(0);
        resultItem.executeInt(result);
        return result;
    }

    private ParsingResultInt parsing(int idx, MathEvalStackItemBase lastItem0, MathEvalStackItemBase lastOperator) {
        MathEvalStackItemBase lastItem1 = null;
        while(idx < items.size())
        {
            MathEvalStackItemBase item = items.get(idx++);
            if (item.isNumber())
            {
                if (lastItem0 == null)
                {
                    if (lastOperator != null)
                    {
                        item = new MathEvalStackNumber(null, lastOperator.executeInt(item.getInt()));
                        lastOperator = null;
                    }
                    lastItem0 = item;
                }else
                if (lastItem1 == null)
                    lastItem1 = item;
                else
                    throw new Error("Invalid expression e=" + parent.getExpression());
            }else
            { // operator evaluation
                if (lastOperator == null)
                {
                    if (lastItem1 != null)
                        throw new Error("Invalid expression e=" + parent.getExpression());
                    lastOperator = item;
                }else
                {
                    if (lastItem1 != null)
                    {
                        MathEvalStackItemOperator lastOperatorMesio = (MathEvalStackItemOperator)lastOperator;
                        MathEvalStackItemOperator itemMesio = (MathEvalStackItemOperator)item;
                        if (lastOperatorMesio.operator.getOperatorPrecedenceVal() >= itemMesio.operator.getOperatorPrecedenceVal())
                        {
                            int result = lastOperator.executeInt(lastItem0.getInt(), lastItem1.getInt());
                            ParsingResultInt pri = new ParsingResultInt();
                            pri.idx = idx;
                            pri.result = result;
                            pri.lastOperator = item;
                            return pri;
                        }else
                        {
                            //throw new Error("Invalid expression e=" + parent.getExpression());
                            ParsingResultInt pri = parsing(idx, lastItem1, item);
                            idx = pri.idx;
                            int result = pri.result;
                            lastItem1 = new MathEvalStackNumber(null, result);
                        }
                    }else
                    {
                        throw new Error("Not yet implemented!");
                    }
                }
            }
        }
        int result = lastOperator.executeInt(lastItem0.getInt(), lastItem1.getInt());
        ParsingResultInt pri = new ParsingResultInt();
        pri.idx = idx;
        pri.result = result;
        pri.lastOperator = null;
        return pri;
    }

    Vector<MathEvalStackItemBase> items = new Vector<>();
    MathExpressionParser parent;
}