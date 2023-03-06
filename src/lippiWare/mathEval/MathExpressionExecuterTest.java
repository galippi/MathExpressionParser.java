import java.util.TreeMap;

import lippiWare.mathEval.MathExpressionExecuterBase;
import lippiWare.mathEval.MathExpressionParser;

class MathExpressionExecuterNothing extends MathExpressionExecuterBase {
    @Override
    public int setIntVar(String varName, int val) {
        //System.out.println("setIntVar varName="+varName + " val=" + val);
        return val;
    }
}

class MathExpressionExecuterMap extends MathExpressionExecuterBase {
    @Override
    public int setIntVar(String varName, int val) {
        //System.out.println("setIntVar varName="+varName + " val=" + val);
        variables.put(varName, new Integer(val));
        return val;
    }

    @Override
    public int getIntVar(String varName) {
        Integer val = variables.get(varName);
        if (val == null)
            throw new Error("Inavlid variable reading varName=" + varName);
        return val.intValue();
    }
    TreeMap<String, Integer> variables = new TreeMap<>();
}

public class MathExpressionExecuterTest {

    static void t(String expression, int expectedResult) {
        System.out.println("expression=" + expression);
        MathExpressionExecuterBase meeb = new MathExpressionExecuterNothing();
        try {
            MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", expression);
            int result = mep.executeInt();
            System.out.println("mep=" + mep.getExpression() + " result = " + result);
            if (expectedResult != result)
                throw new Error("Invalid result is calculated!");
        } catch (Exception e) {
            throw new Error("Exception is raised e=" + e.toString());
        }
    }

    static void te(String expression) {
        System.out.println("expression=" + expression);
        MathExpressionExecuterBase meeb = new MathExpressionExecuterNothing();
        try {
            MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", expression);
            int result = mep.executeInt();
            System.out.println("mep=" + mep.getExpression() + " result = " + result);
            throw new Error("Exception is not raised!");
        } catch (Exception e) {
            System.out.println("Exception is raised - ok - mep=" + expression + " e=" + e.toString());
        }
    }

    static void te2(String expression) {
        System.out.println("expression=" + expression);
        MathExpressionExecuterBase meeb = new MathExpressionExecuterNothing();
        try {
            MathExpressionParser mep = new MathExpressionParser(meeb, expression);
            int result = mep.executeInt();
            System.out.println("mep=" + mep.getExpression() + " result = " + result);
            throw new Error("Exception is not raised!");
        } catch (Exception e) {
            System.out.println("Exception is raised - ok - mep=" + expression + " e=" + e.toString());
        }
    }

    static MathExpressionExecuterMap td(String expression, int expectedResult) {
        System.out.println("expression=" + expression);
        MathExpressionExecuterMap meeb = new MathExpressionExecuterMap();
        try {
            MathExpressionParser mep = new MathExpressionParser(meeb, expression);
            int result = mep.executeInt();
            System.out.println("mep=" + mep.getExpression() + " result = " + result);
            if (expectedResult != result)
                throw new Error("Invalid result is calculated!");
            return meeb;
        } catch (Exception e) {
            throw new Error("Exception is raised e=" + e.toString());
        }
    }

    public static void main(String[] args) {
        t("5", 5);
        t("-5", -5);
        t("5+7", 12);
        t("-5+7", 2);
        t("5+7+3", 15);
        t("3+2-2-3", 0);
        t("3*2", 6);
        t("3*2+3", 9);
        t("2+3*2", 8);
        t("2-3*2", -4);
        t("2+3*2+4", 12);
        t("3*2+3*2", 12);
        t("3 * 2 + 3 * 2", 12);

        t("5*7", 35);
        t("-5*7", -35);
        t("1+5*7", 36);
        t("5*5-2", 23);

        t("5 % 7", 5);
        t("22 % 4", 2);
        t("5*4 % 4", 0);

        te("5 5");

        MathExpressionExecuterMap varMap;
        varMap = td("varRes=1+5*7", 36);
        if (varMap.getIntVar("varRes") != 36)
            throw new Error("Invalid result!");

        te2("varRes");
        te2("varRes = ");
        te2("  = 3");

    }
}
