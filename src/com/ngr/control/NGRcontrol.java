package com.ngr.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ViewSwitcher;

public class NGRcontrol extends Activity 
{

	LinkedList<String> commandQ = new LinkedList<String>();
	private ViewSwitcher switcher;
	PrintWriter out;
	
    //Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        
    }
    
    //clears the text for the "...Enter IP..." textView
    public void clearText(View view)
    {
    	//gets text box by ID
    	final EditText inputText = (EditText)findViewById(R.id.inputText);
    	inputText.setText("");
    }
    
    //handles main controls for car operation
    public void main(View view)
    {    	
    	final String inputText = (String)findViewById(R.id.inputText).toString();
    	out = openConnection(inputText);
    	
    	switcher.showNext();
    	
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
    					System.out.println("It seems you have an Interrupted Exception. main()");
    					e.printStackTrace();
    				}
        			
        			out.println(commandQ.poll());
        			out.flush();
        			try
        			{
        				Thread.sleep(20L);
        			}
        			catch(InterruptedException e)
        			{
    					System.out.println("It seems you have an Interrupted Exception. main()");
    					e.printStackTrace();
        			}
    			}		

    		}
    	}
    	else
    	{
			System.out.println("Either the car IP is invalid, or car not connected");
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
			System.out.println("Cannot connect or cannot find host. openConnection()");
			e.printStackTrace();
            //System.exit(1);
        } 
		catch (IOException e) 
		{
			System.out.println("Looks like you found an I/O Exception. openConnection()");
			e.printStackTrace();
		}
		
		System.out.println("Successful socket return in openConnection()");
        return out;
    }
	
	public void forward(View view)
	{
		System.out.println("Pressed Forward");
		commandQ.add("f\r");
	}
	
	public void backward(View view)
	{
		System.out.println("Pressed Backward");
		commandQ.add("b\r");
	}
	
	public void left(View view)
	{
		System.out.println("Pressed left");
		commandQ.add("l\r");
	}
	
	public void right(View view)
	{
		System.out.println("Pressed right");
		commandQ.add("r\r");
	}
}










