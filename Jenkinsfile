pipeline {
    agent any
    environment {
        // Using returnStdout
        CC = """${sh(
                returnStdout: true,
                script: 'echo "clang"'
            )}""" 
        // Using returnStatus
        EXIT_STATUS = """${sh(
                returnStatus: true,
                script: 'exit 1'
            )}"""
    }

    stages {
        stage('Example Stage1') {
            steps {
                bat 'mvn --version'
                echo "Env: ${env.CC}"
            }
        }
    }
}
