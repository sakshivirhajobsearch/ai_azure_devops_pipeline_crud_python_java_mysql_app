package com.ai.azure.devops.pipeline.gui;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DevOpsRepository {
	private String baseUrl;

	public DevOpsRepository() {
		Properties props = new Properties();
		try {
			// Load from classpath
			InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
			if (is == null)
				throw new IOException("config.properties not found in classpath");
			props.load(is);
			baseUrl = props.getProperty("backend.api.baseurl");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "❌ Failed to load config.properties: " + e.getMessage());
			baseUrl = "http://127.0.0.1:5000"; // fallback
		}
	}

	public List<Vector<Object>> getProjects() {
		List<Vector<Object>> list = new ArrayList<>();
		try {
			URL url = new URL(baseUrl + "/projects");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			JsonArray arr = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonArray();
			int id = 1;
			for (JsonElement e : arr) {
				JsonObject obj = e.getAsJsonObject();
				Vector<Object> row = new Vector<>();
				row.add(id++);
				row.add(obj.get("name").getAsString());
				row.add(obj.has("url") ? obj.get("url").getAsString() : "N/A");
				row.add(new Date().toString());
				list.add(row);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Failed to fetch projects: " + ex.getMessage());
		}
		return list;
	}

	public List<Vector<Object>> getPipelines() {
		List<Vector<Object>> list = new ArrayList<>();
		try {
			URL url = new URL(baseUrl + "/pipelines");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			JsonObject obj = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();
			JsonArray pipelines = obj.getAsJsonArray("pipelines");
			int id = 1;
			for (JsonElement e : pipelines) {
				JsonObject pipe = e.getAsJsonObject();
				Vector<Object> row = new Vector<>();
				row.add(id++);
				row.add(pipe.get("project_name").getAsString());
				row.add(pipe.get("name").getAsString());
				row.add(pipe.get("status").getAsString());
				row.add(pipe.get("last_run").getAsString());
				list.add(row);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Failed to fetch pipelines: " + ex.getMessage());
		}
		return list;
	}
}
