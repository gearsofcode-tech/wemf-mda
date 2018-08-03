package com.gearsofcode.wemf;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gearsofcode.emft.EMFTemplate;
import com.gearsofcode.emft.EMFTemplateProcessor;

/**
 * This is the "main" class for generating code.
 * See {@link #run(File, String...)}
 * 
 * @author Carlos Padoa
 *
 */
public class WEMFCodeGen {
	
	private static Logger logger = LoggerFactory.getLogger(WEMFCodeGen.class);
	
	
	/**
	 * Executes code generation on the project contained in the specified directory.
	 * Executes the modules specified, or executes all modules if none is specified.
	 * */
	public static void run(File projectDir, List<String> sources, List<String>classifiers, List<String> modules, List<String> templates){
		File sourceDir = new File(projectDir, "src/main/resources/");
		File rootGen = new File(projectDir, "src/main");
		
		EMFTemplateProcessor templateProcessor = new EMFTemplateProcessor();
		
		if (sourceDir.exists()) {
			File[] arquivosWEMF = getWEMFFiles(sourceDir, sources);
			List<EMFTemplate> lstArquivosEMFT = getEMFTemplates(getSourceDir(), modules, templates);
			generateCode(rootGen, templateProcessor, arquivosWEMF, lstArquivosEMFT, classifiers);
		}
		else {
			logger.error("Didn't find maven directories structure. '.wemf' were expected at '/src/main/resources'.");
		}
	}

	
	
	/**
	 * Generates code.
	 * @param rootGen target directory for generated files.
	 * @param templateProcessor
	 * @param wemfFiles files containing the written model.
	 * @param lstEMFTFiles template files.
	 */
	private static void generateCode(File rootGen, EMFTemplateProcessor templateProcessor, File[] wemfFiles, List<EMFTemplate> lstEMFTFiles, List<String>classifiers) {
		try {
			EMFModelGenerator emfGen = new EMFModelGenerator();
			EPackage model = null;
			for (File fWEMF : wemfFiles) {
				model = emfGen.generateModel(fWEMF);
				for (EMFTemplate template : lstEMFTFiles) {
					logger.info(String.format("Executing template '%s' with file '%s'", template.getName(), fWEMF.getName()));
					templateProcessor.process(model, template, rootGen, classifiers);
				}
			}
		}
		catch (EMFModelGenerationException e) {
			String msg = "Error generating code.";
			logger.error(msg, e);
		}
	}



