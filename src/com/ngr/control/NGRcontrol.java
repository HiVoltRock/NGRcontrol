package com.ngr.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class NGRcontrol extends Activity 
{

	
    /** Called when the activity is first created. */
	
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
    
    public void connectTelnet()
    {    	
    	final String inputText = (String)findViewById(R.id.inputText).toString();
    	
    	
    }
    
	public static PrintWriter openConnection(String RCIP) {
		Socket s;
		PrintWriter out = null;
		
		try 
		{
			s = new Socket(RCIP, 23);
			out = new PrintWriter(s.getOutputStream(), true);
        } 
		catch (UnknownHostException e) 
		{
            System.err.println("Cannot connect to or cannot find host.");
            System.exit(1);
        } 
		catch (IOException e) 
		{
			System.out.println();
			e.printStackTrace();
		}
        return out;
    }
}










