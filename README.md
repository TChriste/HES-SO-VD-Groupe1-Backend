Quarkus guide: https://quarkus.io/guides/rest-client



##Déploiement sur Google Cloud Platform : 
(https://quarkus.io/guides/deploying-to-google-cloud)

###Configuration
1. Créer un compte Google Cloud
2. Installer le Cloud SDK CLI (https://cloud.google.com/sdk/docs/quickstart)
3. Initialisation du SDK (gcloud init)
4. A la racine du projet exécuter
`cloud auth login`
   
### Déploiement
1. Exécuter la commande
`/Library/google-cloud-sdk/bin/gcloud app deploy target/rest-client-quickstart-1.0.0-SNAPSHOT-runner.jar`