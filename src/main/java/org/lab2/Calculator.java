package org.lab2;

import java.util.ArrayList;
import java.util.List;

public class Calculator implements Runnable{
    private final Resource resource;
    private List<Long> divider;
    public Calculator(Resource resource){
        this.resource = resource;
        this.divider = new ArrayList<>();
    }

    private boolean isPrimeNumber(long n)
    {
        if(n < 2) return false;
        for(long i = 2;i<n;i++)
        {
            if(n%i==0) {
                divider.add(i);
                //System.out.println("First finded divide is " + i);
                //return false;
            }
        }
        if(divider.isEmpty()){
            return true;
        }
        return false;
    }
    @Override
    public void run() {
        String result;
        while(true)
        {
            System.out.println(Thread.currentThread().getId() + " - going to take resource");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try{
                long number = resource.take();
                System.out.println(Thread.currentThread().getId() + " take " + number);
                if(isPrimeNumber(number)){
                    result = number + " is a prime number";
                    System.out.println(result);
                    resource.appendResult(result, divider);
                    divider.clear();
                }else{
                    result = number + " is not a prime number";
                    System.out.println(result);
                    resource.appendResult(result, divider);
                    divider.clear();
                }
            }catch (InterruptedException ex){
                System.out.println(Thread.currentThread().getId() + " - iterrupted, bye bye");
                break;
            }
        }
    }
}
