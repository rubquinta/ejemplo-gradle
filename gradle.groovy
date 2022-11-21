def gradle_build(){
    
        sh 'gradle build'
    
}

def gradle_run(){
    
        sh 'gradle bootRun'
    
}

def gradle_test(){
    
        sh 'Test: curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
    
}

return this;