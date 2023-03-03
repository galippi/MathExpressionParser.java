import lippiWare.mathEval.MathExpressionExecuterBase;
import lippiWare.mathEval.MathExpressionParser;

public class MathExpressionExecuterTest {

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

    public static void main(String[] args) {
        t0();
        t1();
        t2();
        t3();
    }

}
