import java.io.FileOutputStream;
import java.io.FileWriter;

public class MyApp {
    public static void main(String[] args) {

       // FileWriter fout = null;
       FileOutputStream fout = null;
        
        byte[] temp = new byte[6];
        temp[0] = (byte)0x00;
        temp[1] = (byte)0xE0;
        temp[2] = (byte)0x81;
        temp[3] = (byte)0x7F;
        temp[4] = (byte)0x81;
        temp[5] = (byte)0x7F;
        try{
            //fin = new FileInputStream(inputFileName);
            //fout = new FileWriter("outputFile.txt");

            fout = new FileOutputStream("Test.txt");
            fout.write(temp); 

            if(fout != null) fout.close();

        }catch(Exception e){            
            

        }finally{
            
            
        }
    
        
    }
    

    
}
