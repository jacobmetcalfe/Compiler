package project1a;

import java.io.FileWriter;
import java.io.IOException;

public class Interpreter {
    private final ReserveTable Instructions;

    public Interpreter(){
        Instructions = new ReserveTable(MAX_QUAD);
        initReserve(Instructions);
    }
    int programCounter = 0;
    public static int MAX_QUAD = 1000;

    static final int STOP = 0;
    static final int DIV = 1;
    static final int MUL = 2;
    static final int SUB = 3;
    static final int ADD = 4;
    static final int MOV = 5;
    static final int STI = 6;
    static final int LDI = 7;
    static final int BNZ = 8;
    static final int BNP = 9;
    static final int BNN = 10;
    static final int BZ = 11;
    static final int BP = 12;
    static final int BN = 13;
    static final int BR = 14;
    static final int BINDR = 15;
    static final int PRINT = 16;


    public void initReserve(ReserveTable optable){
        optable.Add("STOP", 0);
        optable.Add("DIV", 1);
        optable.Add("MUL", 2);
        optable.Add("SUB", 3);
        optable.Add("ADD", 4);
        optable.Add("MOV", 5);
        optable.Add("STI", 6);
        optable.Add("LDI", 7);
        optable.Add("BNZ", 8);
        optable.Add("BNP", 9);
        optable.Add("BNN", 10);
        optable.Add("BZ", 11);
        optable.Add("BP", 12);
        optable.Add("BN", 13);
        optable.Add("BR", 14);
        optable.Add("BINDR", 15);
        optable.Add("PRINT", 16);
    }

    private String makeTraceString(int pc, int opcode,int op1,int op2,int op3 )
    {
        String result = "";
        result = "PC = "+String.format("%04d", pc)+": "+(Instructions.LookupCode(opcode)+"     ").substring(0,6)+String.format("%02d",op1)+ ", "+String.format("%02d",op2)+", "+String.format("%02d",op3) + "\n";
        System.out.print(result);
        return result;
    }

    public void initializeFactorialTest(SymbolTable st, QuadTable qt){
        st.initializeSymbolTableFactorial(st);
        qt.initializeQuadTableFactorial(qt);
    }

    public void initializeSummationTest(SymbolTable st, QuadTable qt){
        st.initializeSymbolTableSummation(st);
        qt.initializeQuadTableSummation(qt);
    }


