



### For CI
Set environment variables

- Parse
```
ORG_GRADLE_PROJECT_parseAppId
ORG_GRADLE_PROJECT_parseClientKey
```

- Firebase
Put google-services.json somewhere and set the URL.
```
GOOGLE_SERVICES_JSON_URL
```

As of now, I put it on dropbox then add a little trick in circle.yml and wercker.yml.


Get the server key
https://console.firebase.google.com/
Setting icon -> Project Settings -> CLOUD MESSAGING


- notification message
```
curl --header "Authorization: key=<SERVER_KEY>" \
     --header Content-Type:"application/json" \
     https://fcm.googleapis.com/fcm/send \
     -d "{\"to\": \"/topics/news\",\"priority\":\"high\",\"notification\": {\"title\": \"message title\", \"body\": \"message body\", \"icon\": \"ic_stat_ic_notification\"}}"
```

curl --header "Authorization: key=AIzaSyBwrISaDOvvNDgoXRq-pivooCt-2tRXLy8" \
     --header Content-Type:"application/json" \
     https://fcm.googleapis.com/fcm/send \
     -d "{\"to\": \"/topics/news\",\"priority\":\"high\",\"notification\": {\"title\": \"this is title\", \"body\": \"this is body\", \"icon\": \"ic_stat_ic_notification\"}}"

- data message
```
curl --header "Authorization: key=<SERVER_KEY>" \
     --header Content-Type:"application/json" \ｔ
     https://fcm.googleapis.com/fcm/send \
     -d "{\"to\": \"/topics/news\",\"priority\":\"high\",\"data\": {\"custom_title\": \"data custom title\", \"custom_body\": \"data custom body\", \"icon\": \"ic_stat_ic_notification\"}}"
```
