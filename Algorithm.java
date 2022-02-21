import java.util.ArrayList;
import java.util.List;


class Boundary{
    int i,j;
    float sum;

    Boundary(int i, int j, float sum){
        this.i = i;
        this.j = j;
        this.sum = sum;
    }

}

public class Algorithm {

    
    public static void main(String[] args) {

        //args = new String[] {"0", "0", "1.5", "-3", "0", "6", "7"};
        int i, j , k;
        float sum, tempsum;
        Boundary  temp = null;        
        int argsLen = args.length; 
        float[] data = new float[argsLen];       
        //List<Boundary> preprocessL = new ArrayList<Boundary>();

        if(argsLen < 1){
            System.out.println("Need args!");
            return;
        }

        for(int m = 0; m<argsLen; m++){
        try{
            data[m] = Float.parseFloat(args[m]);

        }catch(NumberFormatException nfe){
            System.out.println("The arg should be numbers!");
            return;
        }  
        }

        i = 0; j = 0; sum = data[0];
        temp = new Boundary(i,j,sum);

        while(true){
        makeRBoundary(data, temp);
        makeLBoundary(data, temp);

        if(temp.sum > sum){
            i = temp.i;
            j = temp.j;
            sum = temp.sum;
        }else if(temp.sum == sum){
            if((j-i)>(temp.j -temp.i)){
                i = temp.i;
                j = temp.j;
            }
        }
         
        if(temp.j<argsLen-1){
            temp.i = temp.j+1;
            temp.j = temp.i;
            temp.sum = data[temp.i];

        }else{
            break;
        }
    }

    System.out.println("i =" + (i+1));
    System.out.println("j =" + (j+1));



        /*try{
            preprocess(args,preprocessL);

        }catch(NumberFormatException nfe){
            System.out.println("The arg should be numbers!");
            return;
        }  

        temp = new Boundary(k, k, preprocessL.get(k).sum);
        makeRBoundary(preprocessL, temp);*/

        
    }

    /*static void preprocess(String[] args, List<Boundary> preprocessL) throws NumberFormatException{
        int i = 0,j = 0,k = 0;
        float temp,sum;
        

        try{
            temp = Float.parseFloat(args[0]);
            sum = temp;
            
            while(k<args.length-1){
                k++;
                temp = Float.parseFloat(args[k]);
                if(((sum==0)&&(temp==0))
                ||((sum>0)&&(temp>0))
                ||((sum<0)&&(temp<0))) {
                    j = k;
                    sum += temp;
                    continue;
                }                
                if(((sum==0)&&(temp != 0))
                ||((sum>0)&&(temp<=0))
                ||((sum<0)&&(temp>=0))){
                    preprocessL.add(new Boundary(i,j,sum));
                    i = k; j = k; sum = temp;
                    continue;
                }
            }

            preprocessL.add(new Boundary(i,j,sum));

        }catch(NumberFormatException nfe){
            throw nfe;
        }

    }*/

    static void makeRBoundary( float[] data, Boundary temp){
        int j = temp.j;
        float sum = 0;
        // = preprocessL.get(i).sum;

        while(j< data.length-1){
            j++;
            sum += data[j];
            if(sum >0){
                temp.j = j;
                temp.sum += sum;
                sum = 0; 
            }

        }
    }

    static void makeLBoundary( float[] data, Boundary temp){
        int i = temp.i;        
        float sum = 0;
        
        // = preprocessL.get(i).sum;

        while(i< temp.j){
            
            sum += data[i];
            if(sum <=0){
                temp.i = i+1;
                temp.sum -= sum;
                sum = 0; 
            }
            i++;

        }
    }
}
