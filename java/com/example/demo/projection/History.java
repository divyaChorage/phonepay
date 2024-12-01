package com.example.demo.projection;

import java.util.Date;

public interface History {
//if  u  want  to  select  data  from multiple  datable  and  accounfd to that wants to  sed  particular  data  to the  ui to
	//display the data in form of  then  user  projection
	//mainy used for  to  send  data  to  ui
	

	String getReceiverName();
	   Integer getAmount();            // Changed to Integer
	    Integer getSenderPreviousBal(); // Changed to Integer
	    Integer getSenderNowBal(); 
  String getCrDb();
  Date getDate();
  
     
     
}
