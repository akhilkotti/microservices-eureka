pipeline {
    agent any

    // Define polling trigger in Jenkinsfile
    triggers {
        pollSCM('H/5 * * * *')  // Poll every 5 minutes
    }
    
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
        
        // Conditional Test stage - only runs if tests exist
        stage('Test') {
            when {
                expression { 
                    fileExists('**/src/test/java') 
                }
            }
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    // Skip if no test reports found
                    junit(
                        allowEmptyResults: true, 
                        testResults: '**/target/surefire-reports/*.xml'
                    )
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
