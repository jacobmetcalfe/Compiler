package project1a;
import java.io.FileWriter;
import java.io.IOException;

public class SymbolTable {
    Symbol[] symbols;
    int index;

    public SymbolTable(){}

    public SymbolTable(int maxSize) {
        this.symbols = new Symbol[maxSize];
    }

    public void initializeSymbolTableFactorial(SymbolTable st)
    {
        st.AddSymbol("n", 'v', 10);
        st.AddSymbol("i", 'i', 0);
        st.AddSymbol("product", 'i', 0);
        st.AddSymbol("1", 'c', 1);
        st.AddSymbol("$temp", 'i', 0);
    }

    public void initializeSymbolTableSummation(SymbolTable st)
    {
        st.AddSymbol("n", 'v', 10);
        st.AddSymbol("i", 'i', 0);
        st.AddSymbol("product", 'i', 0);
        st.AddSymbol("1", 'c', 1);
        st.AddSymbol("$temp", 'i', 0);
        st.AddSymbol("0", 'c', 0);
    }


    public int AddSymbol(String symbol, char kind, int value){
        if (index+1 > symbols.length) return -1;
        for (int i = 0; i < index; i++){
            if (symbols[i].symbol.toLowerCase().equals(symbol.toLowerCase()))
                return index;
        }
        Symbol newsymbol = new Symbol(symbol, kind, value);
        this.symbols[index] = newsymbol;
        return index++;
    }

    public int AddSymbol(String symbol, char kind, double value){
        if (index+1 > symbols.length) return -1;
        for (int i = 0; i < index; i++){
            if (symbols[i].symbol.toLowerCase().equals(symbol.toLowerCase()))
                return i;
        }
        Symbol newsymbol = new Symbol(symbol, kind, value);
        this.symbols[index] = newsymbol;
        return index++;
    }

    public int AddSymbol(String symbol, char kind, String value){
        if (index+1 > symbols.length) return -1;
        for (int i = 0; i < index; i++){
            if (symbols[i].symbol.toLowerCase().equals(symbol.toLowerCase()))
                return i;
        }
        Symbol newsymbol = new Symbol(symbol, kind, value);
        this.symbols[index] = newsymbol;
        return index++;
    }

    public int LookupSymbol(String symbol){
        for (int i = 0; i < symbols.length; i++){
            if(this.symbols[i].symbol.toLowerCase().equals(symbol.toLowerCase())){
                return i;
            }
        }
        return -1;
    }

    String GetSymbol(int index){
        return this.symbols[index].symbol;
    }

    char GetKind(int index){
        return this.symbols[index].kind;
    }

    char GetDataType(int index){
        return this.symbols[index].type;
    }

    String GetString(int index){
        return this.symbols[index].sValue;
    }

    int GetInteger(int index){
        return this.symbols[index].iValue;
    }

    Double GetFloat(int index){
        return this.symbols[index].dValue;
    }

    void UpdateSymbol(int index, char kind, int value){
        this.symbols[index].kind = kind;
        this.symbols[index].iValue = value;
    }

    void UpdateSymbol(int index, char kind, double value){
        this.symbols[index].kind = kind;
        this.symbols[index].dValue = value;
    }

    void UpdateSymbol(int index, char kind, String value){
        this.symbols[index].kind = kind;
        this.symbols[index].sValue = value;
    }

    void PrintSymbolTable(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == null)
                break;
            writer.write(symbols[i].symbol.trim()+'|'+symbols[i].kind+'|'+symbols[i].type+'|');
            switch (symbols[i].type) {
                case 'i':
                    writer.write(Integer.toString(symbols[i].iValue)+"\n");
                    break;
                case 'f':
                    writer.write(Double.toString(symbols[i].dValue)+"\n");
                    break;
                case 's':
                    writer.write(symbols[i].sValue.trim()+"\n");
                    break;
                default:
                    break;
            }
        }
        writer.close();
    }
}
