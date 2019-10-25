import com.sun.javafx.PlatformUtil;
import org.xbill.DNS.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class akamai {

    public static void main(String args[]) throws Exception {
        {
            ProcessBuilder processBuilder = new ProcessBuilder();

            if (PlatformUtil.isLinux()|| PlatformUtil.isMac()) {
                processBuilder.command("bash", "-c", "nslookup www.ringcentral.com | grep Name:");
            }
            if (PlatformUtil.isWindows()) {
                processBuilder.command("cmd", "-c", "nslookup www.ringcentral.com | echo findstr Name:");
            }
            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            StringBuilder output = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                System.out.println(line.replaceAll("Name:",""));

             output.append(line.replaceAll("Name:\\s","") + "\n");
            }
            System.out.println(output);
            String akamai= output.toString().replace(".net","-staging.net");
            System.out.println(output.toString().replace(".net","-staging.net"));

        }
    }


    }
    }
