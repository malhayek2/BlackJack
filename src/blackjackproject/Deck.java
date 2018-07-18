/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackproject;

/**
 *
 * @author malhayek
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author malhayek
 */
public class Deck
{
   String  cardName;
    int cardNumber;

    
//    public static void main(String [] arags){
//        setCards();
//       
//        for (int i = 0 ; i < 10 ; i++){
//             int y = rand();
//        System.out.println(name(y) + " " + value(y) ); 
//    }
//        
//   
//        
//       
//        
//    }

   
   public Deck(int cardNum, String cardN)
   {
       cardName = cardN;
       cardNumber =  cardNum;
   }
   
   public  String getName()
   {
      return cardName;
   }
   
   public int getValue()
   {
      return cardNumber;
   }
   

   
   
}