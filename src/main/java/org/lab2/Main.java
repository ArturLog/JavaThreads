package org.lab2;
import java.io.File;
import java.util.Scanner;

import static java.lang.Long.parseLong;

/**
 * Main program
 * 2147483647 - largest prime 32-bit
 */
public class Main
{
    static final int AMOUNT_OF_THREADS = 2;
    /**
     * Main method
     *
     */
    public static void main( String[] args ) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        Resource resource = new Resource();
        Thread[] threads = new Thread[AMOUNT_OF_THREADS];
        long  number = 0;
        for(int i = 0;i<AMOUNT_OF_THREADS;i++)
        {
            threads[i] = new Thread(new Calculator(resource));
            threads[i].start();
        }
        Thread.sleep(2000);
        // Modyfikacja
        // odczyt           ```````````````````````````````````````````````````````````
        try{
            Scanner fileScan = new Scanner(new File("C:\\Users\\Artur\\Desktop\\4sem\\pt\\java\\lab2\\lab2\\src\\main\\resources\\liczby.txt"));
            while (fileScan.hasNextLine()) {
                number = parseLong(fileScan.nextLine());
                resource.put(number);
            }
            fileScan.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //
        while(true)
        {
            number = scan.nextLong();
            if(number == 1){
                resource.printResult();
            }else if(number != 0){
                resource.put(number);
            }
            else{
                for(int i = 0;i<AMOUNT_OF_THREADS;i++) {
                    threads[i].interrupt();
                }
                break;
            }
        }
    }
}
