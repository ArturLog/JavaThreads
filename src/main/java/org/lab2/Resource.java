package org.lab2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Resource {
    private List<Long> values;
    private final List<String> synchroResult;

    public Resource(){
        List<String> results = new ArrayList<String>();
        synchroResult = Collections.synchronizedList(results);
        values = new ArrayList<>();
    }

    public synchronized void appendResult(String result, List<Long> divider)
    {
        synchroResult.add(result);
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Artur\\Desktop\\4sem\\pt\\java\\lab2\\lab2\\src\\main\\resources\\result.txt", true));
            writer.append('\n');
            writer.append(result);
            writer.append(" : ");
            for(Long divide : divider)
            {
                writer.append(divide.toString() + ", ");
            }
            writer.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public synchronized void printResult()
    {
        System.out.println("Result of synchonized list:");
        for(String result : synchroResult)
        {
            System.out.println(result);
        }
    }
    public synchronized long take() throws InterruptedException{
        while (values.isEmpty()){
            wait(); // waiting for value
        }
        long ret = values.get(0);
        values.remove(0);
        return ret;
    }

    public synchronized void put(long value){
        this.values.add(value);
        notifyAll();
    }
}
