import java.util.Scanner;

public class SymbolTable {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the following commands to access menu:");

        int col = 4;
        String table[][] = new String[100][col];
        int n=1;
        int sl = 1;
        int t=0;
        int loopCount = 0;
        table[0][0] = "SL";
        table[0][1] = "Name";
        table[0][2] = "Type";
        table[0][3] = "Value";
        while(true){
            System.out.println("1 - Insert data");
            System.out.println("2 - Remove (Use NAME to remove a row)");
            System.out.println("3 - Update ( Use NAME to access the row and update TYPE and VALUE)");
            System.out.println("4 - Display");
            System.out.println("5 - EXIT\n");

            int choice = scan.nextInt();

            while (choice < 1 || choice > 5) {
                System.out.println("Enter one of these valid options: \"1\", \"2\", \"3\", \"4\", \"5\" ");
                choice = scan.nextInt();
            }
            if (choice == 1){

                while (n!=0){
                    int c = 0;
                    int commaCount = 0;
                    table[sl][c] = String.valueOf(sl);
                    System.out.println("Enter your data: ");
                    Scanner sc = new Scanner(System.in);
                    String data = sc.nextLine();
                    data = data.replace(";"," ");

                    String[] dataSplit = data.split(" ");
                    String dataType = dataSplit[0];
                    table[sl][c+2] = dataType;
                    String data1 = data.replaceAll("\\s","");
                    String[] splitAfterType = data1.split(dataType);
                    //System.out.println("Split after datatype: "+splitAfterType[1]);
                    for (int i=0; i<data.length(); i++){
                        if (data.charAt(i) == ','){
                            commaCount++;                            // Comma count
                        }
                    }
                    if (commaCount == 0){
                        //System.out.println("comma count 0.................");
                        if (splitAfterType[1].contains("=")){
                            //System.out.println("======");
                            String[] splitData1 = splitAfterType[1].split("=");
                            String name = splitData1[0];
                            table[sl][c+1] = name;
                            //System.out.println("name: "+name);
                            String value = splitData1[1];
                            table[sl][c+3] = value;
                            //System.out.println("Value: "+value);
                            sl++;
                        }
                        else {
                            String name = splitAfterType[1];
                            table[sl][c+1] = name;
                            table[sl][c+3] = "NULL";
                            //System.out.println("name: "+name);
                            sl++;
                        }
                    }
                    else {
                        //System.out.println("comma count ++.................");
                        String[] sComma = splitAfterType[1].split(",");
                        for (int i=0; i<=commaCount; i++){
                            if (sComma[i].contains("=")){
                                //System.out.println("======");
                                String[] splitData2 = sComma[i].split("=");
                                String name = splitData2[0];
                                table[sl][c+1] = name;
                                //System.out.println("name: "+name);
                                String value = splitData2[1];
                                table[sl][c+3] = value;
                                table[sl][c+2] = dataType;
                                table[sl][0] = String.valueOf(sl);
                                sl++;
                                //loopCount++;
                                //System.out.println("Value: "+value);
                            }
                            else {
                                String name = sComma[i];
                                table[sl][0] = String.valueOf(sl);
                                table[sl][c+1] = name;
                                table[sl][c+2] = dataType;
                                table[sl][c+3] = "NULL";
                                sl++;
                                //loopCount++;
                            }
                            loopCount++;
                        }
                    }

                    System.out.println("\nYou want to insert more? \n1 - Continue \n0 - Stop taking input");
                    n = sc.nextInt();
                    loopCount++;
                }
            }

            else if (choice == 2){
                Scanner rem = new Scanner(System.in);
                if (loopCount > 0){
                    System.out.println("Enter a name to remove");
                    String remove = rem.nextLine();
                    int f=0;
                    for (int i=1; i<=loopCount; i++){
                        if (table[i][1].equals(remove)){
                            f++;
                            for (int j=1; j<col;j++){
                                table[i][j] = "NULL";
                            }
                        }
                    }
                    if (f==0){
                        System.out.println("Name does not match!!!");
                    }
                }
                else {
                    System.out.println("Table is empty!!!");
                }
            }

            else if (choice == 3){
                Scanner up = new Scanner(System.in);
                if (loopCount > 0){
                    System.out.println("Enter a name to update the TYPE and Value");
                    String update = up.nextLine();
                    int f=0;
                    for (int i=1; i<=loopCount;i++){
                        if (table[i][1].equals(update)){
                            f++;
                            System.out.println("Enter the new TYPE");
                            table[i][2] = up.nextLine();
                            System.out.println("Enter the new value");
                            table[i][3] = up.nextLine();
                        }
                    }
                    if (f==0){
                        System.out.println("Name does not match!!!");
                    }
                }
                else {
                    System.out.println("Table is empty!!!");
                }
            }

            else if (choice == 4){
                if (loopCount>0) {
                    for (int i = 0; i <= loopCount; i++) {
                        for (int j = 0; j < col; j++) {
                            if (table[i][j] == null) {
                                table[i][j] = " ";
                            } else {
                                System.out.print(table[i][j] + " ");
                            }
                        }
                        System.out.println();
                    }
                }
                else {
                    System.out.println("Table is empty!!!");
                }
            }

            else if (choice == 5){
                System.out.println("EXITING...");
                break;
            }

            System.out.println("\n-----------------ENTER A COMMAND AGAIN FOR FURTHER ACTIONS-----------------------\n");
        }
    }
}
