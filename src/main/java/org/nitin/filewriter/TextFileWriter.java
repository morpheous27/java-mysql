package org.nitin.filewriter;

import org.nitin.util.DateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
public class TextFileWriter<T> {

    @Value("${filename.prefix}")
    private String filenamePrefix;

    @Value("${path.prefix}")
    private String pathPrefix;

    public String writeDataUsingFiles(List<T> data) {
        boolean isSuccess = false;
        String fileToWrite = pathPrefix+getFileName();


        try (FileWriter fw = new FileWriter(fileToWrite, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            data.stream().forEach(i -> out.println(i));
            isSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return fileToWrite;
    }

    private String getFileName() {
        return filenamePrefix + DateUtil.getFromattedCurrDate() + ".txt";
    }
}
