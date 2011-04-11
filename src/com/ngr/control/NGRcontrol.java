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
    	String input = (String)findViewById(R.id.inputText).toString();
    	
    	Pattern ip = Pattern.compile("\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b");
    	Matcher m = ip.matcher(input);
    	
    	if(m.matches())
    	{
    		Socket telnet;
    		PrintWriter out = null;
    		try
    		{
    			telnet = new Socket(input, 23); //always port 23 for telnet protocol
    			out = new PrintWriter(telnet.getOutputStream(), true);  

    		}
    		catch(UnknownHostException uhe)
    		{
    			//Android lets you use all your var.method stuff at the same time instead of var.method1(); var.method2(); etc
    			new AlertDialog.Builder(this)
    				.setMessage("Cannot find the IP address. Try again")
    				.setTitle("Error")
    				.show();
    			
    			uhe.getStackTrace();
    		}
    		catch(IOException ioe)
    		{
    			//Android lets you use all your var.method stuff at the same time instead of var.method1(); var.method2(); etc
    			new AlertDialog.Builder(this)
    				.setMessage("I/O problem. Try looking again. NGRcontroller.connectTelnet() ")
    				.setTitle("Error")
    				.show();
    		}
    		
    		Intent control = new Intent(getApplicationContext(), Control.class);
    		startActivity(control);

    	}
    }
}








