def gradle_build(){
    steps{
        sh 'gradle build'
    }
}

def gradle_run(){
    steps{
        sh 'gradle bootRun'
    }
}

def gradle_test(){
    steps{
        sh 'Test: curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
    }
}

return this;