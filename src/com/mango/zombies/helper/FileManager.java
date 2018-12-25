package com.mango.zombies.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.GsonBuilder;

public class FileManager
{
	public static String ReadContentsAsString(String filePath)
	{
		StringBuilder contentBuilder = new StringBuilder();
		
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
	    {
	        String sCurrentLine;
	        
	        while ((sCurrentLine = br.readLine()) != null)
	        	contentBuilder.append(sCurrentLine).append("\n");
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    
	    return contentBuilder.toString();
	}
	
	public static void WriteFile(File directory, String name, Object contents)
	{
	    try (FileWriter writer = new FileWriter(directory + "/" + name))
	    {
	    	new GsonBuilder().setPrettyPrinting().create().toJson(contents, writer);
	    	writer.close();
		}
	    catch (IOException e)
	    {
			System.out.println("[Zombies] Failed to write: " + name);
		}
	}
}