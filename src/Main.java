import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/*
Brute Force algorithm (BF)
Deterministic Finite Automaton algorithm (DFA)
Karp-Rabin algorithm (KR)
Shift Or algorithm (SO)
Morris-Pratt algorithm (MP)
Knuth-Morris-Pratt algorithm (KMP)
Simon algorithm (SMN)
Colussi algorithm (CLS)
Galil-Giancarlo algorithm (GG)
Apostolico-Crochemore algorithm (AC)
Not So Naive algorithm (NSN)
Boyer-Moore algorithm (BM)
Turbo BM algorithm (TBM)
Apostolico-Giancarlo algorithm (AG)
Reverse Colussi algorithm (RC)
Horspool algorithm (HP)
Quick Search algorithm (QS)
Tuned Boyer-Moore algorithm (BMT)
Zhu-Takaoka algorithm (ZT)
Berry-Ravindran algorithm (BR)
Smith algorithm (SMT)
Raita algorithm (RT)
Reverse Factor algorithm (RF)
Turbo Reverse Factor algorithm (TRF)
Forward Dawg Matching algorithm (FDM)
Backward Nondeterministic Dawg Matching algorithm (BNDM)
Backward Oracle Matching algorithm (BOM)
Galil-Seiferas algorithm (GS)
Two Way algorithm (TW)
String Matching on Ordered Alphabets algorithm (SMOA)
Optimal Mismatch algorithm (OM)
Maximal Shift algorithm (MS)
Skip Search algorithm (SS)
KMP Skip Search algorithm (KPMSS)
*/

public class Main {

    public static final boolean GUI = true;
    public static final String[] algorithms =         {"BF", "BM", "BNDM", "HP", "KPMSS", "MS", "QS", "RT", "SMT", "SO", "SS"};
    public static final boolean[] enabledAlgorithms = {true, true, true, true, true, true, true, true, true, true, true};
    public static final byte numberOfAlgorithms = (byte) algorithms.length;
    public static final String[] operations = {"Character Comparisons", "Integer Comparisons", "Two Integer Operations", "Integer Bitshifts", "Assignments"};
    public static final byte numberOfDifferentOperations = 5;
    public static final String lastUsedPatternFile = "lastusedpattern.txt";
    public static final String lastUsedTextFile = "lastusedtext.txt";
    public static final long repeatUntil = 0L;

