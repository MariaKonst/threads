import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.List;
public class Main {

    public static void main(String[] args) {
        PriorityBlockingQueue<Product> products= new PriorityBlockingQueue<>(100, new ProductCom());


            ProductThread t1 = new ProductThread("CKThread", "CK.txt", products);
            ProductThread t2 = new ProductThread("GuessThread", "guess.txt", products);
            ProductThread t3 = new ProductThread("TrussardiThread", "trussardi.txt", products);
            t1.start();
            t2.start();
            t3.start();
            try {
            t1.join();
            t2.join();
            t3.join();

        }catch (Exception exception){
            System.out.println("Thre was an error");
            System.exit(1);
        }

        ArrayList<Product> polledEllements =new ArrayList<>();
        products.drainTo(polledEllements);


        File file= new File("result.txt");
        if(file.exists()){

            System.out.println("File exists");
            System.exit(1);
        }

        PrintWriter output= null;
        try{
            output= new PrintWriter(file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        for(int i=0;i<10;i++){
         output.println(polledEllements.get(i)+ " ");
         // Iterator<Product> it=products.iterator();
         // while(it.hasNext()){
         //   output.println(it.next()+" ");
        }

        output.close();
    }

}