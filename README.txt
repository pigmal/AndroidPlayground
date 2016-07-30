



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