    public void InterpretQuads(QuadTable Q, SymbolTable S, boolean TraceOn, String filename) throws IOException {
        int lastIndex = Q.quadTable.length- 1;
        boolean stop = false;
        programCounter = 0;
        FileWriter writer = new FileWriter(filename);
        // Quad Parser
        while ((programCounter <= lastIndex) && !stop)  {

            int opCode = Q.GetQuad(programCounter, 0);
            int op1 = Q.GetQuad(programCounter, 1);
            int op2 = Q.GetQuad(programCounter, 2);
            int op3 = Q.GetQuad(programCounter, 3);

            if (TraceOn){
                if (op3 < S.symbols.length){
                    writer.write(makeTraceString(programCounter,opCode,op1,op2,op3));
                }
            }

            if (Instructions.LookupCode(opCode) != ""){
                switch (opCode)
                {
                    case STOP: // STOP - Terminate Program
                        stop = true;
                        System.out.println("Execution terminated by program STOP.");
                        break;
                    case DIV: // DIV - Place op1/op2 into op3
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, S.symbols[op1].iValue/S.symbols[op2].iValue);
                                break;
                            case 'f':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, ((int)(S.symbols[op1].dValue/(int)S.symbols[op2].dValue)));
                                break;
                            case 's':
                                break;
                        }
                        break;
                    case MUL: // MUL - Place op1*op2 into op3
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, S.symbols[op1].iValue*S.symbols[op2].iValue);
                                break;
                            case 'f':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, (S.symbols[op1].dValue*S.symbols[op2].dValue));
                                break;
                            case 's':
                                break;
                        }
                        break;
                    case SUB: // SUB - Place op1-op2 into op3
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, S.symbols[op1].iValue-S.symbols[op2].iValue);
                                break;
                            case 'f':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, (S.symbols[op1].dValue-S.symbols[op2].dValue));
                                break;
                            case 's':
                                break;
                        }
                        break;
                    case ADD: // ADD - Place op1+op2 into op3
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, S.symbols[op1].iValue+S.symbols[op2].iValue);
                                break;
                            case 'f':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, (S.symbols[op1].dValue+S.symbols[op2].dValue));
                                break;
                            case 's':
                                break;
                        }
                        break;
                    case MOV: // MOV - Place op1 into op3
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, S.symbols[op1].iValue);
                                break;
                            case 'f':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, S.symbols[op1].dValue);
                                break;
                            case 's':
                                S.UpdateSymbol(op3, S.symbols[op3].kind, S.symbols[op1].sValue);
                        }
                        break;
                    case STI: // STI Store indexed
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                S.UpdateSymbol(op1,S.symbols[op1].kind, S.symbols[op2+op3].iValue);
                                break;
                            case 'f':
                                S.UpdateSymbol(op1,S.symbols[op1].kind, S.symbols[op2+op3].dValue);
                                break;
                            case 's':
                                S.UpdateSymbol(op1, S.symbols[op1].kind, S.symbols[op2+op3].sValue);
                                break;
                        }
                        break;
                    case LDI: // LDI Load indexed
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, S.symbols[op1+op2].iValue);
                                break;
                            case 'f':
                                S.UpdateSymbol(op3,S.symbols[op3].kind, S.symbols[op1+op2].dValue);
                                break;
                            case 's':
                                S.UpdateSymbol(op3, S.symbols[op3].kind, S.symbols[ op1+op2].sValue);
                                break;
                        }
                        break;
                    case BNZ: // BNZ Branch Not Zero
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                if ((S.symbols[op1].iValue) !=0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 'f':
                                if ((S.symbols[op1].dValue) !=0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 's':
                                break;
                        }
                        break;
                    case BNP: // BNP Branch Not Positive
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                if ((S.symbols[op1].iValue) <=0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 'f':
                                if ((S.symbols[op1].dValue) <=0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 's':
                                break;
                        }
                        break;
                    case BNN: // BNN Branch Not Negative
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                if ((S.symbols[op1].iValue) >=0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 'f':
                                if ((S.symbols[op1].dValue) >=0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 's':
                                break;
                        }
                        break;
                    case BZ: // BZ Branch Zero
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                if ((S.symbols[op1].iValue) ==0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 'f':
                                if ((S.symbols[op1].dValue) ==0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 's':
                                break;
                        }
                        break;
                    case BP: // BP Branch Positive
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                if ((S.symbols[op1].iValue) > 0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 'f':
                                if ((S.symbols[op1].dValue)  > 0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 's':
                                break;
                        }
                        break;
                    case BN: // BN (Branch Negative)
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                if ((S.symbols[op1].iValue) < 0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 'f':
                                if ((S.symbols[op1].dValue)  < 0){
                                    programCounter = op3;
                                    continue;
                                }
                                break;
                            case 's':
                                break;
                        }
                        break;
                    case BR: // BR (Branch unconditional)
                        programCounter = op3;
                        continue;
                    case BINDR: // BINDR (Branch unconditional indirect)
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                programCounter = S.symbols[op3].iValue;
                                continue;
                            case 'f':
                                break;
                            case 's':
                                continue;
                        }
                        break;
                    case PRINT: // PRINT
                        switch(S.symbols[op1].type)
                        {
                            case 'i':
                                System.out.println(S.symbols[op1].symbol + " = " + S.symbols[op1].iValue);
                                break;
                            case 'f':
                                System.out.println(S.symbols[op1].symbol + " = " + S.symbols[op1].dValue);
                                break;
                            case 's':
                                System.out.println(S.symbols[op1].sValue);
                                break;
                        }
                        break;
                }
            }
            if(!stop)
                programCounter++;
            else
                writer.close();
        }
    }
}
