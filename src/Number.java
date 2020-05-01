public class Number {

    private int value;
    private NumberType type;

    public Number(int value, NumberType type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public NumberType getType() {
        return type;
    }

}
