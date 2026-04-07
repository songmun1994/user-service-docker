pipeline {
    agent any

    tools {
        jdk 'Java17'
    }

    stages {
        stage('Checkout') {
            steps {
                // Git에서 코드를 가져오는 단계
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
                // 테스트 코드 실행
                sh './gradlew test'
            }
        }
    }
}