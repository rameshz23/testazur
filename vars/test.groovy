def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    repository_url = 'https://github.com/rameshz23/vm-jenk.git'
    pipeline {
        environment {
          gitCredentialID='ram'
        }

	pipeline {
            options {
                buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
                }

	agent any
        stages {
            stage('Clone') {
                steps {
                    echo "this should be skipped, but it does not ("
		    println "git checkout"

                  git([
                    url: 'https://github.com/rameshz23/vm-jenk.git',
                    credentialsId: env.gitCredentialID,
                    branch: 'master'
                  ])
                build.git()
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
}
