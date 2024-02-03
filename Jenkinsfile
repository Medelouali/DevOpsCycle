pipeline {
    agent any
    triggers {
    	githubPush()
  }
    
    tools{
    	maven "maven"
    }
    
    environment {
        // Define environment variables
        DOCKER_IMAGE_NAME = 'medelouali/devopscycle-image'
	DOCKERHUB_CREDENTIALS= credentials('dockerhubcredentials') 

        imageName = "medelouali/devopscycle-image"
        imageTag= "1.xx"
        registryCredential = 'medelouali-dockerhub'
        dockerImage = ''
        GIT_CONFIG_NAME = 'Medelouali'
        GIT_CONFIG_EMAIL = 'Mohammedelouali1999@gmail.com'
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
	sh 'docker build -t medelouali/devopscycle-image:1.$BUILD_NUMBER .'           
        echo 'Build Image Completed'                
      }           
    }
    
    stage('Login to Docker Hub') {         
      steps{                            
	sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'                 
	echo 'Login Completed'                
      }           
    } 
                  
    stage('Push Image To Docker Hub') {         
      steps{                            
	sh 'docker push medelouali/devopscycle-image:1.$BUILD_NUMBER'                 
	echo 'Push Image Completed'       
      }           
    }
    
    stage('Update Manifests and Push to Git') {
            steps {
                script {
                    echo "Current directory: ${pwd()}"
                    def cloneDir = 'DevOpsCycleGitOps'
                     sh "rm -rf ${cloneDir}"
                    if (!fileExists(cloneDir)) {

                        sh "git clone git@github.com:DevOpsTestOrgAi/DevOpsCycleGitOps.git ${cloneDir}"
                    }

                    gitConfigure()

                    def manifestsDir = "${cloneDir}"
                    def newImageLine = "image: devopscycle-image:${BUILD_NUMBER}"

                    echo "File content before sed:"
                    sh "cat ${manifestsDir}/app-deployment-manifest.yaml"

                    sh "sed -i 's|image: medelouali/devopscycle-image:latest.*|${newImageLine}|' ${manifestsDir}/app-deployment-manifest.yaml || echo 'sed command failed'"

                    echo "File content after sed:"
                    sh "cat ${manifestsDir}/app-deployment-manifest.yaml"

                    withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                        dir(cloneDir) {
                            sh "git status"
                            sh "git add ."
                            sh "git status" // Print status again to verify changes
                            sh "git commit -m 'Update image tag in Kubernetes manifests'"
                            sh "git pull"
                           sh "git push https://${GITHUB_TOKEN}@github.com:DevOpsTestOrgAi/DevOpsCycleGitOps.git HEAD:main" //ssa
                        }
                    }

                    sh "rm -rf ${cloneDir}"
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
