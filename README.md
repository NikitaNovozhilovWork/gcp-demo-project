# gcp-demo-project

[![Join the chat at https://gitter.im/gcp-demo-project/community](https://badges.gitter.im/gcp-demo-project/community.svg)](https://gitter.im/gcp-demo-project/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Project for gcp demo

# Google App Engine
This code is example to try Google App Engine. **To run it you should have gitter development access.**

First, we’ll need to create new cloud runtime configuration *(If Cloud Runtime Configuration API isn't enabled, you can find out how to do it in the branch "cloud_shell")*
```
gcloud beta runtime-config configs create gitterchat_appengine
```
and add all needed settings from geitter developer settings.
```
gcloud beta runtime-config configs variables set gitter.community <gitter community key> --config-name gitterchat_appengine
gcloud beta runtime-config configs variables set gitter.token <gitter access token> --config-name gitterchat_appengine
gcloud beta runtime-config configs variables set gitter.oauth.key <gitter oauth key> --config-name gitterchat_appengine
gcloud beta runtime-config configs variables set gitter.oauth.secret <gitter oauth secret> --config-name gitterchat_appengine
gcloud beta runtime-config configs variables set gitter.oauth.redirect_url <gitter redirect url> --config-name gitterchat_appengine
gcloud beta runtime-config configs variables set spring.cloud.gcp.datastore.namespace datastore_messages --config-name gitterchat_appengine
```

Also we we’ll need to clone this repository and checkout to the ***app_engine*** branch.
```
git clone https://github.com/NikitaNovozhilovWork/gcp-demo-project.git
git checkout app_engine
```

So, lets enable App Engine and start datastore.
```
Enable App Engine in your project by link from 'gcloud app open-console' -> Settings
select datastore from main menu and start it.
```

Also create topic and subscriptions in the Cloud Pub/Sub
```
gcloud pubsub topics create chat-messages
gcloud pubsub subscriptions create --topic chat-messages chat-messages-history-ds
gcloud pubsub subscriptions create --topic chat-messages chat-messages-updater-ds
```

Lets build and deploy our applications,
```
export PROJECT_ID=$(gcloud config get-value core/project)
./gradlew gitter-listener:appengineDeploy
./gradlew message-history-service:appengineDeploy
./gradlew chat-wall:appengineDeploy
```

Now you can access the app through the public chat-wall-ds service url
