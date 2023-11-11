package christmas.common.constants;

public enum Symbol {
    COMMA(",", ','),
    HYPHEN("-", '-');

    private final String stringSymbol;
    private final char charSymbol;

    Symbol(String stringSymbol, char charSymbol) {
        this.stringSymbol = stringSymbol;
        this.charSymbol = charSymbol;
    }

    public String getStringSymbol() {
        return stringSymbol;
    }

    public char getCharSymbol() {
        return charSymbol;
    }
}
