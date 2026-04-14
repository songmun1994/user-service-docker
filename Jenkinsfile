pipeline {
    agent any

    tools {
        jdk 'Java17'
    }

    environment {
        DOCKERHUB_USERNAME = 'munhyeoksong'
        IMAGE_NAME = 'user-service-docker'
        DOCKERHUB_CREDENTIALS_ID = 'dockerhub-credentials'
        PROJECT_NAME = "user-docker-service"
        // 1.0.14 같은 형식으로 저장됩니다.
        VERSION = "1.0.${env.BUILD_NUMBER}"
    }

    stages {
        stage('Initialize & Clean') {
            steps {
                cleanWs()
                script {
                    currentBuild.displayName = "${env.PROJECT_NAME}_v${env.VERSION}"
                }
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build -x test'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Docker Image Build') {
            steps {
                echo "Building Docker Image: ${env.DOCKERHUB_USERNAME}/${env.IMAGE_NAME}:${env.VERSION}"
                sh "docker build -t ${env.DOCKERHUB_USERNAME}/${env.IMAGE_NAME}:${env.VERSION} ."
                sh "docker tag ${env.DOCKERHUB_USERNAME}/${env.IMAGE_NAME}:${env.VERSION} ${env.DOCKERHUB_USERNAME}/${env.IMAGE_NAME}:latest"
            }
        }

        stage('Docker Push') {
            steps {
                echo "Pushing Image to Docker Hub..."
                withCredentials([usernamePassword(credentialsId: "${env.DOCKERHUB_CREDENTIALS_ID}", passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                    sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
                    sh "docker push ${env.DOCKERHUB_USERNAME}/${env.IMAGE_NAME}:${env.VERSION}"
                    sh "docker push ${env.DOCKERHUB_USERNAME}/${env.IMAGE_NAME}:latest"
                }
            }
        }
    }
}