import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TidyNumSolver {


    public static void main(String args[]) {

        try {

            InputStream inputStream = new FileInputStream("./input/B-large.in");
            PrintStream printStream = new PrintStream("./output/tidyNum_out_3.txt");

            // inputStream = System.in;
            // printStream = System.out;

            Reader inputReader = new InputStreamReader(inputStream);
            String numTestsStr = "";
            int ch = inputReader.read();
            while(ch!='\n') {
                numTestsStr += (char)ch;
                ch = inputReader.read();
            }

            int numTests = Integer.parseInt(numTestsStr);

            TidyNumSolver solver = new TidyNumSolver();

            for (int t = 0; t < numTests; t++) {
                System.out.print("Case #" + (t + 1) + ": ");
                printStream.print("Case #" + (t + 1) + ": ");

                solver.printFloorTidyNum(inputReader, printStream);

                System.out.println();
                printStream.println();
                printStream.flush();
            }


            printStream.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Input does not have leading zeros
    private void printFloorTidyNum(Reader inputReader, PrintStream printStream) throws IOException {

        int ch = inputReader.read();
        double runLength = 0;
        int lastCh = -1;
        boolean inversionFound = false;

        while(ch != -1 ) {
            // System.out.println("char found: "+(char)(ch) + " "+ch);

            // LF and \r - return
            if(ch==10) {
                if(!inversionFound) {
                    char lastChar = (char) lastCh;
                    printCharNTimes(lastChar, runLength, printStream);
                }
                break;
            }

            if(inversionFound) {
                // System.out.print("9");
                printStream.print("9");
                ch = inputReader.read();
                continue;
            }

            // Inversion point not found

            // Same num
            if(ch == lastCh) {
                runLength++;
            }

            // Greater num
            else if(ch > lastCh) {
                // print old
                char lastChar = (char) lastCh;
                printCharNTimes(lastChar, runLength, printStream);

                // update
                lastCh = ch;
                runLength=1;
            }

            // Lesser num - inversion point - no leading zeros
            else {
                // print reduced char
                char reducedChar = (char)(lastCh-1);
                if(reducedChar!='0') {
                    // System.out.print(reducedChar);
                    printStream.print(reducedChar);
                }

                runLength--;
                // print 9 for last chars + current
                printCharNTimes('9', runLength+1, printStream);
                // update
                inversionFound = true;
            }

            ch = inputReader.read();
        }

    }

    private void printCharNTimes(char ch, double n, PrintStream printStream) {
        while(n > 0) {
            // System.out.print(ch);
            printStream.print(ch);
            n--;
        }
    }


}
