/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcgame_ai;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Grzes
 */
public class CalcGame_AI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new CalcGame_AI();
    }

    public CalcGame_AI() {
        Start();

    }

    public void print(String pr) {
        System.out.println(pr);
    }
    private char[] charset;
    ArrayList permutations = new ArrayList();

    public void generate( int length, int maxNum) {
        
        int [] number = new int[length];
        for (int i = 0; i < number.length; i++) {
           number[i]=0;
            
        }
        maxNum+=1;
        while(number[0]!=maxNum){
            number[length-1]=number[length-1]+1;
            for(int k = 0; k<2; k++){
            for (int i = 1; i < number.length; i++) {
                int j = number[i];
                if(j==maxNum){
                  
                    number[i-1]=number[i-1]+1;
                    number[i]=0;
                    
                }
            }
            }
            if(number[0]!=maxNum){
                String out = "";
                for (int k = 0; k < number.length; k++) {
                    int l = number[k];
                    out=out+l;
                }
                if(!out.contains(""+maxNum)){
                System.out.println(out);
                permutations.add(out);
                }
            }else{
                break;
            }
                 
                
            
        }
//        if (length == 0) {
//            System.out.println(str);
//            String reverse = new StringBuilder(str).reverse().toString();
//             System.out.println(reverse);
//            permutations.add(str);
//            permutations.add(reverse);
//        } else {
//            for (int i = pos; i < charset.length; i++) {
//                generate(str + charset[i], i, length - 1);
//            }
//        }
    }

    private void Start() {
        print("MOVES:");
        Scanner in = new Scanner(System.in);
        int moves = in.nextInt();
        print("GOAL:");
        int goal = in.nextInt();
        print("START_NUM:");
        int startNum = in.nextInt();
        Scanner in2 = new Scanner(System.in);
        print("POSSIBILITIES: (x4, +2, -1 ...");
        String possibilities[] = in2.nextLine().split(", ");
        System.out.println(possibilities.length);
        String allNumbers = "";
//        for (int i = 0; i < possibilities.length; i++) {
//            allNumbers = allNumbers + "" + i;
//        }
//        charset = allNumbers.toCharArray();

        //test.generate("", 1);
        // Change 5 with the length of charset
        generate( moves, possibilities.length-1);
         int res = startNum;
        for (int i = 0; i < permutations.size(); i++) {
            String get = (String) permutations.get(i);
           
            String recipe = "";
            res = startNum;
            for (int j = 0; j < get.length(); j++) {
                
                int operationIndex = Integer.parseInt(get.charAt(j) + "");
                String operation = possibilities[operationIndex];
                System.out.println(operation);
                recipe = recipe + operation + ", ";
                if (operation.contains("x")) {
                    int basenum = Integer.parseInt(operation.split("x")[1] + "");
                    res = res * basenum;
                }
                if (operation.contains("/")) {
                    int basenum = Integer.parseInt(operation.split("/")[1] + "");
                    res = res / basenum;
                }
                if (operation.contains("cut")) {
                    if(res>9){
                       // System.out.println(res);
                    String x_str = Integer.toString(res);

                    int new_x = Integer.parseInt(x_str.substring(0, x_str.length()-1));

                    res=new_x;
                  // print(res+"");
                    }

                }
                if (operation.contains("+")) {
                    int basenum = Integer.parseInt(operation.split("\\+")[1]);
                    res = res + basenum;
                }
                if (operation.contains("-")) {
                    int basenum = Integer.parseInt(operation.split("\\-")[1]);
                    res = res - basenum;
                }
                 print(res+"");
            }
           
            if (res == goal) {
                System.out.println(recipe);
                break;
            }

        }
        if(res!=goal){
            print("No solution found.");
        }

    }
}
