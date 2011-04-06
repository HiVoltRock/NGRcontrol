package com.ngr.control;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
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
    	
    	String input = (String)findViewById(R.id.inputText).toString();
    	
    	Pattern ip = Pattern.compile("\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b");
    	Matcher m = ip.matcher(input);
    	
    	if(m.matches())
    	{
    		try
    		{
    			Socket telnet = new Socket(input, 23);
    			
    		}
    		catch(UnknownHostException uhe)
    		{
    			//TODO:catch this code
    		}
    		catch(IOException ioe)
    		{
    			//TODO: catch this code
    		}
    	}
    }
}








