pipeline {
    agent any
    
    environment {
        // Define environment variables
        DOCKER_HUB_CREDENTIALS = credentials('dckr_pat_neDIl-qYI_FitaxBN3PIcc4Z_GM')
        DOCKER_IMAGE_NAME = 'medelouali/devopscycle-image'
        MAVEN_HOME = tool 'Maven'
    }
    
    stages {
        stage('Checkout Source Code') {
                        steps {
                            script {
        //                         deleteDir() // Optional: clean workspace before checkout
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
                sh "sudo docker build -t ${DOCKER_IMAGE_NAME}:${BUILD_NUMBER} ."
                echo 'Build Image Completed'
            }
        }


        stage('Login to Docker Hub') {
            steps {
                sh "echo ${DOCKER_HUB_CREDENTIALS_PSW} | sudo docker login -u ${DOCKER_HUB_CREDENTIALS_USR} --password-stdin"
                echo 'Login Completed'
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                sh "sudo docker push ${DOCKER_IMAGE_NAME}:${BUILD_NUMBER}"
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
