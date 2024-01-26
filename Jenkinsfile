pipeline {
    agent any
    
    environment {
        // Define environment variables
        DOCKER_HUB_CREDENTIALS = credentials('dockeriscool@@')
        DOCKER_IMAGE_NAME = 'medelouali/devopscycle-image'
        MAVEN_HOME = tool 'Maven'
    }
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout source code from version control
                git 'https://github.com/DevOpsTestOrgAi/DevOpsCycle'
            }
        }
        
        stage('Unit Test') {
            steps {
                // Run unit tests using Maven
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }
        
        stage('Build') {
            steps {
                // Build the Spring Boot application using Maven
                sh "${MAVEN_HOME}/bin/mvn clean package"
            }
        }
        
        stage('Dockerize') {
            steps {
                // Build Docker image
                script {
                    docker.build "${DOCKER_IMAGE_NAME}:${BUILD_ID}"
                }
            }
        }
        
        stage('Push to Docker Hub') {
            steps {
                // Push Docker image to Docker Hub
                script {
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_HUB_CREDENTIALS) {
                        dockerImage.push("BUILD_ID")
                    }
                }
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline successfully completed!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

