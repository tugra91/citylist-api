package com.kuehnenagel.citylist.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataUtil {

    private static final String COMMADELIMETER =  ",";

    public static List<List<String>> retrieveDataFromCsvToList(File csvFile) {
        List<List<String>> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            while((line = br.readLine()) != null) {
                String[] values = line.split(COMMADELIMETER);
                result.add(Arrays.asList(values));
            }
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
