pipeline{
	
	agent any 
	stages{
		stage ('Checkout GIT'){
			steps{
				echo 'Pulling...';
					git branch: 'master',
					url : 'https://github.com/GuebliMed/Timesheet_DevOps.git';
			}
		}

		
		stage ("verification version Maven"){
			steps{
				bat """mvn -version"""
			}
		}

		stage ("Clean"){
			steps{
				bat """mvn clean"""
			}
		}

		stage ("Creation du livrable"){
			steps{
				bat """mvn package"""
			}
		}

		stage ("Lancement des Tests Unitaires"){
			steps{
				bat """mvn test"""
			}
		}

		stage ("Analyse avec Sonar"){
			steps{
				bat """mvn sonar:sonar"""
			}
		}

		stage ("Deploiement"){
			steps{
				bat """mvn clean package -Dmaven.test.skip=true -Dmaven.test.failure.ignore=true deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet_DevOps -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet_DevOps-1.0.jar"""
			}
		}

		

		

		
	}

	post{
		success{
			emailext body: 'Build success', subject: 'Jenkins', to:'mohamed.guebli1@esprit.tn'
		}
		failure{
			emailext body: 'Build failure', subject: 'Jenkins', to:'mohamed.guebli1@esprit.tn'
		}

	}
}
//
