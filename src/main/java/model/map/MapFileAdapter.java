package model.map;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Adapter transform the map into the right type of map
 */
public class MapFileAdapter extends DominationMapReader implements Serializable {

	/**
	 *  get other file types
	 */
	private ConquestMapReader d_otherFileType;

	/**
	 * implement map file adapter.
	 * @param p_fr file reader.
	 */
	public MapFileAdapter(ConquestMapReader p_fr) {
		// the roundPeg is plugged into the adapter
		this.d_otherFileType = p_fr;
	}

	/**
	 * Map File Adapter
	 */
	public MapFileAdapter() {
	}

	/**
	 * Transform a domination format map file to a conquest format map file
	 * @param p_str map file name
	 * @throws IOException if map file does not exist
	 */
	public void dominationFileToConquestFile(String p_str) throws IOException {
		Map<String, List<String>> l_map = readFile(p_str);
		// read content from territories tag in conquest map
		StringBuffer l_terr = new StringBuffer("[Territories]\n");
		List<String> l_flag = new ArrayList<>();
		// read content from territories tag in domination map
		l_map.get("[borders]").forEach(b -> {
			StringBuffer l_te = new StringBuffer();
			String[] l_sp = b.split(" ");
			for (int l_i = 0; l_i < l_sp.length; l_i++) {
				String l_s = l_sp[l_i];
				if (l_i == 0) {
					// read content from countries tag in domination map
					l_map.get("[countries]").forEach(c -> {
						if (c.startsWith(l_s)) {
							String[] l_cs = c.split(" ");
							String l_con = l_map.get("[continents]").get(Integer.parseInt(l_cs[2]) - 1).split(" ")[0];
							l_te.append(l_cs[1] + "," + l_con + ",");
							// determine whether the previous and present continents are the same
							if (!l_flag.isEmpty()) {
								String l_dl = l_flag.get(0);
								if (!l_con.equals(l_dl)) {
									l_terr.append("\n");
								}
								l_flag.set(0, l_con);
							} else {
								l_flag.add(l_con);
							}
						}
					});
				} else {
					l_map.get("[countries]").forEach(c -> {
						if (c.startsWith(l_s)) {
							String[] l_cs = c.split(" ");
							l_te.append(l_cs[1] + ",");
						}
					});
				}
				if (l_i == l_sp.length - 1)
					l_terr.append(l_te.toString().substring(0, l_te.toString().length() - 1));
			}
			l_terr.append("\n");
		});
		// deal with continents and write in map file
		File l_File = getFile(p_str);
		BufferedWriter l_Bw = new BufferedWriter(new FileWriter(l_File));
		l_Bw.write("[continents]\n");
		l_map.get("[continents]").forEach(con -> {
			String l_s = con.replace(" ", "=");
			try {
				l_Bw.write(l_s + "\n");
				l_Bw.flush();
			} catch (IOException l_e) {
				try {
					l_Bw.close();
				} catch (IOException l_e1) {
					l_e1.printStackTrace();
				}
				l_e.printStackTrace();
			}
		});
		l_Bw.write("\n");
		l_Bw.write(l_terr.toString());
		l_Bw.close();
	}

	/**
	 * Transform a conquest format map file to a domination format map file
	 * @param p_str map file name
	 * @throws IOException if map file does not exist
	 */
	public void conquestFileToDominationFile(String p_str) throws IOException {
		Map<String, List<String>> l_map = this.d_otherFileType.readJSONFileType(p_str);
		// read content from territories tag in conquest map
		List<String> l_list = l_map.get("[Territories]");
		String l_s = "[countries]\n";
		String l_s2 = "[borders]\n";
		// map records the country number
		Map<String, Integer> l_couId = new LinkedHashMap<>();
		// find the continent of the country
		Map<String, Integer> l_dlId = new LinkedHashMap<>();
		l_list.forEach(t -> {
			String[] l_cs = t.split(",");
			int l_t2 = 0;
			for (int l_j = 0; l_j < l_cs.length; l_j++) {
				if (l_cs[l_j].matches("[0-9]*"))
					continue;
				l_t2++;
				if (l_t2 == 2) {
					List<String> l_list2 = l_map.get("[continents]");
					for (int l_a = 0, l_len2 = l_list2.size(); l_a < l_len2; l_a++) {
						String[] l_cons = l_list2.get(l_a).split("=");
						if (l_cs[l_j].equals(l_cons[0])) {
							l_dlId.put(l_cs[0], l_a + 1);
						}
					}
				}
			}
		});
		for (int l_i = 0, l_len = l_list.size(); l_i < l_len; l_i++) {
			String[] l_cs = l_list.get(l_i).split(",");
			// conNum：record the continent number
			int l_t = 0;
			for (int l_j = 0; l_j < l_cs.length; l_j++) {
				if (l_cs[l_j].matches("[0-9]*"))
					continue;
				l_t++;
				if (l_t != 2) {
					Integer l_id = l_couId.get(l_cs[l_j]);
					if (l_id == null) {
						l_id = 0;
						for (Map.Entry<String, Integer> en : l_couId.entrySet()) {
							l_id = en.getValue();
						}
						l_id += 1;
						l_couId.put(l_cs[l_j], l_id);
						l_s += l_id + " " + l_cs[l_j] + " " + l_dlId.get(l_cs[l_j]) + "\n";
					}
					l_s2 += (l_id + " ");
					// remove the blank space
					if (l_j == l_cs.length - 1)
						l_s2 = l_s2.substring(0, l_s2.length() - 1) + "\n";
				}
			}
		}
		// deal with continents and write in map file
		File l_File = getFile(p_str);
		BufferedWriter l_Bw = new BufferedWriter(new FileWriter(l_File));
		l_Bw.write("[continents]\n");
		l_map.get("[continents]").forEach(con -> {
			String l_c = con.replace("=", " ");
			try {
				l_Bw.write(l_c + "\n");
				l_Bw.flush();
			} catch (IOException l_e) {
				try {
					l_Bw.close();
				} catch (IOException l_e1) {
					l_e1.printStackTrace();
				}
				l_e.printStackTrace();
			}
		});
		l_Bw.write("\n");
		l_Bw.write(l_s+"\n");
		l_Bw.write(l_s2);
		l_Bw.close();
	}
}
