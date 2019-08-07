# gcp-demo-project

[![Join the chat at https://gitter.im/gcp-demo-project/community](https://badges.gitter.im/gcp-demo-project/community.svg)](https://gitter.im/gcp-demo-project/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Project for gcp demo

## Google Compute Engine
This code is example to try Google Compute Engine. **To run it you should have gitter development access.**

First, we’ll need to create new cloud runtime configuration *(If Cloud Runtime Configuration API isn't enabled, you can find out how to do it in the branch "cloud_shell")*
```
gcloud beta runtime-config configs create gitterchat_compute_engine
```
and add all needed settings from geitter developer settings.
```
gcloud beta runtime-config configs variables set gitter.community <gitter community key> --config-name gitterchat_compute_engine
gcloud beta runtime-config configs variables set gitter.token <gitter access token> --config-name gitterchat_compute_engine
gcloud beta runtime-config configs variables set gitter.oauth.key <gitter oauth key> --config-name gitterchat_compute_engine
gcloud beta runtime-config configs variables set gitter.oauth.secret <gitter oauth secret> --config-name gitterchat_compute_engine
gcloud beta runtime-config configs variables set gitter.oauth.redirect_url <gitter redirect url> --config-name gitterchat_compute_engine
gcloud beta runtime-config configs variables set spring.datasource.url 'jdbc:mysql://localhost:3306/messages?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC' --config-name gitterchat_compute_engine
gcloud beta runtime-config configs variables set spring.datasource.username root --config-name gitterchat_compute_engine
gcloud beta runtime-config configs variables set spring.datasource.password <mysql root password> --config-name gitterchat_compute_engine
```

Also we we’ll need to clone this repository and checkout to the ***compute_engine*** branch.
```
git clone https://github.com/NikitaNovozhilovWork/gcp-demo-project.git
git checkout compute_engine
```

So, lets create compute engine instance *("your project id" and "your project number" you can find in "IAM and administration -> Settings" menu)*.
```
gcloud compute --project=<your project id> instances create gitter-compute-engine --zone=us-central1-a --machine-type=n1-standard-1 --subnet=default --network-tier=PREMIUM --maintenance-policy=MIGRATE --service-account=<your project number>-compute@developer.gserviceaccount.com --scopes=https://www.googleapis.com/auth/cloud-platform --tags=http-server --image=debian-9-stretch-v20190729 --image-project=debian-cloud --boot-disk-size=10GB --boot-disk-type=pd-standard --boot-disk-device-name=gitter-compute-engine
```

Lets build our application,
```
./gradlew build
```

copy to our vm, configure and run it.
```
copying:
gcloud compute scp build/libs/gcp-demo-project-0.0.1-SNAPSHOT.jar gitter-compute-engine:~/gcp-demo-project.jar --zone=us-central1-a

open ssh connection:
gcloud beta compute --project "<your project id>" ssh --zone "us-central1-a" "gitter-compute-engine"

install java:
sudo apt-get install openjdk-8-jdk

install MySQL:
sudo apt-get install mysql-server

configure mysql (enter the same password for root user as in the cloud runtime configuration spring.datasource.password):
sudo mysql_secure_installation 

create schema messages and grant priveleges for root user:
sudo mysql -u root -p 
CREATE SCHEMA `messages`; 
GRANT ALL PRIVILEGES on *.* to 'root'@'localhost' IDENTIFIED BY '<root password>'; 
FLUSH PRIVILEGES; 
exit

run application:
nohup sudo java -Dserver.port=80 -jar gcp-demo-project.jar &
```

Now you can access the app through the vm external ip