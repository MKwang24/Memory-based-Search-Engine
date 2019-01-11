package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileDocSource extends DocSource{
	private ArrayList<File> _fds;
	public FileDocSource(String location) {
		// use FileFinder to find all of the documents in 'location'
		
		_fds = FileFinder.GetAllFiles(location);
	}
	@Override
	public int getNumDocs() {
		// TODO returns the number of documents found
		return _fds.size();
	}

	@Override
	public String getDoc(int id) {
		// TODO loads the file corresponding to document location 'id'
		BufferedReader cin;
		String s = null; 
		String rl = null;
		try {
			cin = new BufferedReader(new FileReader(_fds.get(id)));
			StringBuilder sb = new StringBuilder();
			while((rl = cin.readLine())!=null) { 
				sb.append(rl+" ");
			}
			s = sb.toString();
			cin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return s ;
	}

	
}
