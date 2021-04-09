package model.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextFileReader {

	/**
	 * Directory Name
	 */
	public static String DIRNAME = "domination";

	/**
	 * This is the method that the client class knows how to use
	 * 
	 * @throws IOException
	 */
	public Map<String, List<String>> readFile(String str) throws IOException {
		System.out.println("TextFileReader.readFile(): " + str);
		Map<String, List<String>> createdMap = new HashMap<String, List<String>>();
		// read the file and create the map object
		File l_F3 = getFile(str);
		BufferedReader l_Br = new BufferedReader(new FileReader(l_F3));
		String l_Line = "";
		boolean f1 = false, f2 = false, f3 = false;
		List<String> list1 = new ArrayList<>(), list2 = new ArrayList<>(), list3 = new ArrayList<>();
		while ((l_Line = l_Br.readLine()) != null) {
			if ("[continents]".equals(l_Line)) {
				f1 = true;
				continue;
			}
			if ("[countries]".equals(l_Line)) {
				f1 = false;
				f2 = true;
				continue;
			}
			if ("[borders]".equals(l_Line)) {
				f2 = false;
				f3 = true;
				continue;
			}
			if (f1 && !"".equals(l_Line.trim())) {
				list1.add(l_Line.trim());
			}
			if (f2 && !"".equals(l_Line.trim())) {
				list2.add(l_Line.trim());
			}
			if (f3 && !"".equals(l_Line.trim())) {
				list3.add(l_Line.trim());
			}
		}
		l_Br.close();
		createdMap.put("[continents]", list1);
		createdMap.put("[countries]", list2);
		createdMap.put("[borders]", list3);
		return createdMap;
	}

	public static File getFile(String p_FileName) throws IOException {
		File l_F = new File("");
		String l_Path = l_F.getCanonicalPath();
		File l_F2 = new File(l_Path + "/" + DIRNAME);
		if (!l_F2.exists()) {
			l_F2.mkdir();
		}
		File l_F3 = new File(l_F2.getAbsolutePath() + "/" + p_FileName);
		return l_F3;
	}
}
