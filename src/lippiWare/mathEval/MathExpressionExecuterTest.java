import lippiWare.mathEval.MathExpressionExecuterBase;
import lippiWare.mathEval.MathExpressionParser;

public class MathExpressionExecuterTest {

    public static void main(String[] args) {
        MathExpressionExecuterBase meeb = new MathExpressionExecuterBase();
        //MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "5+7");
        MathExpressionParser mep = new MathExpressionParser(meeb, "varResult", "5");
        System.out.println("result = " + mep.executeInt());
    }

}
