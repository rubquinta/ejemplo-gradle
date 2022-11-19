def maven_build_test(){  
            steps{
                sh './mvn clean install -e'
            }
}

return this;