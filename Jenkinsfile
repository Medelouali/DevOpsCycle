pipeline {
    agent any
    
    environment {
        // Define environment variables
        DOCKER_HUB_CREDENTIALS = credentials('dckr_pat_neDIl-qYI_FitaxBN3PIcc4Z_GM')
        DOCKER_IMAGE_NAME = 'medelouali/devopscycle-image'
        MAVEN_HOME = tool 'Maven'

        imageName = "medelouali/devopscycle-image"
        registryCredential = 'medelouali-dockerhub'
        dockerImage = ''
    }
    
    stages {
        stage('Cloning Git') {
              steps {
                git([url: 'https://github.com/DevOpsTestOrgAi/DevOpsCycle.git', branch: 'main', credentialsId: 'dckr_pat_neDIl-qYI_FitaxBN3PIcc4Z_GM'])
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

        stage('Building image') {
              steps{
                script {
                  dockerImage = docker.build imageName
                }
              }
        }

        stage('Deploy Image') {
              steps{
                script {
                  docker.withRegistry( '', registryCredential ) {
                    dockerImage.push("$BUILD_NUMBER")
                     dockerImage.push('latest')
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
