pipeline {
    agent any
    tools{
        maven 'MAVEN_HOME'
        jdk 'SMARTAPPS_JAVA_HOME'
    }
    stages{
        stage('Stage 1 - Checkout code'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Shylendra/SmartProfile']])
            }
        }
        stage('Stage 2 - Build Maven'){
            steps{
                bat 'mvn clean install -Dactive.profile=dev'
            }
        }
        stage('Stage 3 - Build docker image'){
            steps{
                bat 'docker build -t shylendra2015/smartprofile-api:latest .'
            }
        }
        stage('Stage 4 - Login and Push image to Dockerhub'){
            steps{
              	withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                	bat "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                  bat 'docker push shylendra2015/smartprofile-api:latest'
                }            
            }
        }
        stage('Stage 5 - Deploy to k8s'){
            steps{
                script{
                    kubernetesDeploy (configs: 'deployment.yaml', kubeconfigId: 'KubernetesConfigPwd')
                }
            }
        }
   }
    post {
        always {
            bat 'docker logout'
        }
    }
}