// Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
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
            steps{
            def props = readProperties file: './version.properties'
            def versionNum = props['version']
            def rcNum = props['rcNum']
            echo "Version Number: ${versionNum}"
            echo "RC Number: ${rcNum}"
            sh "gh release create v${versionNum} --title ${versionNum}.${rcNum} --prerelease"
            }
        }
    }
}
