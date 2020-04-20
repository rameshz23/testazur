def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    jobID= "fa6f7c2ae9364ad39ce13b015e444c6b"
	pipeline {
            options {
                buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
                }
	       
	agent any
        stages {
            stage('Clone') {
                steps {
                    echo "this should be skipped, but it does not ("
                }
            }
            
           stage ('FTP') {
		agent{
			docker { image 'aequitas/ftp-resource' }
		}
            	steps {
		      sh """ env > envfile
		      cat envfile """// log.info 'Starting'
		      script { 
		               echo USER //log.info 'Starting'
		               echo SHELL //log.warning 'Nothing to do!'
		  	       //currentBuild.displayName = "$env.NEW_BUILDNUMBER"
		       }
		}
	    }
		
     }
  }
}
