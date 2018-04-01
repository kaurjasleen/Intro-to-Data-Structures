
import java.io.*;
import java.util.Scanner;

public class Simulation{

   public static Job getJob(Scanner in) {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }

   public static void main(String[] args) throws IOException{
      Job j = null;
      Queue storage = new Queue();
      Queue backup_s = new Queue();
      Queue completed_s = new Queue();
      //Queue[] arr = null;
      int time = 0;

      if(args.length != 1){
         System.out.println("Usage: Simulation infile");
         System.exit(1);
      }

      Scanner in = new Scanner(new File(args[0]));
      PrintWriter report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
      PrintWriter trace = new PrintWriter(new FileWriter(args[0] + ".trc"));

      int m = Integer.parseInt(in.nextLine());

      while(in.hasNextLine()){
         storage.enqueue(getJob(in));
      }

      report.println("Report file: " + (args[0] + ".rpt"));
      report.println(m + " Jobs:");
      report.println(storage.toString()+"\n");
      report.println("***********************************************************");

      trace.println("Trace file: " + (args[0] + ".trc"));
      trace.println(m + " Jobs:");
      trace.println(storage.toString()+"\n");


      for(int n = 1; n < m; n++){
         int processors = n;
         int totalWait = 0;
         int maxWait = 0;
         double averageWait = 0.00;
         Queue[] arr = new Queue[n+2];
         arr[0] = storage;

         for(int i = 1; i<storage.length()+1; i++){
            j = (Job)storage.dequeue();
            j.resetFinishTime();
            backup_s.enqueue(j);
            storage.enqueue(j);
         }

         arr = new Queue[n+2];
         arr[0] = backup_s;
         arr[n+1] = completed_s;
         for(int i = 0; i <= n; i++){
            arr[i] = new Queue();
         }

         trace.println("*****************************");
         if(processors == 1){
            trace.println(n + " processor:");
         } else {
            trace.println(n + " processors:");
         }
         trace.println("*****************************");
         trace.println("time=" + time);
         trace.println("0: " + storage.toString());
         for(int i = 1; i < processors+1; i++){
               trace.println(i + ": " + arr[i]);
         }

         while(completed_s.length() != m){
            int cF = Integer.MAX_VALUE;
            int fin_ind = 1;
            int comp = -1;
            int len = -1;
            int fin_len = -1;
            Job c = null;

            if(!backup_s.isEmpty()){
               c = (Job)backup_s.peek();
               cF = c.getArrival();
               fin_ind = 0;
            }

            for(int i = 1; i < processors+1; i++){
               if(arr[i].length() != 0){
                  c = (Job)arr[i].peek();
                  comp = c.getFinish();
               }
               if(comp == -1){
               }else if(comp < cF){
                  cF = comp;
                  fin_ind = i;
               }
               time = cF;
               }

               if(fin_ind == 0){
                  int tempIndex = 1;
                  fin_len = arr[tempIndex].length();
                  for(int i = 1; i < processors+1; i++){
                     len = arr[i].length();
                     if(len < fin_len){
                        fin_len = len;
                        tempIndex = i;
                     }
                   }

                  c = (Job)backup_s.dequeue();
                  arr[tempIndex].enqueue(c);
                  if(arr[tempIndex].length()==1){
                     c = (Job)arr[tempIndex].peek();
                     c.computeFinishTime(time);
                  }
               }else{
                  c = (Job)arr[fin_ind].dequeue();
                  completed_s.enqueue(c);
                  int tempWait = c.getWaitTime();
                  if(tempWait > maxWait){
                     maxWait = tempWait;
                  }
                  totalWait += tempWait;

                  if(arr[fin_ind].length() >= 1){
                     c = (Job)arr[fin_ind].peek();
                     c.computeFinishTime(time);
                  }
               }

               trace.println();
               trace.println("time=" + time);
               trace.println("0: " + completed_s.toString()+backup_s.toString());
               for(int i = 1; i < processors+1; i++){
                  trace.println(i + ": " + arr[i]);
               }
            }
            averageWait = ((double)totalWait/m);
            averageWait = (double)Math.round(averageWait*100)/100;
            trace.println();

            if(n == 1) {
               report.println(processors + " processor: totalWait="+totalWait+ ", maxWait="+maxWait+", averageWait="+String.format("%.2f", averageWait));
            } else {
               report.println(processors + " processors: totalWait="+totalWait+ ", maxWait="+maxWait+", averageWait="+String.format("%.2f", averageWait));
            }

            time = 0;
            completed_s.dequeueAll();
      }

      in.close();
      report.close();
      trace.close();
   } 
}
