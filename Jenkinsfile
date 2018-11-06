import org.apache.commons.io.FileUtils
node {

	def mvnHome
	
	stage('Git Download') { // for display purposes
		// Get some code from a GitHub repository
		git 'https://github.com/nickcjordan/NewFantasy'
		// Get the Maven tool.
		// ** NOTE: This 'M3' Maven tool must be configured in the global configuration.           
		mvnHome = tool 'M3'
	}
	
	stage('Workspace Cleanup') {
		def dbFile = new File("falifa-fantasy-db/")
		if (dbFile.exists()) {
			FileUtils.cleanDirectory(dbFile)
		}
		def uiFile = new File("falifa-fantasy-ui/")
		if (uiFile.exists()) {
			FileUtils.cleanDirectory(uiFile)
		}
		def angularFile = new File("falifa-fantasy-app/")
		if (angularFile.exists()) {
			FileUtils.cleanDirectory(angularFile)
		}
		
		FileUtils.copyFile("build/db/Procfile", "falifa-fantasy-db/Procfile");
		FileUtils.copyFile("build/ui/Procfile", "falifa-fantasy-ui/Procfile");
	}
	
	stage('Build') {
		// Run the maven build
		if (isUnix()) {
			sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean install"
		} else {
			bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean install/)
		}
	}

	stage('Archive Artifacts') {
		archiveArtifacts 'falifa-fantasy-db/*'
		archiveArtifacts 'falifa-fantasy-ui/*'
		archiveArtifacts 'falifa-fantasy-app/*'
	}
     
}