import java.util.*;

public class Main {

    private static final Progress progress = new Progress();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<BinaryType> binaries = new ArrayList<>();

        String builder1;
        String userChoice;

        PROGRAM:
        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            userChoice = scanner.nextLine();

            String decodedBinaries;

            switch (userChoice) {

                case "encode":
                    System.out.println("Input string:");
                    binaries = progress.decimaltoBinary(scanner.nextLine().toCharArray());
                    System.out.println("Encoded string:");
                    System.out.println(progress.encodeBinaries(binaries));
                    break;

                case "decode":
                    System.out.println("Input encoded string:");
                    try {
                        binaries = progress.decodeBinaries(scanner.nextLine());
                        if (binaries == null) {
                            throw new NullPointerException();
                        }

                        System.out.println("Decoded string:");
                        progress.printDecodeBinaries(binaries);
                    } catch (NullPointerException e) {
                        System.out.println("Encoded string is not valid.");
                    }
                    break;

                case "exit":
                    System.out.println("Bye!");
                    break PROGRAM;

                default:
                    System.out.printf("There is no '%s' operation\n",userChoice);
            }

            System.out.println();
        }


    }
}