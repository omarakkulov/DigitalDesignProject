import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String consoleStr = reader.readLine();

        if (Control.isCorrect(consoleStr)) {
            StringBuilder sb = new StringBuilder(consoleStr);

            while (sb.toString().contains("[")) {
                StringBuilder tmp = new StringBuilder();

                // Индекс последней открывающей скобки
                int openBracket = sb.lastIndexOf("[");
                // Индекс закрывающейся скобки для предыдущей открывающейся
                int closedBracket = sb.indexOf("]", openBracket);
                // Индекс числа
                int numIndex = openBracket - 1;
                // Значение числа
                int num = Integer.parseInt(sb.substring(numIndex, openBracket));


                int capacity = 0;
                int j = 0;
                while (numIndex - j >= 0) {
                    if (Character.isDigit(sb.charAt(numIndex - j))) {
                        capacity++;
                    } else break;
                    j++;
                }

                StringBuilder numTmp = new StringBuilder();
                int k = 0;
                while (k < capacity) {
                    String strTmp = Character.toString(sb.charAt(numIndex - k));
                    k++;
                    numTmp.append(strTmp);
                }
                num = Integer.parseInt(numTmp.toString());

                // Прогоняемся по части с числом и строкой внутри скобок и раскрываем должным образом
                for (int i = 0; i < num; i++) {
                    tmp.append(sb.substring(openBracket + 1, closedBracket));
                }
                // В поданной строке, учитывая индексы, меняем нужную часть строки на нужную распакованную
                sb.replace(numIndex - capacity + 1, closedBracket + 1, tmp.toString());
            }
            System.out.println(sb.toString());
        }
    }

    public static boolean isDigit(int numIndex, int lastOpenedBracket, StringBuilder sb) {
        int res;

        try {
            res = Integer.parseInt(sb.substring(numIndex, lastOpenedBracket));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
