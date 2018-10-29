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
        def file = new File("falifa-fantasy-bundle/")
        if (file.exists()) {
            FileUtils.cleanDirectory(file)
        }
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
       archiveArtifacts 'falifa-fantasy-bundle/*'
       archiveArtifacts 'falifa-fantasy-app/*'
    //   archiveArtifacts '**/target/*.jar'
    //   archiveArtifacts 'Procfile'
   }
    // stage('Copy Archive') {
    //      script {
    //          step ([$class: 'CopyArtifact',
    //          projectName: 'Falifa-Fantasy_Deploy-AWS',
    //          filter: "NewFantasyUI/target/NewFantasyUI-0.0.1-SNAPSHOT.jar",
    //          target: 'deploy/NewFantasyUI-0.0.1-SNAPSHOT.jar']);
    //      }
    //      script {
    //          step ([$class: 'CopyArtifact',
    //          projectName: 'Falifa-Fantasy_Deploy-AWS',
    //          filter: "DatabaseManager/target/DatabaseManager-0.0.1-SNAPSHOT.jar",
    //          target: 'deploy/DatabaseManager-0.0.1-SNAPSHOT.jar']);
    //      }
    //      script {
    //          step ([$class: 'CopyArtifact',
    //          projectName: 'Falifa-Fantasy_Deploy-AWS',
    //          filter: "Procfile",
    //          target: 'deploy/Procfile']);
    //      }
    // }
     
}