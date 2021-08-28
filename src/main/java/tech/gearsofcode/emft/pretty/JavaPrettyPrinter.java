package tech.gearsofcode.emft.pretty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class JavaPrettyPrinter {

	
	public static void prettyPrint(File f) throws IOException {
		List<String> allLines = Files.readAllLines(f.toPath());
		StringBuilder strb = new StringBuilder();
		String linha;
		String nextLinha;
		int j;
		for (int i=0; i<allLines.size();i++) {
			linha = allLines.get(i);
			
			/**Find next non empty line*/
			j = i+1;
			nextLinha = null;
			while(j<allLines.size() && allLines.get(j).trim().isEmpty()) {
				j++; 
			}
			if (j<allLines.size()) nextLinha = allLines.get(j).trim();

			//Skip empty lines
			if (linha.trim().length()==0) continue;
			
			//Add lines between import and main declaration
			if (i>0 && allLines.get(i-1).startsWith("import") && !linha.startsWith("import")) {
				strb.append("\n\n\n");
			}
			
			//Append line
			strb.append(linha);
			
			//Add 3 lines between methods
			if (linha.trim().endsWith("}") && (i+1<allLines.size()) 
					&&  (nextLinha.startsWith("public") || nextLinha.startsWith("private") || nextLinha.endsWith("Override"))){
				strb.append("\n\n\n\n");
			}
			//Add 1 extra line between private declarations
			else if (linha.trim().startsWith("private") && (i+1<allLines.size() && nextLinha.startsWith("@"))) {
				strb.append("\n\n");
			}
			//Add break line that is removed from "readLines"
			else {
				strb.append("\n");
			}
		}
		FileWriter fw = new FileWriter(f);
		fw.write(strb.toString());
		fw.close();
	}

}
