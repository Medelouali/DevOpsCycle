pipeline {
    agent any
    
    tools{
    	maven "maven"
    	dockerTool 'docker'
    }
    
    environment {
        // Define environment variables
        //DOCKER_HUB_CREDENTIALS = credentials('dckr_pat_neDIl-qYI_FitaxBN3PIcc4Z_GM')
        DOCKER_IMAGE_NAME = 'medelouali/devopscycle-image'
  

        imageName = "medelouali/devopscycle-image"
        registryCredential = 'medelouali-dockerhub'
        dockerImage = ''
    }
    
    stages {
        stage('Checkout Source Code') {
            steps {
                script {
                    deleteDir()
                        checkout([$class: 'GitSCM',
                        branches: [[name: 'main']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [[$class: 'CleanBeforeCheckout']],
                        userRemoteConfigs: [[url: 'https://github.com/DevOpsTestOrgAi/DevOpsCycle.git']]])
                }
            }
        }
        /*
        stage('Unit Test') {
            steps {
                // Ruun unit tests using Maven
                sh "mvn test"
            }
        }
        */

        stage('Build') {
            steps {
                // Build the Spring Boot application using Maven
                sh "mvn package"
            }
        }

        stage('Building image') {
              steps{
                script {
                  dockerImage = docker.build imageName
                }
              }
        }
       stage('Build and Push Docker Image') {
           environment {
               DOCKER_IMAGE = "medelouali/devopscycle-image:2.${BUILD_ID}"
               DOCKERFILE_LOCATION = "."
           }
           steps {
               script {
                   // Build Docker image
                   sh "docker build -t ${DOCKER_IMAGE} ."

                   // Authenticate with Docker registry and push image using token
                   withCredentials([string(credentialsId: 'dockerhub-token', variable: 'dckr_pat_neDIl-qYI_FitaxBN3PIcc4Z_GM')]) {
                       sh "echo ${DOCKER_HUB_TOKEN} | docker login -u _json_key --password-stdin https://index.docker.io/v1/"
                       sh "docker push ${DOCKER_IMAGE}"
                   }

                   // Remove Docker image
                   sh "docker rmi -f ${DOCKER_IMAGE}"
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
