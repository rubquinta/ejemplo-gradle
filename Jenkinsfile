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
        string(name: 'installationName', defaultValue: 'sonita', description: 'Nombre instalacion Sonarqube')
        string(name: 'credentialsId', defaultValue: 'SoniToken3', description: 'Credencial con TOken y configurada con Sonarqube')
        choice choices: ['gradle', 'maven'], description: 'which tool would you use', name: 'Build_tool'
        }   
    stages {    

        stage('Loading Scripts'){
            steps{
                script{
                    mvn_script = load "maven.groovy"
                    gradle_script = load "gradle.groovy"
                }
            }
        }
        stage('build-mvn'){  
            when {
                expression {params.Build_tool == 'maven'}
            }
            steps{
                script{
                    mvn_script.maven_build_test()
                }     
            }     
            
        }
        stage('build.gradle'){
            when {
                expression {params.Build_tool == 'gradle'}
            }
            steps{
                //sh 'gradle build'
                script{
                    gradle_script.gradle_build()
                }             
            }
        }
        stage('Sonar'){ 
            when {
                expression {params.Build_tool == 'gradle'}
            }           
            steps {
                echo 'sonar'
                withSonarQubeEnv(credentialsId: params.credentialsId, installationName: params.installationName) {
                    sh './gradlew -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build sonarqube'
                    //"-Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
                }
            }
        }       
        stage('run'){
            when {
                expression {params.Build_tool == 'gradle'}
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
                expression {params.PushToNexus}
            }
            steps {
                echo 'TODO: nexus'
                nexusPublisher nexusInstanceId: 'nxs01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]], tagName: '0.0.1'
            }
        
        }
    }
}


