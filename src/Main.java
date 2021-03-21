import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // Читаем строку из консоли
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String consoleStr = reader.readLine();

        // Смотрим удовлетворяет ли условиям наша строка
        if (Control.isCorrect(consoleStr)) {
            StringBuilder sb = new StringBuilder(consoleStr);

            // Пока в нашем стрингбилдере есть скобки для парсинга содержимого внутри них
            while (sb.toString().contains("[")) {
                StringBuilder sbForDigits = new StringBuilder();
                StringBuilder sbForResult = new StringBuilder();

                // Индекс последней открытой скобки
                int openBracket = sb.lastIndexOf("[");
                // Индекс закрывающейся скобки для открывающейся предыдущей
                int lockedBracket = sb.indexOf("]", openBracket);

                // Само значение числа
                int digit;
                // Счетчик
                int i = 1;
                // Индекс числа
                int digitIndex;
                while (true) {
                    digitIndex = openBracket - i;
                    // Пока элемент из sb под индексом digitIndex является числом, то
                    if (Character.isDigit(sb.charAt(digitIndex))) {
                        sbForDigits.insert(0, sb.charAt(openBracket - i));
                        i++;
                        if (digitIndex == 0) {
                            break;
                        }
                    } else {
                        digitIndex++;
                        break;
                    }
                }
                digit = Integer.parseInt(sbForDigits.toString());

                // В цикле пробегаемся по строке внутри квадратных скобок ровно столько раз, чему равно число
                // между этими скобками
                for (int j = 0; j < digit; j++) {
                    sbForResult.append(sb.substring(openBracket + 1, lockedBracket));
                }
                // Далее меняем значение внутри скобок основного стрингбилдера на то, что есть внутри sbForResult
                sb.replace(digitIndex, lockedBracket + 1, sbForResult.toString());
            }
            // Выводим результат
            System.out.println(sb.toString());
        }
    }
}
