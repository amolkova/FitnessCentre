import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class FileHandler {
    
    public FileHandler() {
    }
    
    public LinkedList<Member> readFile() {
        LinkedList<Member> m = new LinkedList();
        String lineRead;
        String[] splitLine = null;
        Member mem;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))) {
            lineRead = reader.readLine();
            while (lineRead != null) {
                splitLine = lineRead.split(", ");
                
                if (splitLine[0].equals("S")) {
                    
                    mem = new SingleClubMember('S', Integer.parseInt(splitLine[1]), splitLine[2],
                        Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                } else {
                    mem = new MultiClubMember('M', Integer.parseInt(splitLine[1]), splitLine[2],
                        Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                }
                
                m.add(mem);
                lineRead = reader.readLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return m;
    }
    
    public void appendFile(String mem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv", true))) {
            writer.write(mem + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void overWriteFile(LinkedList<Member> m) {
        String s;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.temp", false))) {
            for (int i = 0; i < m.size(); i++) {
                s = m.get(i).toString();
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            File f = new File("members.csv");
            File tf = new File("members.temp");
            f.delete();
            tf.renameTo(f);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
