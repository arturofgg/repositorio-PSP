package proceso1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class proceso1 {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("ls", "-l");
        Process p = pb.start();
        try {
            InputStream is = p.getInputStream();
            InputStream er = p.getErrorStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(er));
            String liner = null;
            while ((liner = buffer.readLine()) != null) {
                System.out.println("ERROR > " + liner);
            }
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}