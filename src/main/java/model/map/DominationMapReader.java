package model.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * read the map file with domination type.
 */
public class DominationMapReader {

	/**
	 * Directory Name
	 */
	public static String DIRNAME = "domination";

	/**
	 * read a domination map
	 * @param p_str map name
	 * @return map file
	 * @throws IOException if map does not exist
	 */
	public Map<String, List<String>> readFile(String p_str) throws IOException {
		System.out.println("DominationMapReader.readFile(): " + p_str);
		// set up a hash map file
		Map<String, List<String>> l_createdMap = new HashMap<String, List<String>>();
		// read the file and create the map object
		File l_F3 = getFile(p_str);
		BufferedReader l_Br = new BufferedReader(new FileReader(l_F3));
		String l_Line = "";
		boolean l_f1 = false, l_f2 = false, l_f3 = false;
		// read context from  domination map file
		List<String> l_list1 = new ArrayList<>(), l_list2 = new ArrayList<>(), l_list3 = new ArrayList<>();
		while ((l_Line = l_Br.readLine()) != null) {
			if ("[continents]".equals(l_Line)) {
				l_f1 = true;
				continue;
			}
			if ("[countries]".equals(l_Line)) {
				l_f1 = false;
				l_f2 = true;
				continue;
			}
			if ("[borders]".equals(l_Line)) {
				l_f2 = false;
				l_f3 = true;
				continue;
			}
			if (l_f1 && !"".equals(l_Line.trim())) {
				l_list1.add(l_Line.trim());
			}
			if (l_f2 && !"".equals(l_Line.trim())) {
				l_list2.add(l_Line.trim());
			}
			if (l_f3 && !"".equals(l_Line.trim())) {
				l_list3.add(l_Line.trim());
			}
		}
		l_Br.close();
		l_createdMap.put("[continents]", l_list1);
		l_createdMap.put("[countries]", l_list2);
		l_createdMap.put("[borders]", l_list3);
		return l_createdMap;
	}

	/**
	 * Get a model.map file from an existing "domination" model.map file
	 *
	 * @param p_FileName path to file
	 * @return the model.map file
	 * @throws IOException if file not found or cannot read
	 */
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
