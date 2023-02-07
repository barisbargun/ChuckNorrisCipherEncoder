import java.util.ArrayList;

public class Progress extends ProgressTemplate {

    // convert degimal(67) to 7 bit binary code(1000011)
    public ArrayList<BinaryType> decimaltoBinary(char[] chars){
        binaries.clear();

        for (char ch: chars) {
            builder1.setLength(0);
            binaryNumber = ch;

            for (int i = 6; i >= 0 ; i--) {

                if (Math.pow(2, i) <= binaryNumber) {
                    builder1.append(1);
                    binaryNumber -= Math.pow(2, i);
                } else {
                    builder1.append(0);
                }

            }

            binaries.add(new BinaryType(ch, builder1.toString()));

        }
        return binaries;
    }

    // print 'C', 1000011
    public void printCharBinaries(ArrayList<BinaryType> binary) {
        for (BinaryType binaryType : binary) {
            System.out.printf("%c = %s\n", binaryType.ch, binaryType.binaryCode);
        }
    }

    // Convert binary(1000011) to encoded binary(0 0 00 0000 0 00)
    public String encodeBinaries(ArrayList<BinaryType> binary) {
        char lastItem = 'A';

        binaries = binary;
        builder1.setLength(0);
        builder2.setLength(0);

        for (BinaryType binaryType : binary) {

            for (char binaryCode : binaryType.binaryCode.toCharArray()) {
                if (binaryCode == lastItem) {

                    builder2.append("0");
                } else {

                    switch (binaryCode) {
                        case '1':
                            builder2.append(" 0 0");
                            break;
                        case '0':
                            builder2.append(" 00 0");
                    }

                }
                lastItem = binaryCode;
            }

        }
        return builder2.toString().trim();
    }

    // Convert encoded(0 0 00 0000 0 00) to binary(1000011) to char(C)
    public ArrayList<BinaryType> decodeBinaries(String encodedString) {
        binaries.clear();

        builder1.setLength(0);
        index = 7;

        int sameNext = -1;
        boolean isStarted = false;

        int bug = 0;

        for (char c : encodedString.trim().toCharArray()) {

            if (c != ' ' && c != '0') {
                return null;
            }

            if (c == ' ') {
                if (isStarted) {
                    sameNext = -1;
                    isStarted = false;
                } else {
                    isStarted = true;
                }
                bug++;
                if (bug == 2) return null;
            }

            if (c == '0') {
                bug = 0;
                if (isStarted) {
                    builder1.append(sameNext);
                    if (sameNext == 1) {
                        binaryNumber += Math.pow(2, index - 1);
                    }
                    index--;
                }

                else {
                    if (sameNext == 0)
                        return null;
                    if (sameNext == 1) {
                        sameNext = 0;
                    } else {
                        sameNext = 1;
                    }
                }

            }


            if (builder1.length() == 7) {
                binaries.add(new BinaryType((char)binaryNumber,builder1.toString()));
                builder1.setLength(0);

                index = 7;
                binaryNumber = 0;
            }
        }
        if (builder1.length() != 0 || binaries.size() == 0) {
            return null;
        }

        return binaries;
    }

    public void printDecodeBinaries(ArrayList<BinaryType> decodedBinary) {
        for (BinaryType b : decodedBinary){
            System.out.print(b.ch);
        }
        System.out.println();
    }
}
