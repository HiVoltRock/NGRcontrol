package com.ngr.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class NGRcontrol extends Activity 
{

	LinkedList<String> commandQ = new LinkedList<String>();
	
    //Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    //clears the text for the "...Enter IP..." textView
    public void clearText(View view)
    {
    	//gets text box by ID
    	final EditText inputText = (EditText)findViewById(R.id.inputText);
    	inputText.setText("");
    }
    
    //handles main controls for car operation
    public void main()
    {    	
    	final String inputText = (String)findViewById(R.id.inputText).toString();
    	PrintWriter out = openConnection(inputText);
    	
    	if(out != null)
    	{
    		while(true)
    		{
    			if(!commandQ.isEmpty())
    			{
        			try 
        			{
    					Thread.sleep(20L);
    				} 
        			catch (InterruptedException e) 
        			{
        				AlertDialog alert = new AlertDialog.Builder(this).create();
    					alert.setTitle("Exception!");
    					alert.setMessage("It seems you have an Interrupted Exception. main()");
    					e.printStackTrace();
    				}
        			
        			out.println("Element in command queue: " + commandQ.poll());
        			out.flush();
        			try
        			{
        				Thread.sleep(20L);
        			}
        			catch(InterruptedException e)
        			{
        				AlertDialog alert = new AlertDialog.Builder(this).create();
        				alert.setTitle("Exception!");
    					alert.setMessage("It seems you have an Interrupted Exception. main()");
    					e.printStackTrace();
        			}
    			}		

    		}
    	}
    	else
    	{
    		AlertDialog alert = new AlertDialog.Builder(this).create();
    		alert.setTitle("Problem");
			alert.setMessage("Either the car IP is invalid, or car not connected");
    		System.exit(0);
    	}
    	
    }
    
	public PrintWriter openConnection(String RCIP) {
		Socket s;
		PrintWriter out = null;
		
		try 
		{
			s = new Socket(RCIP, 23);
			out = new PrintWriter(s.getOutputStream(), true);
        } 
		catch (UnknownHostException e) 
		{
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("Exception!");
			alert.setMessage("Cannot connect or cannot find host. openConnection()");
			e.printStackTrace();
            System.exit(1);
        } 
		catch (IOException e) 
		{
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("Exception!");
			alert.setMessage("Looks like you found an I/O Exception. openConnection()");
			e.printStackTrace();
		}
        return out;
    }
}










