pipeline {
    agent any
    stages {    
        stage('build & test')
        {
            steps {
                echo 'TODO: build & test'
                //sh "./mvnw clean compile -e"
            }
        }
        stage('sonar')
        {
            steps {
                echo 'sonar'
               // withSonarQubeEnv(credentialsId: 'SoniToken', installationName: 'Sonita') { // You can override the credential to be used
            //sh './mvnw org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }
        }        
        stage('run') 
        {
            steps {
                echo 'TODO: run'
                //sh "./mvnw clean package -e"
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


