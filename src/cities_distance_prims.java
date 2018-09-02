
import java.util.Scanner;
import mylabpackage.Prims;
import org.apache.commons.io.IOUtils;

import java.net.URL;

import org.json.JSONObject;

public class cities_distance_prims {
     public static final int INFINITE = 999999999;
    public static void main(String args[]) throws Exception
    {int n;int adj[][];String url,inputLine;
     String cities[]={"","jaipur","hyderabad","banglore","chennai","ajmer"};String distance;
     JSONObject jsonRespRouteDistance;
     System.out.println("enter no of cities");
     Scanner sc=new Scanner(System.in);
     n=sc.nextInt();
     adj=new int[n+1][n+1];
     

     
      for(int i=1;i<=n;i++)
        { for(int j=i;j<=n;j++)
           {if(i==j)
            {adj[i][j]=INFINITE; }
           else
           {//https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=hyderabad+ON&destinations=chennai+ON&key=AIzaSyDOORO5NtuUNPmbdhR5JYRKT2TUFJnESz0
               url="https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+cities[i]+"+ON&destinations="+cities[j]+"+ON&key=AIzaSyDOORO5NtuUNPmbdhR5JYRKT2TUFJnESz0";
           inputLine= IOUtils.toString(new URL(url));
           jsonRespRouteDistance = new JSONObject(inputLine)
                                        .getJSONArray("rows")
                                        .getJSONObject(0)
                                        .getJSONArray ("elements")
                                        .getJSONObject(0)
                                        .getJSONObject("distance");
            distance = jsonRespRouteDistance.get("value").toString(); 
            System.out.println(distance);
            adj[i][j]=adj[j][i]=(int)Integer.parseInt(distance);
           }
           } 
        }//adj is ready;
           
      for(int i=1;i<=n;i++)
        for(int j=1;j<=n;j++)
              if(adj[i][j]==INFINITE)
                       System.out.println("distance from "+cities[i]+"to "+cities[j]+":"+0);
                else
              System.out.println("distance from "+cities[i]+"to "+cities[j]+":"+adj[i][j]);
    
            Prims prims = new Prims(n);
            prims.primsAlgorithm(adj);
            prims.printMST();
    
    }
    }
    

