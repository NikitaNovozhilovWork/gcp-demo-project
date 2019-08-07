# gcp-demo-project

[![Join the chat at https://gitter.im/gcp-demo-project/community](https://badges.gitter.im/gcp-demo-project/community.svg)](https://gitter.im/gcp-demo-project/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Project for gcp demo

## Google Kubernetes Engine
This code is example to try Google Compute Engine. **To run it you should have gitter development access.**

First, we’ll need to create new cloud runtime configuration *(If Cloud Runtime Configuration API isn't enabled, you can find out how to do it in the branch "cloud_shell")*
```
gcloud beta runtime-config configs create gitterchat_kubernetes
```
and add all needed settings from geitter developer settings. *(If you don't know where to get "your project id" or "your project number" you can find out how to do it in the branch "compute_engine")*
```
gcloud beta runtime-config configs variables set gitter.community <gitter community key> --config-name gitterchat_kubernetes
gcloud beta runtime-config configs variables set gitter.token <gitter access token> --config-name gitterchat_kubernetes
gcloud beta runtime-config configs variables set gitter.oauth.key <gitter oauth key> --config-name gitterchat_kubernetes
gcloud beta runtime-config configs variables set gitter.oauth.secret <gitter oauth secret> --config-name gitterchat_kubernetes
gcloud beta runtime-config configs variables set gitter.oauth.redirect_url <gitter redirect url> --config-name gitterchat_kubernetes
gcloud beta runtime-config configs variables set spring.datasource.username root --config-name gitterchat_kubernetes
gcloud beta runtime-config configs variables set spring.datasource.password <mysql root password> --config-name gitterchat_kubernetes
gcloud beta runtime-config configs variables set spring.cloud.gcp.sql.instance-connection-name '<your project id>:us-central1:gcp-demo-project-mysql' --config-name gitterchat_kubernetes
gcloud beta runtime-config configs variables set spring.cloud.gcp.sql.database-name messages --config-name gitterchat_kubernetes
```

Also we we’ll need to clone this repository and checkout to the ***kubernetes_engine*** branch.
```
git clone https://github.com/NikitaNovozhilovWork/gcp-demo-project.git
git checkout kubernetes_engine
```

So, lets create kubernetes instance,
```
gcloud beta container --project "<your project id>" clusters create "gcp-demo-project-cluster" --zone "us-central1-a" --no-enable-basic-auth --cluster-version "1.12.8-gke.10" --machine-type "n1-standard-1" --image-type "COS" --disk-type "pd-standard" --disk-size "100" --metadata disable-legacy-endpoints=true --scopes "https://www.googleapis.com/auth/cloud-platform" --num-nodes "3" --enable-cloud-logging --enable-cloud-monitoring --enable-ip-alias --network "projects/<your project id>/global/networks/default" --subnetwork "projects/<your project id>/regions/us-central1/subnetworks/default" --default-max-pods-per-node "110" --addons HorizontalPodAutoscaling,HttpLoadBalancing --enable-autoupgrade --enable-autorepair
```

MySQL cloud instance
```
gcloud sql instances create gcp-demo-project-mysql --tier=db-n1-standard-1 --region=us-central1 --storage-type=HDD --storage-size=10
gcloud sql users set-password root --host % --instance gcp-demo-project-mysql --password <mysql root password>
gcloud sql databases create messages --instance=gcp-demo-project-mysql
```

and Enable Cloud Translation API
```
gcloud services enable translate.googleapis.com
```

Lets build containers from applications,
```
export PROJECT_ID=$(gcloud config get-value core/project)
./gradlew jib
```

connect to the kubernetes cluster 
```
gcloud container clusters get-credentials gcp-demo-project-cluster --zone us-central1-a --project <your project id>
```

and run containers on it.
```
kubectl apply -f kubernetes.yaml
```

Now you can access the app through the cluster chat-wall-kubernetes docker container external ip
