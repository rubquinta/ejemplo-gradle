def mvn_script
def gradle_script

pipeline {
    agent any
    tools{
        gradle 'grdl'
        maven 'maven_jenkins'
    }
    parameters{
        choice(name: 'Build_Tool', choices: ['gradle', 'maven'], description: '')
        booleanParam(name: 'PushToNexus', defaultValue: false, description: '')
    }
    stages {    
        stage('Loading Scripts'){
            steps{
                script{
                    mvn_script = load "maven.groovy"
                    gradle_Script = load "gradle.groovy"
                }
            }
        }
        stage('build-mvn'){  
            when {
                expression {
                    params.Build_Tool == 'maven'
                }
            }
            script{
                mvn_script.maven_build_test()
            }          
            
        }
        stage('build.gradle'){
            when {
                expression {
                    params.Build_Tool == 'gradle'
                }
            }
            steps{
                gradle_script.gradle_build()
            }
        }
        /*stage('Sonar'){            
            //steps {
                //echo 'sonar'
                //withSonarQubeEnv(credentialsId: 'SoniToken') {
                    -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build
                }
            }
        }  */      
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
        stage('pushToNexus'){
            when {
                expression {
                    params.PushToNexus
                }
            }
            steps {
                echo 'TODO: nexus'
            }
        
        }
    }
}


