pipeline {
    agent any
    environment {
        CC = "clang"
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
