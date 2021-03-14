package project1a;

public class Symbol {
    String symbol;
    char kind;
    char type;
    int iValue;
    double dValue;
    String sValue;

    public Symbol(String symbol, char kind, int iValue){
        this.symbol = symbol;
        this.kind = kind;
        this.type = 'i';
        this.iValue = iValue;
    }

    public Symbol(String symbol, char kind, double dValue){
        this.symbol = symbol;
        this.kind = kind;
        this.type = 'f';
        this.dValue = dValue;
    }

    public Symbol(String symbol, char kind, String sValue){
        this.symbol = symbol;
        this.kind = kind;
        this.type = 's';
        this.sValue = sValue;
    }
}
