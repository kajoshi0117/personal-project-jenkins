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
        
        // stage('build') {
        //     agent any
        //     environment {
        //         GH_TOKEN = credentials('027f6170-6a85-41d5-bcc6-89e8d8417a98')
        //     }
        //     steps {
        //         sh '''
        //         git checkout -b 12.0.54
        //         echo \"rcNumAmt=1\" > rc_num_amt.txt
        //         ls -a
        //         git status
        //         git add rc_num_amt.txt; git commit -m \"rc num amt text file created\"
        //         git push origin HEAD:refs/heads/12.0.54
        //         gh release create v12.0.54.0 --title \"12.0.54.0\" --prerelease
        //         '''
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
                    //Version name from properties
                    properties = readFile(file: 'version.properties')
                    propertiesArray = properties.split("\n")
                    versionNumText = propertiesArray[0]
                    versionNumText = versionNumText =~ /[0-9]+\.[0-9]+\.[0-9]+/
                    versionNumText = versionNumText.findAll()
                    versionNum = versionNumText[0]

                    //Rc Num amount
                    def rcNumText = readFile(file: 'rc_num_amt.txt')
                    // rcNumText = rcNumText.substring(9)
                    rcNumText = rcNumText =~ /[0-9]+/
                    rcNumText = rcNumText.findAll()
                    rcNum = rcNumText[0]
                    rcNum = rcNum.toInteger()
                    rcNum = rcNum + 1
                }
            //--------Creating the release in github, which is working---------------
            echo "Version Number: ${versionNum}"
            echo "RC Number: ${rcNum}"
            sh "gh release create v${versionNum}.${rcNum} --title ${versionNum}.${rcNum} --prerelease"
            
            //--------Update rcNumAmt in text file and commit to repo--------------
            script{
                //Prepare the text to write and update rcNumAmt
                updateRcNumAmt = "rcNumAmt=" + rcNum
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

