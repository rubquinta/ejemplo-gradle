def maven_build_test(){  
        sh './mvnw clean install -e'
            
}

return this;