from datetime import datetime

# Simulated Azure DevOps data
AZURE_DEVOPS_ORG_URL = "https://dev.azure.com/your-org"
PROJECTS = ["Project Alpha", "Project Beta"]

def get_projects():
    result = []
    for p in PROJECTS:
        result.append({"name": p, "url": f"{AZURE_DEVOPS_ORG_URL}/{p}"})
    return result

def get_pipelines():
    pipelines = []
    anomalies = []
    for p in PROJECTS:
        pipelines.append({
            "project_name": p,
            "name": f"Build {p}",
            "status": "success",
            "last_run": datetime.now().isoformat()
        })
        pipelines.append({
            "project_name": p,
            "name": f"Deploy {p}",
            "status": "failed",
            "last_run": datetime.now().isoformat()
        })
    return pipelines, anomalies
