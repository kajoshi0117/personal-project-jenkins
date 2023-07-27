// Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
def versionNum
def rcNum
def properties
env.build_type = 'Dev'
def updateRcNumAmt

pipeline {
    agent any
    stages {
        // stage ('Checkout') {
        //     checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[credentialsId: '2ecf4603-d741-4681-9d31-8fa6a6742d35', url: 'https://github.com/kajoshi0117/personal-project-jenkins.git']]]
        // }
        
        // stage('build') {
        //     agent any
        //     steps {
        //         sh '''
        //         echo Hello World
        //         echo \"rcNumAmt=3\" > rc_num_amt.txt
        //         ls -a
        //         git status
        //         git add rc_num_amt.txt; git commit -m "rc num amt text file created"
        //         git push origin HEAD:main
        //         '''
        //     }
        // }
        // stage ('deploy'){
        //     steps{
        //     sh 'javac Main.java'
        //     sh 'java Main'
        //     }
        // }
        stage ('Release'){
            agent any
            environment {
                GH_TOKEN = credentials('027f6170-6a85-41d5-bcc6-89e8d8417a98')
            }
            steps{
                sh "ls -a"
                sh "pwd"
                echo "${env.WORKSPACE}"
                script {
                    properties = readFile(file: 'version.properties')
                    propertiesArray = properties.split("\n")
                    versionNum = propertiesArray[0].substring(12)

                    //Rc Num amount
                    def rcNumText = readFile(file: 'rc_num_amt.txt')
                    // rcNumText = rcNumText.substring(9)
                    rcNumText = rcNumText =~ /[0-9]+/
                    rcNumText = rcNumText.findAll()
                    rcNum = rcNumText[0]
                    rcNum = rcNum.toInteger()
                    println(rcNum)
                    // rcNum = 1

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
            sh "gh release create v${versionNum}.${rcNum} --title ${versionNum}.${rcNum} --prerelease"
            
            //--------Update rcNumAmt in properties file and commit to repo--------------
            script{
            // //     //Prepare the text to write and update rcNumAmt
                updateRcNumAmt = "rcNumAmt=" + rcNum
            // //     properties = properties.replaceAll(~"rcNumAmt=[0-9]+",updateRcNumAmt)
            // //     println("Updated RC Amount: " + rcNum)
            }
            // // echo "New Properties:\n${properties}"
            writeFile file: "rc_num_amt.txt", text: updateRcNumAmt
            sh "git add rc_num_amt.txt; git commit -m \"Incrementing RC Amount\""
            sh "git push origin HEAD:main"
            echo "Release tagged in github at https://github.com/kajoshi0117/personal-project-jenkins/releases/tag/v${versionNum}.${rcNum}"
        } 
        
    }
}
}

