def gradle_build(){
    stage{
        sh 'gradle build'
    }
}

def gradle_run(){
    stage{
        sh 'gradle bootRun'
    }
}

def gradle_test(){
    stage{
        sh 'Test: curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
    }
}

return this;