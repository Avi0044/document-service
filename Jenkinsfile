@Library('jenkins-shared-library') _

def buildInfo
def jfrogServer
def buildType = "Maven"
def rtMaven

def loadAppConfig(){
    config_file = "jenkins/uat-config.yaml"
    if ((env.BRANCH_NAME =~ "release.*").matches()){
        config_file = "jenkins/prod-config.yaml"
    }
   def data = readYaml(file: config_file)
    return data;
}

pipeline{
    agent{
        label "pnb-maven-linux" 
    }
    tools{
        maven "maven-3.6"
    }
    stages{
        stage("init"){
            steps{
                script{
                    echo "initializing ..."
					configValues = loadAppConfig()
                }
            }
        }
        stage("Artifactory Configuration"){
            when {
                expression {
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
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
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
                }
            }
            steps{
                script{
                    incrementAppVersion(configValues, currentBuild.number, "multiRepo")
                }
            }
        }
        stage("Commit version update"){
            when {
                expression {
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
                }
            }
            steps{
                script{
                    gitCommit(configValues)
                }
            }
        }
        stage("SonarQube code analysis"){
            when {
                expression {
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
                }
            }
            steps{
                script{
                    sonarScan(buildType, configValues)
                }
            }
        }
        stage("SonarQube quality gate"){
            when {
                expression {
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
                }
            }
            steps{
                script{
                    sleep(10)
					sonarQualityGate(false)
                }
            }
        }
        stage("Exec Maven"){
            when {
                expression {
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
                }
            }
            steps{
                script{
                    executeMaven("pom.xml", rtMaven, configValues, buildInfo, "multiRepo")
                }
            }
        }
        stage("Publish build info"){
            when {
                expression {
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
                }
            }
            steps{
                script{
                    jfrogServer.publishBuildInfo buildInfo
                }
            }
        }
        stage("J-Xray scan build"){
            when {
                expression {
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
                }
            }
            steps{
                script{
                    jfrogXrayScan(buildInfo.name, buildInfo.number, false)
                }
            }
        }
        stage("Generate Vulnerability Report"){
            when {
                expression {
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
                }
            }
            steps{
                script{
                    sendVulnerabilityFileToSlack(configValues, buildInfo.name, buildInfo.number, buildType)
                }
            }
        }
        stage("Check Banned License and Create Attribution List"){
            when {
                expression {
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
                }
            }
            steps{
                script{
                    bannedLicenseAndAttribution(configValues, buildType, "multiRepo", "true")
                }
            }
        }
        stage("Upload License file to S3 Bucket"){
            when {
                expression {
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
                }
            }
            steps{
                script{
                    uploadLicensesToS3(configValues)
                }
            }
        }
        stage("Upload License file to Version Control"){
            when {
                expression {
                    configValues.build == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ "release.*").matches())
                }
            }
            steps{
                script{
                    licenseVersionControl(configValues)
                }
            }
        }
        stage("Deploy in UAT"){
            when {
                expression {
                    configValues.deploy == "yes" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd")
                }
            }
            steps{
                script{
                    uatDeploy(configValues, buildType)
                }
            }
        }
        stage("Deploy in Production"){
            when {
                expression {
                    configValues.deploy == "yes" && (env.BRANCH_NAME =~ "release.*").matches()
                }
            }
            steps{
                script{
                    prodDeploy(configValues, buildType)
                }
            }
        }
    }
    
    post {
         always {
             script{
                if (configValues.build == "yes"){
                    sendNotification(configValues, "${buildInfo.name}", "${currentBuild.number}", "${currentBuild.currentResult}")
                }
             }
             cleanWs()
         }
    }
}