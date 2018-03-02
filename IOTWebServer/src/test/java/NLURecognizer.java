import server.nlu.IntentRecognizer;
import server.nlu.entities.BestMatch;

public class NLURecognizer {

    public static void main(String[] args) {
        BestMatch match = IntentRecognizer.getInstance().processUtterance("turn on hall device");
    }
}
