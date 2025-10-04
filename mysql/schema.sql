CREATE DATABASE IF NOT EXISTS ai_azure_devops_pipeline;

USE ai_azure_devops_pipeline;

CREATE TABLE devops_projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    url VARCHAR(512),
    fetched_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE devops_pipelines (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(255),
    name VARCHAR(255),
    status VARCHAR(50),
    last_run DATETIME
);

-- Dummy data
INSERT INTO devops_projects (name, url) VALUES
('Project Alpha','https://dev.azure.com/your-org/Project Alpha'),
('Project Beta','https://dev.azure.com/your-org/Project Beta');

INSERT INTO devops_pipelines (project_name, name, status, last_run) VALUES
('Project Alpha','Build Project Alpha','success',NOW()),
('Project Alpha','Deploy Project Alpha','failed',NOW()),
('Project Beta','Build Project Beta','success',NOW()),
('Project Beta','Deploy Project Beta','failed',NOW());
