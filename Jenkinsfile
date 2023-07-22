// Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
def versionNum
def rcNum

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
                GH_TOKEN = credentials('6e2096c7-744f-48aa-bd8f-ce5e820e6327')
            }
            steps{
                sh "ls -a"
                sh "pwd"
                sh "echo ${env.WORKSPACE}"
                script {
                    Properties properties = new Properties()
                    File propertiesFile = new File("${env.WORKSPACE}/version.properties")
                    propertiesFile.withInputStream {
                        properties.load(it)
                    }
                    versionNum = properties.version
                    rcNum = properties.rcNumAmt
                    rcNum = rcNum + 1
                }
            echo "Version Number: ${versionNum}"
            echo "RC Number: ${rcNum}"
            sh "gh release create v${versionNum}.${rcNum} --title ${versionNum}.${rcNum} --prerelease"
        }
    }
}
}