    public static void main(String[] args) {
        if (GUI) {
            SwingUI.run();
        } else {
            int minimumPatternLength;
            int maximumPatternLength;
            int textLength;
            int repetitions;
            String result;
            char[] alphabet;
            char[] pattern;
            char[] text;
            int highestCharacter;
            //abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.!?-:'
            //aáäbcčdďdzdžeéfghchiíjklĺľmnňoóôpqrŕsštťuúvwxyýzžAÁÄBCČDĎDZDŽEÉFGHCHIÍJKLĹĽMNŇOÓÔPQRŔSŠTŤUÚVWXYÝZŽ0123456789 ,.!?-:'
            /*
            System.out.println("Experiment 1:");
            text = Main.readFile("bible.txt");
            pattern = "God ".toCharArray();
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 0;
            result = countingOfAllOperations(minimumPatternLength,pattern , text, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 2:");
            text = Main.readFile("bible.txt");
            maximumPatternLength = 4;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = countingOfAllOperations(minimumPatternLength,pattern , text, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 3:");
            text = Main.readFile("bible.txt");
            pattern = ("Lord Jesus Christ be with you all. Amen.").toCharArray();
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = countingOfAllOperations(minimumPatternLength,pattern , text, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 4:");
            text = Main.readFile("bible.txt");
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = countingOfAllOperations(minimumPatternLength,pattern , text, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 5:");
            alphabet = "ab".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = countingOfAllOperations(minimumPatternLength,pattern , text, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 6:");
            alphabet = "abcd".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = countingOfAllOperations(minimumPatternLength,pattern , text, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 7:");
            alphabet = "abcdefgh".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = countingOfAllOperations(minimumPatternLength,pattern , text, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 8:");
            alphabet = "abcdefghijklmnop".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = countingOfAllOperations(minimumPatternLength,pattern , text, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 9:");
            alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.!?-:\n".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = countingOfAllOperations(minimumPatternLength,pattern , text, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 10:");
            alphabet = "aáäbcčdďdzdžeéfghchiíjklĺľmnňoóôpqrŕsštťuúvwxyýzžAÁÄBCČDĎDZDŽEÉFGHCHIÍJKLĹĽMNŇOÓÔPQRŔSŠTŤUÚVWXYÝZŽ0123456789 ,.!?-:\n".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = countingOfAllOperations(minimumPatternLength,pattern , text, highestCharacter);
            System.out.println(result);
            */

            repetitions = 1000;

            System.out.println("Experiment 1:");
            text = Main.readFile("bible.txt");
            pattern = "God ".toCharArray();
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 0;
            result = repeatedTimeMeasurement(minimumPatternLength, pattern, text, repetitions, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 2:");
            text = Main.readFile("bible.txt");
            maximumPatternLength = 4;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = repeatedTimeMeasurement(minimumPatternLength, pattern, text, repetitions, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 3:");
            text = Main.readFile("bible.txt");
            pattern = ("Lord Jesus Christ be with you all. Amen.").toCharArray();
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = repeatedTimeMeasurement(minimumPatternLength, pattern, text, repetitions, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 4:");
            text = Main.readFile("bible.txt");
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = repeatedTimeMeasurement(minimumPatternLength, pattern, text, repetitions, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 5:");
            alphabet = "ab".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = repeatedTimeMeasurement(minimumPatternLength, pattern, text, repetitions, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 6:");
            alphabet = "abcd".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = repeatedTimeMeasurement(minimumPatternLength, pattern, text, repetitions, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 7:");
            alphabet = "abcdefgh".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = repeatedTimeMeasurement(minimumPatternLength, pattern, text, repetitions, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 8:");
            alphabet = "abcdefghijklmnop".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = repeatedTimeMeasurement(minimumPatternLength, pattern, text, repetitions, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 9:");
            alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.!?-:\n".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = repeatedTimeMeasurement(minimumPatternLength, pattern, text, repetitions, highestCharacter);
            System.out.println(result);

            System.out.println("Experiment 10:");
            alphabet = "aáäbcčdďdzdžeéfghchiíjklĺľmnňoóôpqrŕsštťuúvwxyýzžAÁÄBCČDĎDZDŽEÉFGHCHIÍJKLĹĽMNŇOÓÔPQRŔSŠTŤUÚVWXYÝZŽ0123456789 ,.!?-:\n".toCharArray();
            textLength = 1000000;
            text = generateText(textLength, alphabet);
            maximumPatternLength = 20;
            pattern = generateText(maximumPatternLength, getAlphabet(text));
            highestCharacter = getHighestCharacter(concatenateArrays(getAlphabet(pattern), getAlphabet(text)));
            minimumPatternLength = 1;
            result = repeatedTimeMeasurement(minimumPatternLength, pattern, text, repetitions, highestCharacter);
            System.out.println(result);
        }
    }

    public static String countingOfAllOperations(int minimumLength, char[] pattern, char[] text, int highestCharacter) {
        String output = "Operations Count for:\n";
        output = output.concat("Alphabet:\n");
        output = output.concat(new String(getAlphabet(text)));
        output = output.concat("\n");
        output = output.concat(savePatterTextToFiles(pattern, text));
        output = output.concat("\n");

        for (int a = 0; a < numberOfAlgorithms; ++a) {
            output = output.concat("\t");
            output = output.concat(algorithms[a]);
        }
        output = output.concat("\n");
        if (minimumLength <= 0) {
            long result[][] = countOperations(pattern, text, highestCharacter);
            for (int operation = 0; operation < numberOfDifferentOperations; ++operation) {
                output = output.concat(operations[operation % numberOfDifferentOperations]);
                for (int algorithm = 0; algorithm < numberOfAlgorithms; ++algorithm) {
                    output = output.concat("\t" + result[operation][algorithm]);
                }
                output = output.concat("\n");
            }
            return output;
        } else {
            long result[][] = variousLengthPatternOperations(minimumLength, pattern, text, highestCharacter);
            for (int operation = 0; operation < numberOfDifferentOperations * pattern.length; ++operation) {
                output = output.concat((operation / numberOfDifferentOperations + 1) + "   ");
                output = output.concat(operations[operation % numberOfDifferentOperations]);
                for (int algorithm = 0; algorithm < numberOfAlgorithms; ++algorithm) {
                    output = output.concat("\t" + result[operation][algorithm]);
                }
                output = output.concat("\n");
            }
            return output;
        }
    }

    public static String repeatedTimeMeasurement(int minimumLength, char[] pattern, char[] text, int repetitions, int highestCharacter) {
        String output = "Run Times in Milliseconds for:\n";
        output = output.concat("Alphabet:\n");
        output = output.concat(new String(getAlphabet(text)));
        output = output.concat("\n");
        output = output.concat(savePatterTextToFiles(pattern, text));
        if (minimumLength <= 0 || minimumLength >= pattern.length) {
            long result[] = measureTime(pattern, text, highestCharacter);
            int repetition = 2;
            if (repeatUntil == 0L) {
                for (; repetition <= repetitions; ++repetition) {
                    long currentResult[] = measureTime(pattern, text, highestCharacter);
                    for (int algorithm = 0; algorithm < numberOfAlgorithms; ++algorithm) {
                        result[algorithm] += currentResult[algorithm];
                    }
                }
            } else {
                for (; System.currentTimeMillis() < repeatUntil; ++repetition) {
                    long currentResult[] = measureTime(pattern, text, highestCharacter);
                    for (int algorithm = 0; algorithm < numberOfAlgorithms; ++algorithm) {
                        result[algorithm] += currentResult[algorithm];
                    }
                }
            }

            output = output.concat("Repetitions:\n");
            output = output.concat((repetition - 1) + "\n");
            for (int a = 0; a < numberOfAlgorithms; ++a) {
                output = output.concat("\t");
                output = output.concat(algorithms[a]);
            }

            output = output.concat("\n");
            for (int algorithm = 0; algorithm < numberOfAlgorithms; ++algorithm) {
                output = output.concat("\t" + result[algorithm]);
            }
            return output;
        } else {
            long result[][] = variousLengthPatternTime(minimumLength, pattern, text, highestCharacter);
            int resultHeight = result.length;
            int repetition = 2;
            if (repeatUntil == 0L) {
                for (; repetition <= repetitions; ++repetition) {
                    long currentResult[][] = variousLengthPatternTime(minimumLength, pattern, text, highestCharacter);
                    for (int x = 0; x < resultHeight; ++x) {
                        for (int y = 0; y < numberOfAlgorithms; ++y) {
                            result[x][y] += currentResult[x][y];
                        }
                    }
                }
            } else {
                for (; System.currentTimeMillis() < repeatUntil; ++repetition) {
                    long currentResult[][] = variousLengthPatternTime(minimumLength, pattern, text, highestCharacter);
                    for (int x = 0; x < resultHeight; ++x) {
                        for (int y = 0; y < numberOfAlgorithms; ++y) {
                            result[x][y] += currentResult[x][y];
                        }
                    }
                }
            }

            output = output.concat("Repetitions:\n");
            output = output.concat((repetition - 1) + "\n");
            for (int a = 0; a < numberOfAlgorithms; ++a) {
                output = output.concat("\t");
                output = output.concat(algorithms[a]);
            }
            output = output.concat("\n");

            for (int x = 0; x < resultHeight; ++x) {
                output = output.concat("" + (x + 1));
                for (int y = 0; y < numberOfAlgorithms; ++y) {
                    output = output.concat("\t" + result[x][y]);
                }
                output = output.concat("\n");
            }
            return output;
        }
    }

    public static long[][] variousLengthPatternOperations(int minimumLength, char[] pattern, char[] text, int highestCharacter) {
        int outputX, x, maximumLength = pattern.length;
        long output[][] = new long[(maximumLength - minimumLength + 1) * numberOfDifferentOperations][numberOfAlgorithms];
        long countedOperations[][];
        char[] currentPattern;
        for (int length = minimumLength; length <= maximumLength; ++length) {
            currentPattern = new char[length];
            for (int a = 0; a < length; ++a) {
                currentPattern[a] = pattern[a];
            }
            countedOperations = countOperations(currentPattern, text, highestCharacter);
            outputX = (length - 1) * numberOfDifferentOperations;
            for (int operation = 0; operation < numberOfDifferentOperations; ++operation) {
                x = outputX + operation;
                for (int y = 0; y < numberOfAlgorithms; ++y) {
                    output[x][y] += countedOperations[operation][y];
                }
            }
        }
        return output;
    }

    public static long[][] variousLengthPatternTime(int minimumLength, char[] pattern, char[] text, int highestCharacter) {
        int outputX, maximumLength = pattern.length;
        long output[][] = new long[maximumLength - minimumLength + 1][numberOfAlgorithms];
        long measuredTimes[];
        char currentPattern[];
        for (int length = minimumLength; length <= maximumLength; ++length) {
            currentPattern = new char[length];
            for (int a = 0; a < length; ++a) {
                currentPattern[a] = pattern[a];
            }
            measuredTimes = measureTime(currentPattern, text, highestCharacter);
            outputX = length - 1;
            for (int y = 0; y < numberOfAlgorithms; ++y) {
                output[outputX][y] += measuredTimes[y];
            }
        }
        return output;
    }

    public static long[][] countOperations(char[] pattern, char[] text, int highestCharacter) {
        long output[][] = new long[numberOfDifferentOperations][numberOfAlgorithms];
        for (int operation = 0; operation < numberOfDifferentOperations; ++operation) {
            if (enabledAlgorithms[0]) {
                output[operation][0] = BF.countOperations(pattern, text).get(operation);
            }
            if (enabledAlgorithms[1]) {
                output[operation][1] = BM.countOperations(pattern, text, highestCharacter).get(operation);
            }
            if (enabledAlgorithms[2]) {
                output[operation][2] = BNDM.countOperations(pattern, text, highestCharacter).get(operation);
            }
            if (enabledAlgorithms[3]) {
                output[operation][3] = HP.countOperations(pattern, text, highestCharacter).get(operation);
            }
            if (enabledAlgorithms[4]) {
                output[operation][4] = KPMSS.countOperations(pattern, text, highestCharacter).get(operation);
            }
            if (enabledAlgorithms[5]) {
                output[operation][5] = MS.countOperations(pattern, text, highestCharacter).get(operation);
            }
            if (enabledAlgorithms[6]) {
                output[operation][6] = QS.countOperations(pattern, text, highestCharacter).get(operation);
            }
            if (enabledAlgorithms[7]) {
                output[operation][7] = RT.countOperations(pattern, text, highestCharacter).get(operation);
            }
            if (enabledAlgorithms[8]) {
                output[operation][8] = SMT.countOperations(pattern, text, highestCharacter).get(operation);
            }
            if (enabledAlgorithms[9]) {
                output[operation][9] = SO.countOperations(pattern, text, highestCharacter).get(operation);
            }
            if (enabledAlgorithms[10]) {
                output[operation][10] = SS.countOperations(pattern, text, highestCharacter).get(operation);
            }
        }
        return output;
    }

    public static long[] measureTime(char[] pattern, char[] text, int highestCharacter) {
        long output[] = new long[numberOfAlgorithms];
        if (enabledAlgorithms[0]) {
            output[0] = BF.measureTime(pattern, text).get(0);
        }
        if (enabledAlgorithms[1]) {
            output[1] = BM.measureTime(pattern, text, highestCharacter).get(0);
        }
        if (enabledAlgorithms[2]) {
            output[2] = BNDM.measureTime(pattern, text, highestCharacter).get(0);
        }
        if (enabledAlgorithms[3]) {
            output[3] = HP.measureTime(pattern, text, highestCharacter).get(0);
        }
        if (enabledAlgorithms[4]) {
            output[4] = KPMSS.measureTime(pattern, text, highestCharacter).get(0);
        }
        if (enabledAlgorithms[5]) {
            output[5] = MS.measureTime(pattern, text, highestCharacter).get(0);
        }
        if (enabledAlgorithms[6]) {
            output[6] = QS.measureTime(pattern, text, highestCharacter).get(0);
        }
        if (enabledAlgorithms[7]) {
            output[7] = RT.measureTime(pattern, text, highestCharacter).get(0);
        }
        if (enabledAlgorithms[8]) {
            output[8] = SMT.measureTime(pattern, text, highestCharacter).get(0);
        }
        if (enabledAlgorithms[9]) {
            output[9] = SO.measureTime(pattern, text, highestCharacter).get(0);
        }
        if (enabledAlgorithms[10]) {
            output[10] = SS.measureTime(pattern, text, highestCharacter).get(0);
        }
        return output;
    }

    public static char[] generateText(int length, char[] alphabet) {
        final int alphabetLength = alphabet.length;
        char text[] = new char[length];
        Random random = new Random(System.currentTimeMillis());
        for (int a = 0; a < length; ++a) {
            text[a] = alphabet[random.nextInt(alphabetLength)];
        }
        return text;
    }

    public static char[] readFile(String fileName) {
        try {
            File file = new File(fileName);
            int fileLength = (int) file.length();
            char output[] = new char[fileLength];
            FileReader reader = new FileReader(file);
            reader.read(output);
            return output;
        } catch (Exception e) {
            return null;
        }
    }

    public static int getHighestCharacter(char[] input) {
        int inputLenght = input.length;
        char highestChar = input[0];
        for (int a = 1; a < inputLenght; ++a) {
            if (input[a] > highestChar) {
                highestChar = input[a];
            }
        }
        return highestChar;
    }

    public static String savePatterTextToFiles(char[] patter, char[] text) {
        try {
            FileWriter fileWriter = new FileWriter(lastUsedPatternFile, false);
            fileWriter.write(new String(patter));
            fileWriter.close();
            fileWriter = new FileWriter(lastUsedTextFile, false);
            fileWriter.write(new String(text));
            fileWriter.close();
            return "Pattern:\n" + lastUsedPatternFile + "\nText:\n" + lastUsedTextFile + "\n";
        } catch (IOException e) {
            return "ERROR SAVING TO FILES";
        }
    }

    public static char[] concatenateArrays(char[] firstInput, char[] secondInput) {
        int firstLength = firstInput.length;
        int secondLength = secondInput.length;
        char[] output = new char[firstLength + secondLength];
        System.arraycopy(firstInput, 0, output, 0, firstLength);
        System.arraycopy(secondInput, 0, output, firstLength, secondLength);
        return output;
    }

    public static char[] getAlphabet(char[] text) {
        final int textLength = text.length;
        String differentLetters = "";
        boolean contains;
        for (int textLetter = 0; textLetter < textLength; ++textLetter) {
            contains = false;
            for (int alphabetLetter = 0; alphabetLetter < differentLetters.length(); ++alphabetLetter) {
                if (text[textLetter] == differentLetters.charAt(alphabetLetter)) {
                    contains = true;
                }
            }
            if (!contains) {
                differentLetters = differentLetters.concat("" + text[textLetter]);
            }
        }
        return differentLetters.toCharArray();
    }
}