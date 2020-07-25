pipeline {
  agent any
  stages {
    stage('Run on Dev') {
      parallel {
        stage('Run on Dev') {
          steps {
            sh 'echo "Run on Dev"'
          }
        }

        stage('chrome') {
          steps {
            sh 'echo "Run it on chrome"'
          }
        }

      }
    }

    stage('Run on QA') {
      parallel {
        stage('Run on QA') {
          steps {
            sh 'echo "Run on QA"'
          }
        }

        stage('firefox') {
          steps {
            sh 'echo "Run it on firefox"'
          }
        }

        stage('chrome') {
          steps {
            sh 'echo "Run it on chrome"'
          }
        }

      }
    }

    stage('Run on Stage') {
      parallel {
        stage('Run on Stage') {
          steps {
            sh 'echo "Run on Stage'
          }
        }

        stage('chrome') {
          steps {
            sh 'echo "Run it on chrome"'
          }
        }

        stage('firefox') {
          steps {
            sh 'echo "Run it on firefox"'
          }
        }

        stage('safari') {
          steps {
            sh 'echo "Run it on safari"'
          }
        }

      }
    }

    stage('Run on Prod') {
      parallel {
        stage('Run on Prod') {
          steps {
            sh 'echo "Run on Prod"'
          }
        }

        stage('chrome') {
          steps {
            sh 'echo "Run it on chrome"'
          }
        }

      }
    }

  }
}