import java.io.FileInputStream;
import java.io.FileWriter;

public class DNASeqConversion {

    public static void main(String[] args) {
        
        int pieceLength;
        
        if(args.length != 2){
            System.out.println("Need 2 args!");
            System.out.println("Example: java DNASeqConversion \"filename\" 2");
            return;
        }

        try{
            pieceLength = Integer.parseInt(args[1]);
        }catch(NumberFormatException nfe){
            System.out.println("The second arg should be an integer!");
            System.out.println("Example: java DNASeqConversion \"filename\" 2");
            return;
        }       
        
        try{
            convertSeq(args[0],pieceLength); 
        }catch(Exception e){
            System.out.println("Conversion failed!");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;

        }
        
        System.out.println("Finished! Check outputFile.txt!");
        
    }

    static void convertSeq(String inputFileName, int pieceLength) throws Exception
    {
        FileInputStream fin = null;
        FileWriter fout = null;
        int pieceNum = 0;
        byte[] tempPiece;
        int i;
        StringBuilder encode = new StringBuilder();
        StringBuilder quality = new StringBuilder();
        

        if(pieceLength < 1){
            throw new IllegalArgumentException("The Length of the piece should be greater than 0!");
            
        }

        tempPiece = new byte[pieceLength];

        try{
            fin = new FileInputStream(inputFileName);
            fout = new FileWriter("outputFile.txt");

            i = fin.read(tempPiece);

            while(i != -1){
                encode.setLength(0);
                quality.setLength(0);

                for(int j = 0;j<i;j++){
                    switch ((byte)(tempPiece[j] & 0xC0)){
                        case (byte)0x00:
                            encode.append("A");
                            break;
                        case (byte)0x40:
                            encode.append("C");
                            break;
                        case (byte)0x80:
                            encode.append("G");
                            break;
                        case (byte)0xC0:
                            encode.append("T");
                            break;

                    }

                    quality.append((char)((tempPiece[j] & 0x3F) + 33));                    

                }
                
                pieceNum++;
                fout.write("@READ_"+pieceNum+"\n");
                fout.write(encode.toString()+"\n");
                fout.write("+READ_"+pieceNum+"\n");
                fout.write(quality.toString()+"\n");
                //fout.flush();
                
                if(i<pieceLength)
                throw new IllegalArgumentException("Only"+i+" bytes in the last piece!");

                i = fin.read(tempPiece);
            }


        }catch(Exception e){            
            throw e;

        }finally{
            if(fin != null) fin.close();
            if(fout != null) fout.close();
        }

    }




}