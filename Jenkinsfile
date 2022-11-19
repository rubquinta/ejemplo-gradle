pipeline {
    agent any
    tools{
        gradle 'grdl'
    }
    parameters{
        choice(name: 'Build_Tool', choices: [], description: '')
        booleanParam(name: 'PushToNexus', defaultValue: false, description: '')
    }
    stages {    
        stage('build & test')
        {
            steps {
                echo 'TODO: build & test'
                sh 'gradle build'
            }
        }
        stage('sonar')
            when {
                expression {
                    params.PushToNexus
                }
            }
        {
            steps {
                echo 'sonar'
                withSonarQubeEnv(credentialsId: 'SoniToken') {
                    -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build
                }
            }
        }        
        stage('run') 
        {
            steps {
                echo 'TODO: run'
                sh 'gradle bootRun'
            }
        } 
        stage('testing'){
            steps {
                echo 'TODO: testing'
                sh 'curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
            }
        }
        stage('nexus') 
        {
            steps {
                echo 'TODO: nexus'
            }
        
        }
    }
}


