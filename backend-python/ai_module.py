def analyze_anomalies(pipelines):
    anomalies = []
    for p in pipelines:
        if p["status"] == "failed":
            anomalies.append({
                "project": p["project_name"],
                "name": p["name"],
                "failure_rate": 0.75,
                "status": "failed"
            })
    return anomalies
