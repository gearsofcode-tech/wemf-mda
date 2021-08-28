package tech.gearsofcode.wemf.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.gearsofcode.emft.EMFTemplate;
import tech.gearsofcode.wemf.EMFModelGenerationException;
import tech.gearsofcode.wemf.EMFModelGenerator;
import tech.gearsofcode.wemf.WEMFCodeGen;


public class CodegenPanel extends JPanel {

	private static final long serialVersionUID = 9068194339016741200L;
	private static final Logger logger = LoggerFactory.getLogger(CodegenPanel.class);

	private File workspace;
	private File project;
	
	private JTextField txtWorkspace;
	private JList<String> lstProject;
	private JList<String> lstSource;
	private JList<String> lstClass;
	private JList<String> lstModule;
	private JList<EMFTemplate> lstTemplate;
	JButton btnCodegen;
	
	private List<EMFTemplate> templates;
	

	public CodegenPanel() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		JLabel lblWorkspace = new JLabel("Workspace");
		add(lblWorkspace, gbc);
		
		gbc.gridx++;
		int txtWidth = 2;
		gbc.gridwidth= txtWidth;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		txtWorkspace = new JTextField();
		txtWorkspace.setColumns(20);
		txtWorkspace.setEditable(false);
		add(txtWorkspace, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridwidth=1;
		gbc.gridx+=txtWidth;
		gbc.anchor = GridBagConstraints.WEST;
		JButton btnWorkspace = new JButton("...");
		btnWorkspace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectWorkspace();
			}
		});
		add(btnWorkspace, gbc);
		
		gbc.gridx=0;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.CENTER;
		JLabel lblProject = new JLabel("Project");
		add(lblProject, gbc);
		
		gbc.gridy++;
		lstProject = new JList<String>();
		lstProject.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				projectSelected();
			}
		});
		JScrollPane jspProject = new JScrollPane(lstProject);
		jspProject.setPreferredSize(new Dimension(200,200));
		add(jspProject, gbc);
		
		gbc.gridy--;
		gbc.gridx++;
		JLabel lblSource = new JLabel("Source");
		add(lblSource, gbc);
		
		gbc.gridy++;
		lstSource = new JList<String>();
		lstSource.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting()) return;
				sourceSelected();
			}
		});
		JScrollPane jspSource = new JScrollPane(lstSource);
		jspSource.setPreferredSize(new Dimension(150, 200));
		add(jspSource, gbc);
		
		gbc.gridy--;
		gbc.gridx++;
		JLabel lblClass = new JLabel("Class");
		add(lblClass, gbc);
		
		gbc.gridy++;
		lstClass = new JList<String>();
		JScrollPane jspClass = new JScrollPane(lstClass);
		jspClass.setPreferredSize(new Dimension(150, 200));
		add(jspClass, gbc);
		
		gbc.gridy--;
		gbc.gridx++;
		JLabel lblModule = new JLabel("Module");
		add(lblModule, gbc);
			
		gbc.gridy++;
		lstModule = new JList<String>();
		lstModule.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		lstModule.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				moduleSelected();
			}
		});
		JScrollPane jspModule = new JScrollPane(lstModule);
		jspModule.setPreferredSize(new Dimension(100, 200));
		add(jspModule, gbc);
		
		gbc.gridy--;
		gbc.gridx++;
		JLabel lblTemplate = new JLabel("Template");
		add(lblTemplate, gbc);
			
		gbc.gridy++;
		
		lstTemplate = new JList<EMFTemplate>();
		lstTemplate.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane jspTemplate = new JScrollPane(lstTemplate);
		jspTemplate.setPreferredSize(new Dimension(400, 200));
		add(jspTemplate, gbc);

		
		
		gbc.gridy++;
		gbc.gridx=0;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		btnCodegen = new JButton("generate code");
		btnCodegen.setEnabled(false);
		btnCodegen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				generateCode();				
			}
		});
		add(btnCodegen, gbc);
		
		loadModules();
		
		loadWorkspace();
	}
	
	
	private void loadWorkspace() {
		try {
			File homeDir = new File(System.getProperty("user.home"));
			if (homeDir.exists()) {
				File wemfDir = new File(homeDir, ".wemf-mda");
				if (wemfDir.exists()) {
					File settings = new File(wemfDir, "wemf-mda.properties");
					if (settings.exists()) {
						Properties p = new Properties();
						p.load(new FileInputStream(settings));
						String workspace = p.getProperty("workspace");
						if (workspace!=null) {
							File file = new File(workspace);
							if (file.exists()) {
								workspaceSelected(file);
							}
						}
					}
				}
			}
		}
		catch (IOException e) {
			logger.error("Could not load user preferences.", e);
		}
	}
	
	
	private void selectWorkspace() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (jfc.showOpenDialog(getParent())==JFileChooser.APPROVE_OPTION){
			workspaceSelected(jfc.getSelectedFile());
		}
	}
	
	
	private void workspaceSelected(File workspace) {
		this.workspace = workspace;
		saveWorkspace(workspace);
		txtWorkspace.setText(workspace.getAbsolutePath());
		File[] subdirs = workspace.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory() && !f.isHidden() && !f.getName().startsWith(".");
			}
		});
		String[] data = new String[subdirs.length];
		for (int i=0; i<subdirs.length;i++) data[i] = subdirs[i].getName();
		lstProject.setListData(data);
	}
	
	

	private void saveWorkspace(File workspace2) {
		try {
			File homeDir = new File(System.getProperty("user.home"));
			File codeGenDir = new File(homeDir, ".wemf-mda");
			if (!codeGenDir.exists()) {
				codeGenDir.mkdirs();
			}
			if (codeGenDir.exists()) {
				File settings = new File(codeGenDir, "wemf-mda.properties");
				Properties properties = new Properties();
				if (settings.exists()) {
					properties.load(new FileInputStream(settings));
				}
				properties.put("workspace", workspace2.getAbsolutePath());
				FileOutputStream fos = new FileOutputStream(settings);
				properties.store(fos, "Properties");
				fos.flush();
				fos.close();
			}
		}
		catch (IOException e) {
			logger.error("Could not save user settings.");
			e.printStackTrace();
		}

	}



	private void projectSelected() {
		
		lstSource.setListData(new String[0]);
		lstClass.setListData(new String[0]);
		
		if (lstProject.getSelectedValue()==null) {
			btnCodegen.setEnabled(false);
			return;
		}
		btnCodegen.setEnabled(true);
		project = new File(workspace, lstProject.getSelectedValue());
				
		File resources = new File(project, "src/main/resources");
		File[] sources = resources.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.getName().endsWith("wemf") || f.getName().endsWith("nail");
			}
		});
		
		if (sources!=null) {
			String[] data = new String[sources.length];
			for (int i=0;i<sources.length;i++) data[i] = sources[i].getName();
			Arrays.sort(data);
			lstSource.setListData(data);
		}
	}
	
	
	private void sourceSelected() {
		lstClass.setListData(new String[0]);
		if (lstSource.getSelectedValue()==null) {
			return;
		}
		Thread t = new Thread() {
			public void run() {
				File source = new File(project, "src/main/resources/" + lstSource.getSelectedValue());
				EMFModelGenerator emfgen = new EMFModelGenerator();
				try {
					boolean isNail = source.getName().endsWith("nail");
					if (isNail) {
						return;
					}
					EPackage p = emfgen.generateModel(source);
					EList<EClassifier> lst = p.getEClassifiers();
					String[] data = new String[lst.size()];
					for (int i=0;i<lst.size();i++) data[i] = lst.get(i).getName(); 
					Arrays.sort(data);
					lstClass.setListData(data);
				}
				catch (EMFModelGenerationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	
	
	
	private void loadModules() {
		Thread t = new Thread() {
			public void run() {
				List<String> lst = WEMFCodeGen.getEMFTModules();
				lstModule.setListData(lst.toArray(new String[lst.size()]));
			}
		};
		t.start();
	}
	

	private void moduleSelected() {
		if (lstModule.getSelectedIndices()==null||lstModule.getSelectedIndices().length==0) {
			lstTemplate.setListData(new EMFTemplate[0]);
			return;
		}
		
		Thread t = new Thread() {
			public void run() {
				List<String> lstModules = new LinkedList<String>();
				for (int index : lstModule.getSelectedIndices()) {
					lstModules.add(lstModule.getModel().getElementAt(index));
				}
				templates = WEMFCodeGen.getEMFTemplates(lstModules);
				lstTemplate.setListData(templates.toArray(new EMFTemplate[templates.size()]));
			}
		};
		t.start();
	}
	
	
	private void generateCode() {
		List<String> sources = new LinkedList<String>();
		List<String> classifiers = new LinkedList<String>();
		List<String> modules = new LinkedList<String>();
		List<String> templates = new LinkedList<String>();
		if (!lstSource.isSelectionEmpty()) {
			for (int index : lstSource.getSelectedIndices()) sources.add(lstSource.getModel().getElementAt(index));
		}
		if (!lstClass.isSelectionEmpty()) {
			for (int index : lstClass.getSelectedIndices()) classifiers.add(lstClass.getModel().getElementAt(index));
		}
		if (!lstModule.isSelectionEmpty()) {
			for (int index : lstModule.getSelectedIndices()) modules.add(lstModule.getModel().getElementAt(index));
		}
		if (!lstTemplate.isSelectionEmpty()) {
			for (int index : lstTemplate.getSelectedIndices()) templates.add(lstTemplate.getModel().getElementAt(index).getName());
		}
		
		WEMFCodeGen.run(project, sources, classifiers, modules, templates);
	}
}
