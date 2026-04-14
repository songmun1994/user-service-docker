pipeline {
    agent any
    environment {
            PROJECT_NAME = "user-docker-service"
            VERSION = "1.0.${env.BUILD_NUMBER}" // 빌드 번호를 버전에 포함
    }

    tools {
        jdk 'Java17'
    }

    environment {
        DOCKERHUB_USERNAME = 'munhyeoksong'
        IMAGE_NAME = 'user-service-docker'
        DOCKERHUB_CREDENTIALS_ID = 'dockerhub-credentials'
    }

    stages {
        stage('Clean Workspace') {
                steps {
                    cleanWs() // 워크스페이스를 깨끗하게 비워줍니다.
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
                echo "Building Docker Image: ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${env.BUILD_NUMBER}"
                sh "docker build -t ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${env.BUILD_NUMBER} ."
                sh "docker tag ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${env.BUILD_NUMBER} ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:latest"
            }
        }

        stage('Docker Push') {
            steps {
                echo "Pushing Image to Docker Hub..."
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                    sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
                    sh "docker push ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${env.BUILD_NUMBER}"
                    sh "docker push ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:latest"
                }
            }
        }
    }
}