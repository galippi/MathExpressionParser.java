import lippiWare.mathEval.MathExpressionExecuterBase;
import lippiWare.mathEval.MathExpressionParser;

public class MathExpressionExecuterTest {

    static void t(String expression, int expectedResult) {
        MathExpressionExecuterBase meeb = new MathExpressionExecuterBase();
        MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", expression);
        int result = mep.executeInt();
        System.out.println("mep=" + mep.getExpression() + " result = " + result);
        if (expectedResult != result)
            throw new Error("Invalid result is calculated!");
    }

    static void t0() {
        MathExpressionExecuterBase meeb = new MathExpressionExecuterBase();
        //MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "5+7");
        MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "5");
        System.out.println("mep=" + mep.getExpression() + " result = " + mep.executeInt());
    }

    static void t1() {
        MathExpressionExecuterBase meeb = new MathExpressionExecuterBase();
        //MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "5+7");
        MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "-5");
        System.out.println("mep=" + mep.getExpression() + " result = " + mep.executeInt());
    }

    static void t2() {
        MathExpressionExecuterBase meeb = new MathExpressionExecuterBase();
        //MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "5+7");
        MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "5+7");
        System.out.println("mep=" + mep.getExpression() + " result = " + mep.executeInt());
    }

    static void t3() {
        MathExpressionExecuterBase meeb = new MathExpressionExecuterBase();
        //MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "5+7");
        MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "-5+7");
        System.out.println("mep=" + mep.getExpression() + " result = " + mep.executeInt());
    }

    static void t4() {
        MathExpressionExecuterBase meeb = new MathExpressionExecuterBase();
        //MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "5+7");
        MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "5+7+3");
        System.out.println("mep=" + mep.getExpression() + " result = " + mep.executeInt());
    }

    public static void main(String[] args) {
        t0();
        t1();
        t2();
        t3();
        t4();
        t("3+2-2-3", 0);
        t("3*2", 6);
        t("3*2+3", 9);
        t("2+3*2", 8);
        t("2-3*2", -4);
        t("2+3*2+4", 12);
    }

}
