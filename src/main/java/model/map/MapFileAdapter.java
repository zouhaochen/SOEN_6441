package model.map;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapFileAdapter extends DominationMapReader {

	private ConquestMapReader otherFileType;

	public MapFileAdapter(ConquestMapReader p_fr) {
		// the roundPeg is plugged into the adapter
		this.otherFileType = p_fr;
	}

	public MapFileAdapter() {
	}

	public void textFileToJsonFile(String str) throws IOException {
		Map<String, List<String>> map = readFile(str);
		StringBuffer terr = new StringBuffer("[Territories]\n");
		List<String> flag = new ArrayList<>();
		map.get("[borders]").forEach(b -> {
			StringBuffer te = new StringBuffer();
			String[] sp = b.split(" ");
			for (int i = 0; i < sp.length; i++) {
				String s = sp[i];
				if (i == 0) {
					map.get("[countries]").forEach(c -> {
						if (c.startsWith(s)) {
							String[] cs = c.split(" ");
							String con = map.get("[continents]").get(Integer.parseInt(cs[2]) - 1).split(" ")[0];
							te.append(cs[1] + "," + con + ",");
							// 判断上一个和当前是否一个大陆
							if (!flag.isEmpty()) {
								String dl = flag.get(0);
								if (!con.equals(dl)) {
									terr.append("\n");
								}
								flag.set(0, con);
							} else {
								flag.add(con);
							}
						}
					});
				} else {
					map.get("[countries]").forEach(c -> {
						if (c.startsWith(s)) {
							String[] cs = c.split(" ");
							te.append(cs[1] + ",");
						}
					});
				}
				if (i == sp.length - 1)
					terr.append(te.toString().substring(0, te.toString().length() - 1));
			}
			terr.append("\n");
		});

		// 处理[continents],并写入文件中
		File l_File = getFile(str);
		BufferedWriter l_Bw = new BufferedWriter(new FileWriter(l_File));
		l_Bw.write("[continents]\n");
		map.get("[continents]").forEach(con -> {
			String s = con.replace(" ", "=");
			try {
				l_Bw.write(s + "\n");
				l_Bw.flush();
			} catch (IOException e) {
				try {
					l_Bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		});
		l_Bw.write("\n");
		l_Bw.write(terr.toString());
		l_Bw.close();
	}

	public void jsonFileToTextFile(String str) throws IOException {
		Map<String, List<String>> map = this.otherFileType.readJSONFileType(str);
		List<String> list = map.get("[Territories]");
		String s = "[countries]\n";
		String s2 = "[borders]\n";
		// map记录国家编号
		Map<String, Integer> couId = new LinkedHashMap<>();
		// 找出国家对应大陆
		Map<String, Integer> dlId = new LinkedHashMap<>();
		list.forEach(t -> {
			String[] cs = t.split(",");
			int t2 = 0;
			for (int j = 0; j < cs.length; j++) {
				if (cs[j].matches("[0-9]*"))
					continue;
				t2++;
				if (t2 == 2) {
					List<String> list2 = map.get("[continents]");
					for (int a = 0, len2 = list2.size(); a < len2; a++) {
						String[] cons = list2.get(a).split("=");
						if (cs[j].equals(cons[0])) {
							dlId.put(cs[0], a + 1);
						}
					}
				}
			}
		});
		for (int i = 0, len = list.size(); i < len; i++) {
			String[] cs = list.get(i).split(",");
			// conNum：记录大陆序号
			int t = 0;
			for (int j = 0; j < cs.length; j++) {
				if (cs[j].matches("[0-9]*"))
					continue;
				t++;
				if (t != 2) {
					Integer id = couId.get(cs[j]);
					if (id == null) {
						id = 0;
						for (Map.Entry<String, Integer> en : couId.entrySet()) {
							id = en.getValue();
						}
						id += 1;
						couId.put(cs[j], id);
						s += id + " " + cs[j] + " " + dlId.get(cs[j]) + "\n";
					}
					s2 += (id + " ");
					// 去空格
					if (j == cs.length - 1)
						s2 = s2.substring(0, s2.length() - 1) + "\n";
				}
			}
		}
		// 处理[continents],并写入文件中
		File l_File = getFile(str);
		BufferedWriter l_Bw = new BufferedWriter(new FileWriter(l_File));
		l_Bw.write("[continents]\n");
		map.get("[continents]").forEach(con -> {
			String c = con.replace("=", " ");
			try {
				l_Bw.write(c + "\n");
				l_Bw.flush();
			} catch (IOException e) {
				try {
					l_Bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		});
		l_Bw.write("\n");
		l_Bw.write(s+"\n");
		l_Bw.write(s2);
		l_Bw.close();

	}
}
