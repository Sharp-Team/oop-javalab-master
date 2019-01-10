

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Manager {

    //one space by special characters
    public static String formatOneSpaceSpecial(String line, String character) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] strings = line.split("\\s*\\" + character + "\\s*");
        //appen every word and character special distance is one space
        for (String oneWord : strings) {
            stringBuffer.append(oneWord + character);
            stringBuffer.append(" ");
        }
        return stringBuffer.toString().trim().substring(0, stringBuffer.length() - 2);
    }

    //only one space between words and all character lowercase
    public static String formatOneSpace(String line) {
        line = line.toLowerCase();
        line = line.replaceAll("\\s+", " ");
        line = formatOneSpaceSpecial(line, ".");
        line = formatOneSpaceSpecial(line, ",");
        line = formatOneSpaceSpecial(line, ":");
        line = formatOneSpaceSpecial(line, "\"");
        return line.trim();
    }
    //first character of word after dot is in Uppercase and other words are in lower case.
    public static String afterDotUpperCase(String line) {
        StringBuffer stringBuffer = new StringBuffer(line);
        int lengthLine = stringBuffer.length();
        //check from first to last after . then UpperCase
        for (int i = 0; i < lengthLine - 2; i++) {
            if (stringBuffer.charAt(i) == '.') {
                char afterDot = stringBuffer.charAt(i + 2);
                stringBuffer.setCharAt(i + 2, Character.toUpperCase(afterDot));
            }
        }
        return stringBuffer.toString().trim();
    }

    //there are no spaces before and after sentence or word phrases in quotes (“”).
    static int countQuotes = 0;

    public static String noSpaceQuotes(String line) {
        StringBuffer stringBuffer = new StringBuffer(line);
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == '"' && countQuotes % 2 == 0) {
                if (stringBuffer.charAt(i+1) == ' ') {stringBuffer.deleteCharAt(i+1);}
                stringBuffer.insert(i, " ");
                countQuotes++;
                i++;
            } else if (stringBuffer.charAt(i) == '"' && countQuotes % 2 == 1
                    && i != 0) {
                if (stringBuffer.charAt(i-1) == ' ') {stringBuffer.deleteCharAt(i-1);}
//                stringBuffer.insert(i, " ");
                countQuotes++;
                i+=2;
            }
        }
        System.out.println(countQuotes);
        return stringBuffer.toString().trim();
    }

    //first character of word in first line is in Uppercase
    public static String firstUpercase(String line) {
        StringBuffer stringBuffer = new StringBuffer(line);
        for (int i = 0; i < line.length(); i++) {
            if (Character.isLetter(line.charAt(i))) {
                stringBuffer.setCharAt(i, Character.toUpperCase(line.charAt(i)));
                break;
            }
        }
        return stringBuffer.toString().trim();
    }

    //must have dot at the end of text.
    public static String lastAddDot(String line) {
        if (line.endsWith(".")) {
            return line;
        } else {
            return line + ".";
        }
    }

    //there are no blank line between lines
    public static boolean isLineEmpty(String line) {
        if (line.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //count line
    public static int countLine() {
        int countLine = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)));
                String line;
            //write until end file
            while ((line = br.readLine()) != null) {
                //check line empty
                if (isLineEmpty(line)) {
                    continue;
                }
                countLine++;
            }
            br.close();
            pw.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Can't found.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return countLine;
    }
}
