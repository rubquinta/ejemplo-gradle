def mvn_script
def gradle_script

pipeline {
    agent any
    tools{
        gradle 'grdl'
        maven 'maven_jenkins'
    }
    parameters {
        booleanParam description: 'Use nexus uploader to push artifact', name: 'PushToNexus'
        string 'Nexus Url'
        choice choices: ['gradle', 'maven '], description: 'which tool would you use', name: 'Build_tool'
        }   
    stages {    

        stage('Loading Scripts')
        {
            steps{
                script{
                    mvn_script = load "maven.groovy"
                    gradle_Script = load "gradle.groovy"
                }
            }
        }
        stage('build-mvn')
        {  
            when {
                expression {
                    params.Build_tool == 'maven'
                }
            }steps{
                script{
                    mvn_script.maven_build_test()
                }     
            }     
            
        }
        stage('build.gradle'){
            when {
                expression {
                    params.Build_tool == 'gradle'
                }
            }
            steps{
                script{
                    gradle_script.gradle_build()
                }                
            }
        }
        stage('Sonar'){ 
            when {
                expression {
                    params.Build_tool == 'gradle'
                }
            }           
            steps {
                echo 'sonar'
                withSonarQubeEnv(credentialsId: 'SoniToken2') {
                    sh './gradlew -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build sonarqube '
                    //"-Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
                }
            }
        }       
        stage('run') 
        {
            when {
                expression {
                    params.Build_tool == 'gradle'
                }
            }
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
                nexusPublisher nexusInstanceId: 'nxs01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]], tagName: '0.0.1'
            }
        
        }
    }
}


