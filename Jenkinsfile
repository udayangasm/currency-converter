pipeline {
    agent any

  tools {
        maven '3.9.2'
    }

    stages {
        stage('checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/udayangasm/currency-converter.git']])
            }
        }

        stage('unit-tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('build') {
            steps {
                bat 'mvn clean install'
            }
        }
    }
}
