
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 统计代码行数
 */
public class CalculateRows {
	static long classCount = 0; // Java类的数量
	static long normalLines = 0; // 空行
	static long commentLines = 0; // 注释行
	static long codeLines = 0; // 代码行
	static long allLines = 0; // 代码行
 
	public static void main(String[] args) throws Exception {
		File f = new File("D:\\Orion\\PhoneApp"); // 目录
		String type = ".dart";// 查找什么类型的代码，如".java"就是查找以java开发的代码量，".php"就是查找以PHP开发的代码量
		CalculateRows.treeFile(f, type);
		System.out.println("路径：" + f.getPath());
		System.out.println(type + "类Class数量：" + classCount);
		System.out.println("代码数量：" + codeLines);
		System.out.println("注释数量：" + commentLines);
		System.out.println("空行数量：" + normalLines);
		if (classCount == 0) {
			System.out.println("代码平均数量:" + 0);
		} else {
			System.out.println("代码平均数量:" + codeLines / classCount);
		}
		System.out.println("总代码行：" + allLines);
	}
 
	public static void treeFile(File f, String type) throws Exception {
		File[] childs = f.listFiles();
		for (int i = 0; i < childs.length; i++) {
			File file = childs[i];
			if (!file.isDirectory()) {
				if (file.getName().endsWith(type)) {
					classCount++;
					BufferedReader br = null;
					boolean comment = false;
					br = new BufferedReader(new FileReader(file));
					String line = "";
					while ((line = br.readLine()) != null) {
						allLines++;
						line = line.trim();
						if (line.matches("^[//s&&[^//n]]*$")) {// 这一行匹配以空格开头，但不是以回车符开头，但以回车符结尾
							normalLines++;
						} else if (line.startsWith("/*") && !line.endsWith("*/")) {// 匹配以/*......*/括住的多行注释
							commentLines++;
							comment = true;
						} else if (true == comment) {
							commentLines++;
							if (line.endsWith("*/")) {
								comment = false;
							}
						} else if (line.startsWith("//")
								|| (line.startsWith("/*") && line.endsWith("*/"))) {
							commentLines++;
						} else {// 其他的就是代码行
							codeLines++;
						}
					}
					if (br != null) {
						br.close();
						br = null;
					}
				}
			} else {
				treeFile(childs[i], type);
			}
		}
	}
}