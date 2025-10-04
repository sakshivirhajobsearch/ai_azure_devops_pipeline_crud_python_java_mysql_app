from flask import Flask, jsonify
from flask_cors import CORS
from azure_devops import get_projects, get_pipelines
from ai_module import analyze_anomalies

app = Flask(__name__)
CORS(app)

@app.route('/')
def index():
    return jsonify({"message": "AI + Azure DevOps & Pipelines Flask backend running"})

@app.route('/projects')
def projects():
    projects = get_projects()
    return jsonify(projects)

@app.route('/pipelines')
def pipelines():
    pipelines, _ = get_pipelines()
    anomalies_detected = analyze_anomalies(pipelines)  # AI detection
    return jsonify({"pipelines": pipelines, "anomalies": anomalies_detected})

if __name__ == "__main__":
    app.run(debug=True)
