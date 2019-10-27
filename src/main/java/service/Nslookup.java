package service;

import com.sun.javafx.PlatformUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 */
public class Nslookup {

    enum AddressType{
        IP,
        WWW
    }


    public String getAkamaiStaging(String url) throws Exception {

        String  akamai = performNslookup(url,AddressType.WWW);

        System.out.println(akamai);
      //  String akamai= output.toString().replace(".net","-staging.net");
      //  System.out.println(output.toString().replace(".net","-staging.net"));
        return akamai;
    }


    private String performNslookup(String source, AddressType addressType) throws IOException
    {   String result ="";

        ProcessBuilder processBuilder = new ProcessBuilder();

        if (PlatformUtil.isLinux()|| PlatformUtil.isMac()) {
            processBuilder.command("bash", "-c", "nslookup "+source +" | grep -A 1 Name:");
        }
        if (PlatformUtil.isWindows()) {
            processBuilder.command("cmd", "-c", "nslookup "+source +" | echo findstr Name:");
        }
        Process process = processBuilder.start();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        StringBuilder output = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            System.out.println(line.replaceAll("Name:",""));
            output.append(line);
            System.out.println(output);
            //output.append(line.replaceAll("Name:\\s","") + "\n");
        }
       return result;
    }
}