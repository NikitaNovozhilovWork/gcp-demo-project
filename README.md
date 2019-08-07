# gcp-demo-project

[![Join the chat at https://gitter.im/gcp-demo-project/community](https://badges.gitter.im/gcp-demo-project/community.svg)](https://gitter.im/gcp-demo-project/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Project for gcp demo

## Google Cloud Shell
This code is example to try Google Cloud Shell. To run it you should have gitter development access. 

First, we’ll need to enable Cloud Runtime Configuration API,
```
gcloud services enable runtimeconfig.googleapis.com
```
create cloud runtime configuration
```
gcloud beta runtime-config configs create gitterchat_shell
```
and add all needed settings from geitter developer settings.
```
gcloud beta runtime-config configs variables set gitter.community <gitter community key> --config-name gitterchat_shell
gcloud beta runtime-config configs variables set gitter.token <gitter access token> --config-name gitterchat_shell
gcloud beta runtime-config configs variables set gitter.oauth.key <gitter oauth key> --config-name gitterchat_shell
gcloud beta runtime-config configs variables set gitter.oauth.secret <gitter oauth secret> --config-name gitterchat_shell
gcloud beta runtime-config configs variables set gitter.oauth.redirect_url <gitter redirect url> --config-name gitterchat_shell
```

Also we we’ll need to clone this repository and checkout to the cloud_shell branch.
```
git clone https://github.com/NikitaNovozhilovWork/gcp-demo-project.git
git checkout cloud_shell
```

That's all, now we can start up our application 
```
./gradlew bootRun
```
and watch the streaming from gitter chat to our application, you can find application on port 8080 through the cloud shell port preview.