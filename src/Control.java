public class Control {

    public static boolean isCorrect(String str) {
        return checkRegex(str) && checkBrackets(str);
    }

    private static boolean checkRegex(String regex) {
        boolean b = regex.matches("[\\[\\]a-zA-Z0-9]+");
        if (b) {
            return true;
        } else {
            System.out.println("Строка содержит некорректные символы, остановка программы!");
            return false;
        }
    }

    private static boolean checkBrackets(String str) {
        int opened = 0;
        int closed = 0;


        char[] tmp = str.toCharArray();
        for (char c : tmp) {
            if (c == '[') {
                opened++;
            } else if (c == ']') {
                closed++;
            }
        }
        if (opened != closed) {
            System.out.println("Количество скобок некорректно, введите новую строку!");
            return false;
        }
        return true;
    }
}
