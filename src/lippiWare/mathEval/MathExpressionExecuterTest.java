import lippiWare.mathEval.MathExpressionExecuterBase;
import lippiWare.mathEval.MathExpressionParser;

public class MathExpressionExecuterTest {

    static void t(String expression, int expectedResult) {
        System.out.println("expression=" + expression);
        MathExpressionExecuterBase meeb = new MathExpressionExecuterBase();
        MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", expression);
        int result = mep.executeInt();
        System.out.println("mep=" + mep.getExpression() + " result = " + result);
        if (expectedResult != result)
            throw new Error("Invalid result is calculated!");
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
    }
}
