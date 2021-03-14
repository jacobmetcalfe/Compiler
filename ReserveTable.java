package project1a;

import java.io.FileWriter;
import java.io.IOException;

public class ReserveTable {

    Reserve[] reserveTable;
    int index;

    public ReserveTable(){}

    public ReserveTable(int maxSize){
        this.reserveTable = new Reserve[maxSize];
    }

    public int Add(String name, int code){
        Reserve newReserve = new Reserve(name, code);
        reserveTable[index] = newReserve;
        return index++;
    }

    public int LookupName(String name){
        for (int i = 0; i < reserveTable.length; i++){
            if (reserveTable[i].opName.toLowerCase().equals(name.toLowerCase())){
                return reserveTable[i].code;
            }
        }
        return -1;
    }

    public String LookupCode(int code){
        for (int i = 0; i < reserveTable.length; i++){
            if (reserveTable[i].code == code){
                return reserveTable[i].opName;
            }
        }
        return "";
    }

    public void PrintReserveTable (String filename) throws IOException{
        FileWriter writer = new FileWriter(filename);
        for (int i = 0; i < reserveTable.length; i++) {
            if (reserveTable[i] != null){
                writer.write(reserveTable[i].opName.toUpperCase() + "|" + reserveTable[i].code + "\n");
            }
        }
        writer.close();
    }
}
