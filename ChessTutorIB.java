/*
 * Name:     Iuliia Buniak
 *
 * Course:   CS-12, Fall 2018
 *
 * Date:     11/04/18
 *
 * Filename: ChessTutorIB.java
 *
 * Purpose:  A program which counts daily earnings, accumulated earnings, 
 *           daily earnings history, and defines when millionaire and billionare
 *           status will be reached while teaching chess (1-64)  
 */

public class ChessTutorIB {

    public static void main (String [] arg){
    
        // Data declaration
        char input;
        String menu = "\n--------------------------------\n" +
                      "Chess Tutoring Income Options\n" +
                      "--------------------------------\n" +
                      "Daily earnings on Day N          [D]\n" +
                      "Total earnings up to Day N       [T]\n" + 
                      "History of earnings up to Day N  [H]\n" + 
                      "Quit                             [Q]\n";
        // priming read
        System.out.print(menu);
        input = UtilsIB.readChar("Enter option: ", false);
        // loop will work until user press Q or q option 
        while ((input !='q') && (input !='Q')){
            // depending on user's pick different methods will be called
             
            switch(input) {
            
                // checking daily earnings
                case 'D':
                case 'd':
                    showDailyEarnings();
                    break;
                
                // checking total earnings
                case 'T':
                case 't':
                    showTotalEarnings();
                    break;
                
                // checking history of earnings
                case 'H':
                case 'h':
                    showHistoryEarnings();
                    break;
                    
                // program's reply if user enters something except "D/d", "T/t", "H/h"          
                default:
                    System.out.println ("Unrecognized option " + input + ", please try again\n");
                    break;
        
            }//end switch
            
            // update read
            System.out.print(menu);
            input = UtilsIB.readChar ("Enter option: ", false);
 
        }//end while
        
        // termination
        System.out.println("Exit upon user request");

    } //end main

    //method for displaying daily earnings on day N
    static void showDailyEarnings (){
        int day;
        double payment, paymentDisplay;
        day = getValidDay();
        
        payment = Math.pow(2,day - 1);
        paymentDisplay = payment / 100;
        
        System.out.printf("\n==> Daily earnings on Day %d will be: $%,-30.2f\n", day, paymentDisplay);
        System.out.println();
    }//end showDailyEarnings
        
    //method for calculating total thru day N
    static void showTotalEarnings(){
        int day;
        double payment, totalDisplay;
        double total = 0;
        
        day = getValidDay();
        
        for (int i=1; i <= day; i++) {
            payment = Math.pow(2, i - 1);
            total += payment;
        }
        totalDisplay = (double)total / 100;
        
        System.out.printf("\n==> Total earnings thru Day %d will be: $%,-30.2f\n", day, totalDisplay);
        System.out.println(); 
    }//end showTotalEarnings 
        
    //method for displaying daily history of earnings and total earnings thru Day N
    static void showHistoryEarnings(){
        int day;
        double total = 0;
        double payment, paymentDisplay, totalDisplay;
        final int MILLION = 1000000;
        final int BILLION = 1000000000;
        boolean firstMillion = true;
        boolean firstBillion = true;
        
        day = getValidDay();
        System.out.printf("\n==> Total earnings history thru Day %d\n", day);
        System.out.printf("%3s%30s%30s\n", "Day", "Daily earnings [$]", "Total earnings [$]");
        
        for (int i=1; i <= day; i++) {
            payment = Math.pow(2, i - 1);
            total += payment;
            paymentDisplay = (double)payment / 100.0;
            totalDisplay = (double) total / 100.0;
            System.out.printf("%3d%,30.2f%,30.2f", i, paymentDisplay,totalDisplay); 
            
            //pointing out the first million
            if ((totalDisplay > MILLION ) && (firstMillion)){
                System.out.printf(" <== DAY %d: I'M A MILLIONAIRE!!!", i);
                firstMillion = false;
            }
            //pointiong out the first billion
            else if ((totalDisplay > BILLION ) && (firstBillion)){
                System.out.printf (" <== DAY %d: I'M A BILLIONAIRE!!!", i);
                firstBillion = false;
            }
            else {
            }
            System.out.println();
        }//end for
    }//end showHistoryEarnings
        
    //method for prompting for a valid day number in the range 1-64
    static int getValidDay(){
        int day;
        final int MAX = 64;
        
        day = UtilsIB.readInt("Enter chess teaching day [1-64] > ", false);
        // checking if the input is within the boundaries (1-64)
        while ((day < 1 ) || (day > MAX)){
            System.out.println("ERROR: day must be 1-64, please re-enter");
            day = UtilsIB.readInt("Enter chess teaching day [1-64] > ", false);
        } 
    return day; 
    }//end getValidDay
                 
} // end class 