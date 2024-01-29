pipeline {
    agent any
    
    tools{
    	maven "maven"
    }
    
    environment {
        // Define environment variables
        //DOCKER_HUB_CREDENTIALS = credentials('dckr_pat_neDIl-qYI_FitaxBN3PIcc4Z_GM')
        DOCKER_IMAGE_NAME = 'medelouali/devopscycle-image'
	DOCKERHUB_CREDENTIALS= credentials('dckr_pat_neDIl-qYI_FitaxBN3PIcc4Z_GM') 

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
        stage('Unit Test') {
            steps {
                // Run unit tests using Maven
                sh "mvn clean test"
            }
        }

        stage('Build') {
            steps {
                // Buiild the Spring Boot application using Maven
                sh "mvn package"
            }
        }

       stage('Build Docker Image') {         
      steps{                
	sh 'sudo docker build -t medelouali/devopscycle-image:$BUILD_NUMBER .'           
        echo 'Build Image Completed'                
      }           
    }
    
    stage('Login to Docker Hub') {         
      steps{                            
	sh 'echo $DOCKERHUB_CREDENTIALS_PSW | sudo docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'                 
	echo 'Login Completed'                
      }           
    } 
                  
    stage('Push Image to Docker Hub') {         
      steps{                            
	sh 'sudo docker push medelouali/devopscycle-image:$BUILD_NUMBER'                 echo 'Push Image Completed'       
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
