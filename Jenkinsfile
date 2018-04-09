#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                echo 'Cleaning Project'
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        stage('Setup') {
            steps {
                echo 'Setting up Workspace'
                sh './gradlew setupdecompworkspace'
            }
        }
        stage('Build and Deploy') {
            steps {
                echo 'Building and Deploying to Maven'
                script {
                    if (env.BRANCH_NAME.contains("dev")) {
                        sh './gradlew build -Pbranch=SNAPSHOT uploadArchives'
                    } else if (!env.BRANCH_NAME.contains("dev")) {
                        sh './gradlew build uploadArchives'
<<<<<<< HEAD
                    } else {
                        sh './gradlew build -Pbranch=' + env.BRANCH_NAME + ' uploadArchives'
=======
>>>>>>> 1.12.x
                    }
                }
            }
        }
    }
    post {
        always {
            archive 'build/libs/**.jar'
        }
    }
}