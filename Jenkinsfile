pipeline {
    agent any
    
    environment {
        // Define environment variables
        DOCKER_HUB_CREDENTIALS = credentials('dckr_pat_7f19tOClkKr5QQIsRF6U6Bf36Us')
        DOCKER_IMAGE_NAME = 'medelouali/devopscycle-image'
        MAVEN_HOME = tool 'Maven'
    }
    
    stages {
        stage("Git Checkout"){  
    		steps{     
			git credentialsId: 'github', url: 'https://github.com/DevOpsTestOrgAi/DevOpsCycle'
			echo 'Git Checkout Completed'   
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
    		steps{                     
			sh 'sudo docker build -t medelouali/devopscycle-image:		$BUILD_NUMBER .'     
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
 			sh 'sudo docker push medelouali/devopscycle-image:$BUILD_NUMBER'           
			echo 'Push Image Completed'       
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

