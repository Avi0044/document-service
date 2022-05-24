@Library('jenkins-shared-library') _
    
def buildInfo
def jfrogServer
def buildType = "Maven"
def rtMaven

def loadAppConfig(){
   def data = readYaml(file: "jenkins/config.yaml")
    return data;
}

pipeline{
    agent any
    tools{
            maven 'maven-3.6'
        }
    stages{
        stage("init"){
            steps{
                script{
                    echo "initializing ..."
					configValues = loadAppConfig()
					mavenProfile = configValues.staging.mavenProfile
					mavenExtraParams = configValues.staging.mavenExtraParams
                }
            }
        }
        stage("Artifactory Configuration"){
            when {
                expression {
                    configValues.staging.build == "yes"  && (env.BRANCH_NAME == "develop"  || env.BRANCH_NAME == "feature/ci-cd" )
                }
            }
            steps{
                script{
                    (jfrogServer, rtMaven, buildInfo) = jfrogConfig(configValues, buildType)
                }
            }
        }
        stage("Increment app version"){
            when {
                expression {
                    configValues.staging.build == "yes"  && (env.BRANCH_NAME == "develop"  || env.BRANCH_NAME == "feature/ci-cd" )
                }
            }
            steps{
                script{
                    incrementAppVersion(configValues, currentBuild.number, "isMonorepo")
                }
            }
        }
        stage("Commit version update"){
            when {
                expression {
                    configValues.staging.build == "yes"  && (env.BRANCH_NAME == "develop"  || env.BRANCH_NAME == "feature/ci-cd" )
                }
            }
            steps{
                script{
                    gitCommit(configValues)
                }
            }
        }
        // stage("SonarQube code analysis"){
        //     when {
        //         expression {
        //             configValues.staging.build == "yes"  && (env.BRANCH_NAME == "develop"  || env.BRANCH_NAME == "feature/ci-cd" )
        //         }
        //     }
        //     steps{
        //         script{
        //             mavenParams = "${mavenProfile} ${mavenExtraParams}"
		// 			sonarScan(buildType, configValues.staging.sonarqubeProjectKey, mavenParams)
        //         }
        //     }
        // }
        // stage("SonarQube quality gate"){
        //     when {
        //         expression {
        //             configValues.staging.build == "yes"  && (env.BRANCH_NAME == "develop"  || env.BRANCH_NAME == "feature/ci-cd" )
        //         }
        //     }
        //     steps{
        //         script{
        //             sleep(10)
		// 			sonarQualityGate(false)
        //         }
        //     }
        // }
        stage("Exec Maven"){
            when {
                expression {
                    configValues.staging.build == "yes"  && (env.BRANCH_NAME == "develop"  || env.BRANCH_NAME == "feature/ci-cd" )
                }
            }
            steps{
                script{
                    mavenParams = "${mavenProfile} ${mavenExtraParams}"
					executeMaven("pom.xml", rtMaven, mavenParams, configValues, buildInfo, "isMonorepo")
                }
            }
        }
        stage("Publish build info"){
            when {
                expression {
                    configValues.staging.build == "yes"  && (env.BRANCH_NAME == "develop"  || env.BRANCH_NAME == "feature/ci-cd" )
                }
            }
            steps{
                script{
                    jfrogServer.publishBuildInfo buildInfo
                }
            }
        }
        // stage("J-Xray scan build"){
        //     when {
        //         expression {
        //             configValues.staging.build == "yes"  && (env.BRANCH_NAME == "develop"  || env.BRANCH_NAME == "feature/ci-cd" )
        //         }
        //     }
        //     steps{
        //         script{
        //             jfrogXrayScan(buildInfo.name, buildInfo.number, false)
        //         }
        //     }
        // }
        // stage("Generate Vulnerability Report"){
        //     when {
        //         expression {
        //             configValues.staging.build == "yes"  && (env.BRANCH_NAME == "develop"  || env.BRANCH_NAME == "feature/ci-cd" )
        //         }
        //     }
        //     steps{
        //         script{
        //             sendVulnerabilityFileToSlack(configValues, buildInfo.name, buildInfo.number, buildType)
        //         }
        //     }
        // }
        stage("Deploy in UAT"){
            when {
                expression {
                    configValues.staging.deploy == "yes"  && (env.BRANCH_NAME == "develop"  || env.BRANCH_NAME == "feature/ci-cd" )
                }
            }
            steps{
                script{
                    uatDeployForMonorepo(configValues, buildType)
                }
            }
        }
    }
    
    post {
         always {
            //  script{
            //     if (configValues.staging.build == "yes"){
            //         emailNotify("${configValues.staging.teamEmailId}")
            //         sendNotification(configValues, "${buildInfo.name}", "${currentBuild.number}", "${currentBuild.currentResult}")
            //     }
            //  }
             cleanWs()
         }
    }
}