	/**
	 * Finds .wemf files in a directory.
	 * */
	private static File[] getWEMFFiles(File sourceDir, List<String> sources) {
		File[] arquivosWEMF = sourceDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".wemf") && (sources.isEmpty() || sources.contains(f.getName()));
			}
		});
		if (arquivosWEMF.length==0) {
			logger.warn("No '.wemf' found. No code will be generated.");
		}
		for (File f: arquivosWEMF) logger.info(String.format("Found file '%s'.",  f.getName()));
		return arquivosWEMF;
	}
	
	
	/**
	 * Runs code generation with the "current project".
	 * 
	 * */
	public static void run(){
		File currentDir = new File(ClassLoader.getSystemResource(".").getFile());
		while (currentDir.getParentFile()!=null && !currentDir.getName().equals("target") && !currentDir.getName().equals("bin"))
			currentDir = currentDir.getParentFile();
		
		File sourceDir = new File(currentDir.getParentFile(), "src/main/resources/");
		File rootGen = new File(currentDir.getParentFile(), "src/main");
		
		EMFTemplateProcessor templateProcessor = new EMFTemplateProcessor();
		
		if (sourceDir.exists()) {
			File[] arquivosWEMF = sourceDir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().endsWith(".wemf");
				}
			});
			if (arquivosWEMF.length==0) {
				logger.warn("No '.wemf' file was found. No code will be generated.");
			}
			for (File f: arquivosWEMF) logger.info(String.format("Found file '%s'", f.getName()));
			
			List<EMFTemplate> lstArquivosEMFT = getEMFTemplates(new File(sourceDir, "emft"), new ArrayList<String>(), new ArrayList<String>());
			generateCode(rootGen, templateProcessor, arquivosWEMF, lstArquivosEMFT, new ArrayList<String>());
		}
		else {
			logger.error("Didn't find maven directories structure. '.wemf' were expected at '/src/main/resources'.");
		}
	}

	
	
	/**
	 * Traverses the @{sourceDir} directory looking for subdirectories with ".emft" files.
	 * @param modules 
	 * */
	public static List<String> getEMFTModules() {
		List<String> modules = new ArrayList<String>();
		File sourceDir = getSourceDir();
		if (sourceDir.exists()) {
			File [] subdirs = sourceDir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.isDirectory();
				}
			});
			for (File f : subdirs) {
				modules.add(f.getName());
			}
		}
		return modules;
	}

	
	/**
	 * Traverses the @{sourceDir} directory looking for subdirectories with ".emft" files.
	 * @param modules 
	 * */
	private static List<EMFTemplate> getEMFTemplates(File sourceDir, List<String> modules, List<String> templates) {
		List<EMFTemplate> emftFiles = new ArrayList<EMFTemplate>();
		File[] subdirectories = sourceDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory() && (modules.size()==0||modules.contains(f.getName()));
			}
		});
		
		
		if (subdirectories==null) {
			logger.info("Looking for templates inside the jar.");
			URL url = WEMFCodeGen.class.getResource("/emft");
			File jarFile = new File(url.getFile());
			subdirectories = jarFile.listFiles(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.isDirectory();
				}
			});
		}
		
		if (subdirectories != null) {
			for (File subdir : subdirectories) {
				getTemplateFiles(emftFiles, subdir.getName(), subdir);
			}
		}
		else {
			logger.warn("No subdirectories with templates were found.");
		}
		if (!templates.isEmpty()) {
			Iterator<EMFTemplate> it = emftFiles.iterator();
			while (it.hasNext()) {
				if (!templates.contains(it.next().getName())) it.remove();
			}
		}
		return emftFiles;
	}



	/**
	 * Gets template files (*.emft, *.html) recursively.
	 * */
	private static void getTemplateFiles(List<EMFTemplate> emftFiles, String module, File subdir) {
		File[] templates = subdir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".emft") || f.getName().endsWith(".html");
			}
		});
		for (File f: templates) {
			emftFiles.add(new EMFTemplate(module, f));
		}
		File[] subdirs = subdir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory();
			}
		});
		for (int i=0; i<subdirs.length;i++) {
			getTemplateFiles(emftFiles, module, subdirs[i]);
		}
	}


	
	public static List<EMFTemplate> getEMFTemplates(List<String> lstModules) {
		File sourceDir = getSourceDir();
		return getEMFTemplates(sourceDir, lstModules, new ArrayList<String>());
	}


	/**
	 * Finds where project is located.
	 * */
	private static File getSourceDir() {
		File currentDir = new File(ClassLoader.getSystemResource(".").getFile());
		while (currentDir.getParentFile()!=null && !currentDir.getName().equals("target") && !currentDir.getName().equals("bin"))
			currentDir = currentDir.getParentFile();
		File sourceDir = new File(currentDir.getParentFile(), "src/main/resources/emft");
		return sourceDir;
	}
	
	
	public static void main(String args[]) {
		if (args.length==0) {
			StringBuilder strb = new StringBuilder();
			strb.append("Usage: projectDir [-s] [-c] [-m] [-t]\n\n")
				.append("projectDir: full path between double quotes\n")
				.append("-s: followed by list of source filenames (not full path)\n")
				.append("-c: followed by list of classifiers\n")
				.append("-m: followed by list of modules\n")
				.append("-t: followed by list of templates\n\n")
				.append("lists are space separated. Ex.:\n")
				.append("\"/home/user/git/myproject\" -s MyProject.emft -c EntityName -m spring -t spring/RestController.emft");
			System.out.println(strb.toString());
		}
		List<String> lstSources = new LinkedList<String>();
		List<String> lstClassifiers = new LinkedList<String>();
		List<String> lstModules = new LinkedList<String>();
		List<String> lstTemplates = new LinkedList<String>();
		
		boolean sources = false;
		boolean classifiers = false;
		boolean modules = false;
		boolean templates = false;
		for (int i=1; i<args.length;i++) {
			if ("-s".equals(args[i])) {
				sources = true;
				classifiers = false;
				modules = false;
				templates = false;
				continue;
			}
			else if ("-c".equals(args[i])) {
				sources = false;
				classifiers = true;
				modules = false;
				templates = false;
				continue;
			}
			else if ("-m".equals(args[i])) {
				sources = false;
				classifiers = false;
				modules = true;
				templates = false;
				continue;
			}
			else if ("-t".equals(args[i])) {
				sources = false;
				classifiers = false;
				modules = false;
				templates = true;
				continue;
			}
			if (sources) lstSources.add(args[i]);
			else if (classifiers) lstClassifiers.add(args[i]);
			else if (modules) lstModules.add(args[i]);
			else if (templates) lstTemplates.add(args[i]);
			else {
				System.out.println("Unknown argument");
				System.exit(-1);
			}
		}
		WEMFCodeGen.run(new File(args[0]),lstSources, lstClassifiers, lstModules, lstTemplates);
	}
}