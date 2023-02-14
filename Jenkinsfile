// Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'echo Hello World'
            }
        }
        stage ('deploy'){
            steps{
            sh 'javac Main.java'
            sh 'java Main.java'
            }
        }
    }
}
