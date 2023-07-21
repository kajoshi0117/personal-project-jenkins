// Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
def versionNum = "12.0.52"
def rcNum = 1

pipeline {
    agent any
    stages {
        // stage ('Checkout') {
        //     checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[credentialsId: '2ecf4603-d741-4681-9d31-8fa6a6742d35', url: 'https://github.com/kajoshi0117/personal-project-jenkins.git']]]
        // }
        
        stage('build') {
            steps {
                sh 'echo Hello World'
            }
        }
        // stage ('deploy'){
        //     steps{
        //     sh 'javac Main.java'
        //     sh 'java Main'
        //     }
        // }
        stage ('Release'){
            environment {
                GH_TOKEN = credentials('cd364393-33dd-4b1e-aaca-2a1a5f0ec4e4')
            }
            steps{
                script {rcNum = rcNum + 1 }
            echo "Version Number: ${versionNum}"
            echo "RC Number: ${rcNum}"
            sh "gh release create v${versionNum} --title ${versionNum}.${rcNum} --prerelease"
        }
    }
}
}

