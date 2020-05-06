import java.util.Map;
import java.util.TreeMap;

public class NumberService {

    public static Number parseAndValidate(String symbol) throws Exception {

        int value = -1;
        NumberType type;

        for (Map.Entry<Integer, String> entry : getRomanMap().entrySet()) {
            if (symbol.equals(entry.getValue())) {
                value = entry.getKey();
                break;
            }
        }

        if (value != -1) {
            type = NumberType.ROMAN;
        } else {
            type = NumberType.ARABIC;
            value = toArabicNumber(symbol);
        }

        if (value < 1 || value > 10) {
            throw new Exception("Value is not true");
        }

        return new Number(value, type);
    }

    public static Number parseAndValidate(String symbol, NumberType type) throws Exception {

        Number number = parseAndValidate(symbol);
        if (number.getType() != type) {
            throw new Exception("numbers of different types");
        }

        return number;
    }

    public static TreeMap<Integer, String> getRomanMap() {

        TreeMap<Integer, String> romanMap = new TreeMap<>();
        romanMap.put(1, "I");
        romanMap.put(4, "IV");
        romanMap.put(5, "V");
        romanMap.put(9, "IX");
        romanMap.put(10, "X");
        romanMap.put(40, "XL");
        romanMap.put(50, "L");
        romanMap.put(90, "XC");
        romanMap.put(100, "C");

        return romanMap;
    }

    public static String toRomanNumber(int number) {
        TreeMap<Integer, String> romanMap = getRomanMap();

        int i = romanMap.floorKey(number);

        if (number == i) {
            return romanMap.get(number);
        }
        return romanMap.get(i) + toRomanNumber(number - i);
    }

    public static int toArabicNumber(String roman) throws Exception {
        int result = 0;

        TreeMap<Integer, String> romanMap = getRomanMap();

        for (Map.Entry<Integer, String> entry : romanMap.entrySet()) {
            if (roman.startsWith(entry.getValue())) {
                result += entry.getKey();
                roman = roman.substring(entry.getValue().length());
            }
        }

        if (roman.length() > 0) {
            throw new Exception(roman + " cannot be converted to a Roman");
        }

        return result;
    }
}
