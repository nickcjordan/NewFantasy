import org.apache.commons.io.FileUtils
node {

	def mvnHome
	
	stage('Git Download') { 
		git 'https://github.com/nickcjordan/NewFantasy'
		mvnHome = tool 'M3'
	}
	
	stage('Build') {
		if (isUnix()) {
			sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean install"
		} else {
			bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean install/)
		}
	}

	stage('Archive Artifacts') {
		archiveArtifacts 'falifa-fantasy-db/*'
		archiveArtifacts 'falifa-fantasy-app/*'
	}
     
}