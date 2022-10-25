@Library('jenkins-shared-library@library/v4') _

def buildInfo
def jfrogServer
def buildType = "Maven"
def rtMaven
def getConfirmation = "NO"

def loadAppConfig(String configType, String targetEnv){
    config_file = "jenkins/build-config.yaml"
    deploy_file = "jenkins/uat-deploy-config.yaml"

    if (params.ENVIRONMENT == "PROD"){
        deploy_file = "jenkins/prod-deploy-config.yaml"
    }
    if (configType == 'deploy'){
        return readYaml(file: deploy_file)
    }
    return readYaml(file: config_file);
}

pipeline{
    
        parameters {
        choice(name: "ENVIRONMENT", choices: ["UAT", "PROD"])
        string(name: "ARTIFACT_VERSION",
                      description: "Enter the artifact version (Should be QA verified in case of PROD)",
                      defaultValue: "latest")
    }
    
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
					buildConfig = loadAppConfig("build", params.ENVIRONMENT)
					deployConfig = loadAppConfig("deploy", params.ENVIRONMENT)
					deployConfig.deployBuildNumber = params.ARTIFACT_VERSION
					println "###################################################"
					println "Jenkins running for environment: ${params.ENVIRONMENT}"
					println "###################################################"
                }
            }
        }
        stage("Artifactory Configuration"){
            when {
                expression {
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    (jfrogServer, rtMaven, buildInfo) = jfrogConfig(buildConfig, buildType)
                }
            }
        }
        stage("Increment app version"){
            when {
                expression {
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    incrementAppVersion(buildConfig, currentBuild.number, "multiRepo")
                }
            }
        }
        stage("Commit version update"){
            when {
                expression {
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    gitCommit(buildConfig)
                }
            }
        }
        /* stage("SonarQube code analysis"){
            when {
                expression {
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    sonarScan(buildType, buildConfig)
                }
            }
        } */
        /* stage("SonarQube quality gate"){
            when {
                expression {
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    sleep(10)
					sonarQualityGate(false)
                }
            }
        } */
        stage("Exec Maven"){
            when {
                expression {
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    executeMaven("pom.xml", rtMaven, buildConfig, buildInfo, "multiRepo")
                }
            }
        }
        stage("Publish build info"){
            when {
                expression {
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
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
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
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
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    sendVulnerabilityFileToSlack(buildConfig, buildInfo.name, buildInfo.number, buildType)
                }
            }
        }
        stage("Check Banned License and Create Attribution List"){
            when {
                expression {
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    bannedLicenseAndAttribution(buildConfig, buildType, "${buildInfo.name}", "${currentBuild.number}", "multiRepo", "true")
                }
            }
        }
        stage("Upload License file to S3 Bucket"){
            when {
                expression {
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    uploadLicensesToS3(buildConfig)
                }
            }
        }
        stage("Upload License file to Version Control"){
            when {
                expression {
                    buildConfig.build == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    licenseVersionControl(buildConfig)
                }
            }
        }
        stage("Deploy in UAT"){
            when {
                expression {
                    deployConfig.deploy == "yes" && params.ENVIRONMENT == "UAT" && (env.BRANCH_NAME == "develop" || env.BRANCH_NAME == "feature/ci-cd")
                }
            }
            steps{
                script{
                    uatDeploy(buildConfig, deployConfig, buildType)
                }
            }
        }
        stage("Wait for input (in case of prod)"){
            when {
                expression {
                    deployConfig.deploy == "yes" && params.ENVIRONMENT == "PROD" && (env.BRANCH_NAME == "develop" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    getConfirmation = input(id: 'userInput', message: "This will deploy build ${deployConfig.deployBuildNumber}, Continue?",
					     parameters: [[$class: 'ChoiceParameterDefinition', name:'userInput', choices: "NO\nYES"]])
                }
            }
        }
        stage("Deploy in Production"){
            when {
                expression {
                    getConfirmation == "YES" && deployConfig.deploy == "yes" && params.ENVIRONMENT == "PROD" && (env.BRANCH_NAME == "develop" || (env.BRANCH_NAME =~ ".*hotfix.*").matches())
                }
            }
            steps{
                script{
                    prodDeploy(buildConfig, deployConfig ,buildType)
                }
            }
        }
    }
    
    post {
         always {
             script{
                if (buildConfig.build == "yes" && params.ENVIRONMENT == "UAT"){
                    sendNotification(buildConfig, "${buildInfo.name}", "${currentBuild.number}", "${currentBuild.currentResult}")
                }
             }
             cleanWs()
         }
    }
}