package com.ngr.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Control extends Activity
{
	@Override
	/**
	 * Every time we create an activity with the intent to switch screens, we need an onCreate to get the saved state of the program
	 * and select what content view (GUI xml file) we're using
	 */
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control);
		//Intent intent = getIntent();
	}
}
