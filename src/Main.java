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
                int lastOpenedBracket = sb.lastIndexOf("[");
                // Индекс закрывающейся скобки для предыдущей открывающейся
                int closedBracket = sb.indexOf("]", lastOpenedBracket);
                // Индекс числа
                int numIndex = lastOpenedBracket - 1;
                // Значение числа
                int num = Integer.parseInt(sb.substring(numIndex, lastOpenedBracket));

                // Прогоняемся по части с числом и строкой внутри скобок и раскрываем должным образом
                for (int i = 0; i < num; i++) {
                    tmp.append(sb.substring(lastOpenedBracket + 1, closedBracket));
                }
                // В поданной строке, учитывая индексы, меняем нужную часть строки на нужную распакованную
                sb.replace(numIndex, closedBracket + 1, tmp.toString());
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
