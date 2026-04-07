pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Git에서 코드를 가져오는 단계
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // 프로젝트 빌드 (예: Spring Boot라면 ./gradlew build)
                sh './gradlew clean build -x test'
            }
        }
        stage('Test') {
            steps {
                // 테스트 코드 실행
                sh './gradlew test'
            }
        }
    }
}