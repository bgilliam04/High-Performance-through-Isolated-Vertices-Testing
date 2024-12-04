import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;

public class Main {

        public static void main(String[] args) throws FileNotFoundException {
                        System.out.println("Creating the graph file");

                        int numRuns = 300;

                        for (int i = 0; i < numRuns; i++) {
                                Shortcut sc = new Shortcut(64, 8, 2, 2, "../initialCode (4)/graph" + i + ".txt");
                                File file = new File("../initialCode (4)/graph" + i + ".txt");
                                try {
                                        FileWriter writer = new FileWriter(file);
                                        writer.write(sc.toString());
                                        writer.close();
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                             
                        }
//              NonRandom nr = new NonRandom(1024,28);                                     
//              System.out.println(nr);                                                    
//                                                                                         
//              while(sc.hasNextLine()) {                                                  
//                      String i=sc.nextLine();                                            
//                      System.out.println(i);                                             
//              }                                                                          
//              sc.close();                                                                
        }
}
