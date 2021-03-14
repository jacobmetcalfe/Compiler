package project1a;

import java.io.FileWriter;
import java.io.IOException;

public class QuadTable {
    Quad[] quadTable;
    int nextAvailable;

    public QuadTable(int maxSize) {
        this.quadTable = new Quad[maxSize];
    }

    int NextQuad() {
        return nextAvailable;
    }

    void AddQuad(int opcode, int op1, int op2, int op3) {
        Quad newQuad = new Quad(opcode, op1, op2, op3);
        quadTable[NextQuad()] = newQuad;
        nextAvailable++;
    }

    public static void initializeQuadTableFactorial(QuadTable qt){
        qt.AddQuad(5,3,0,2);
        qt.AddQuad(5,3,0,1);
        qt.AddQuad(3,1,0,4);
        qt.AddQuad(12,4,0,7);
        qt.AddQuad(2,2,1,2);
        qt.AddQuad(4,1,3,1);
        qt.AddQuad(14,0,0,2);
        qt.AddQuad(16,2,0,0);
        qt.AddQuad(0,0,0,0);
    }

    public static void initializeQuadTableSummation(QuadTable qt){
        qt.AddQuad(5,5,0,2);
        qt.AddQuad(5,3,0,1);
        qt.AddQuad(3,1,0,4);
        qt.AddQuad(12,4,0,7);
        qt.AddQuad(4,2,1,2);
        qt.AddQuad(4,1,3,1);
        qt.AddQuad(14,0,0,2);
        qt.AddQuad(16,2,0,0);
        qt.AddQuad(0,0,0,0);
    }

    int GetQuad(int index, int column) {
        switch (column) {
            case 0:
                return quadTable[index].opcode;
            case 1:
                return quadTable[index].op1;
            case 2:
                return quadTable[index].op2;
            case 3:
                return quadTable[index].op3;
            default:
                return -1;
        }
    }

    void UpdateQuad(int index, int opcode, int op1, int op2, int op3) {
        this.quadTable[index] = new Quad(opcode, op1, op2, op3);
    }

    void PrintQuadTable(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (int i = 0; i < quadTable.length; i++) {
            if (quadTable[i] != null){
                writer.write(quadTable[i].opcode + "|" + quadTable[i].op1 + "|" + quadTable[i].op2 + "|" + quadTable[i].op3 + "|\n");
            }
        }
        writer.close();
    }
}
