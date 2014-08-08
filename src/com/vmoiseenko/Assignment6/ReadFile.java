package com.vmoiseenko.Assignment6;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {
	private String path;

public ReadFile(String path){
this.path=path;
}

public String getText() throws IOException{
String content="";
//get the file stream--read it from disk
FileInputStream fstream=new FileInputStream(path);
//get the data in the file
DataInputStream fileIn = new DataInputStream(fstream);
//buffer it
BufferedReader buffReader =
new BufferedReader(new InputStreamReader(fileIn));
String stringln;
//loop through the buffer to get the content
while ((stringln=buffReader.readLine()) != null){
//concatinate in each line and add the new line
//character
content += (stringln + "\n");
}
//return the string with all the file content
return content;
}
}
