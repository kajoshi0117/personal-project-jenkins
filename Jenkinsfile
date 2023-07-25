// Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
def versionNum
def rcNum
def properties

pipeline {
    agent any
    stages {
        // stage ('Checkout') {
        //     checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[credentialsId: '2ecf4603-d741-4681-9d31-8fa6a6742d35', url: 'https://github.com/kajoshi0117/personal-project-jenkins.git']]]
        // }
        
        stage('build') {
            agent any
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
            agent any
            environment {
                GH_TOKEN = credentials('6e2096c7-744f-48aa-bd8f-ce5e820e6327')
            }
            steps{
                sh "ls -a"
                sh "pwd"
                echo "${env.WORKSPACE}"
                script {
                    properties = readFile(file: 'version.properties')
                    propertiesArray = properties.split("\n")
                    versionNum = propertiesArray[0].substring(12)
                    def rcNumText = propertiesArray[1].substring(9)
                    rcNum = rcNumText.toInteger()
                    // println("Data type of rcNum: " + rcNum.getClass())
                    

                //------Attempting to read in properties-----------
                //     Properties properties = new Properties()
                //     File propertiesFile = new File("${env.WORKSPACE}/version.properties")
                //     propertiesFile.withInputStream {
                //         properties.load(it)
                //     }
                //     versionNum = properties.version
                //     rcNum = properties.rcNumAmt
                    rcNum = rcNum + 1
                }
            //--------Creating the release in github, which is working---------------
            echo "${propertiesArray}"
            echo "Version Number: ${versionNum}"
            echo "RC Number: ${rcNum}"
            // sh "gh release create v${versionNum}.${rcNum} --title ${versionNum}.${rcNum} --prerelease"
            
            //--------Update rcNumAmt in properties file and commit to repo--------------
            script{
                //Prepare the text to write
                def updateRcNumAmt = "rcNumAmt=" + rcNum
                properties = properties.replaceAll(~"rcNumAmt=[0-9]+",updateRcNumAmt)
                println("Updated RC Amount:" + updateRcNumAmt)
            }
            echo "New Properties:\n ${properties}"
            writeFile file: "version.properties", text: properties
            sh "git add version.properties; git commit -m \"Incrementing RC Amount\""
            sh "git push origin HEAD:main"
            echo "Release tagged in github at https://github.com/kajoshi0117/personal-project-jenkins/releases/tag/v${versionNum}.${rcNum}"
        }
    }
}
}

