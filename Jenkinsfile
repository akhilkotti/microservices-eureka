pipeline {
    agent any

    tools {
        jdk 'jdk21'  // Match Jenkins JDK configuration name
        maven 'maven' // Match Jenkins Maven configuration name
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                url: 'https://github.com/akhilkotti/microservices-eureka.git'
            }
        }
        
        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        
        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
    }
}
