package Utils;

import java.util.HashSet;

public class SymbolGenerator {
    private int i;
    private HashSet<String> allSymbols;

    public SymbolGenerator() {
        this.i = 0;
        this.allSymbols = new HashSet<>();
    }

    public String getNewSymbol() {
        String symbol = "NEW" + i;
        i++;
        allSymbols.add(symbol);
        return symbol;
    }

    public boolean hasSymbol(String symbol) {
        return allSymbols.contains(symbol);
    }
}
