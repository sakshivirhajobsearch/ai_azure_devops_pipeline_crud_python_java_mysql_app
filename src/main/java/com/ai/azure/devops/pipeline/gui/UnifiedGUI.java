package com.ai.azure.devops.pipeline.gui;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UnifiedGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTable projectTable, pipelineTable;
	private DefaultTableModel projectModel, pipelineModel;
	private DevOpsRepository repo;

	public UnifiedGUI() {
		repo = new DevOpsRepository();
		setTitle("AI + Azure DevOps CRUD");
		setSize(900, 600);
		setLayout(new GridLayout(2, 1));

		// Projects Table
		projectModel = new DefaultTableModel(new String[] { "ID", "Name", "URL", "Fetched At" }, 0);
		projectTable = new JTable(projectModel);
		JScrollPane projectScroll = new JScrollPane(projectTable);
		add(projectScroll);

		// Pipelines Table
		pipelineModel = new DefaultTableModel(new String[] { "ID", "Project", "Name", "Status", "Last Run" }, 0);
		pipelineTable = new JTable(pipelineModel);
		JScrollPane pipelineScroll = new JScrollPane(pipelineTable);
		add(pipelineScroll);

		// Load data
		loadData();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void loadData() {
		List<Vector<Object>> projects = repo.getProjects();
		for (Vector<Object> row : projects)
			projectModel.addRow(row);

		List<Vector<Object>> pipelines = repo.getPipelines();
		for (Vector<Object> row : pipelines)
			pipelineModel.addRow(row);
	}

	public static void main(String[] args) {
		new UnifiedGUI();
	}
}
