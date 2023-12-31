package com.tut;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */

public class App 
{
    public static String getData(String c)throws Exception{
        StringBuffer br=new StringBuffer();
        br.append("<html>");

         String url="https://www.worldometers.info/coronavirus/country/"+c+"/";
         Document doc=Jsoup.connect(url).get();
         Elements elements=doc.select("#maincounter-wrap");
         elements.forEach((e)->{
           String text=e.select("h1").text();
           String count=e.select(".maincounter-number>span").text();
           br.append(text).append(":").append(count).append("<br>");


        });
         br.append("</html>");

         return br.toString();
    }
    public static void main( String[] args )throws Exception
    {
//        System.out.println( "Hello World!" );
//        Scanner sc=new Scanner(System.in);
//        System.out.println("Enter country:");
//        String co=sc.nextLine();
//        System.out.println(getData(co));
         JFrame root=new  JFrame("Details of Country");
         root.setSize(500,500);
         Font f=new Font("Poppins",Font.BOLD,30);
         JTextField field= new JTextField();
         JLabel dataL=new JLabel();
         field.setFont(f);
         dataL.setFont(f);
         field.setHorizontalAlignment(SwingConstants.CENTER);
         dataL.setHorizontalAlignment(SwingConstants.CENTER);
         JButton button=new JButton("Get");
         button.addActionListener(event->{
             //click;
             try{
                 String maindata=field.getText();
                 String result=getData(maindata);
                 dataL.setText(result);

             } catch(Exception e){
                 e.printStackTrace();
             }



         });
         button.setFont(f);
         root.setLayout(new BorderLayout());

        root.add(field,BorderLayout.NORTH);
        root.add(dataL,BorderLayout.CENTER);
        root.add(button,BorderLayout.SOUTH);
        root.setVisible(true);


    }
}
