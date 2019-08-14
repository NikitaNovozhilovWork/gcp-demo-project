# gcp-demo-project

[![Join the chat at https://gitter.im/gcp-demo-project/community](https://badges.gitter.im/gcp-demo-project/community.svg)](https://gitter.im/gcp-demo-project/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Project for gcp demo

## Google Cloud Functions
This code is example to try Google Cloud Functions. **To run it you should have gitter development access.**

First, we’ll need to create new cloud runtime configuration *(If Cloud Runtime Configuration API isn't enabled, you can find out how to do it in the branch "cloud_shell")*
```
gcloud beta runtime-config configs create gitterchat_cloudfunctions
```
and add all needed settings from geitter developer settings.
```
gcloud beta runtime-config configs variables set gitter.community <gitter community key> --config-name gitterchat_cloudfunctions
gcloud beta runtime-config configs variables set gitter.token <gitter access token> --config-name gitterchat_cloudfunctions
gcloud beta runtime-config configs variables set gitter.oauth.key <gitter oauth key> --config-name gitterchat_cloudfunctions
gcloud beta runtime-config configs variables set gitter.oauth.secret <gitter oauth secret> --config-name gitterchat_cloudfunctions
gcloud beta runtime-config configs variables set gitter.oauth.redirect_url <gitter redirect url> --config-name gitterchat_cloudfunctions
gcloud beta runtime-config configs variables set spring.cloud.gcp.spanner.instance-id gcp-demo-spanner --config-name gitterchat_cloudfunctions
gcloud beta runtime-config configs variables set spring.cloud.gcp.spanner.database chat-messages --config-name gitterchat_cloudfunctions
```

Also we we’ll need to clone this repository and checkout to the ***cloud_functions*** branch.
```
git clone https://github.com/NikitaNovozhilovWork/gcp-demo-project.git
git checkout cloud_functions
```

So, lets enable App Engine and start datastore.
```
Enable App Engine in your project by link from 'gcloud app open-console' -> Settings
select datastore from main menu and start it.
```

Also create topic and subscriptions in the Cloud Pub/Sub
```
gcloud pubsub topics create chat-messages
gcloud pubsub subscriptions create --topic chat-messages chat-messages-updater-sp
```

and create and config Cloud Spanner instance
```
gcloud spanner instances create gcp-demo-spanner --config=regional-us-central1 --description="GCP demo spanner" --nodes=1
gcloud spanner databases create chat-messages --instance=gcp-demo-spanner --ddl='CREATE TABLE messages (messageId STRING(MAX) NOT NULL, sent TIMESTAMP NOT NULL, text STRING(MAX) NOT NULL, translation STRING(MAX) NOT NULL, userDisplayName STRING(MAX) NOT NULL, userId STRING(MAX) NOT NULL, userUsername STRING(MAX) NOT NULL) PRIMARY KEY (messageId)'
```

Lets register cloud function
```
cd message-history-function
gcloud functions deploy message-history-function --entry-point=subscribe --runtime nodejs8 --trigger-topic=chat-messages
cd ..
```

and build and deploy our applications.
```
export PROJECT_ID=$(gcloud config get-value core/project)
./gradlew gitter-listener:appengineDeploy
./gradlew chat-wall:appengineDeploy
```

Now you can access the app through the public chat-wall-cs service url
