# feature-switches

## Getting Started

1. Make sure the required tools are installed: [Guide](https://spring.io/guides/gs/spring-boot)

2. Add or edit feature access with `/feature` endpoint (method: POST):
    ```bash
    ## Body parameters: featureName, email, enable
    curl --location 'localhost:8080/feature' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "featureName": "edit-profile",
        "email": "abc@gmail.com",
        "enable": true
    }
    '
    ```
    Response example:
    ```
    {
        "statusCode": "OK",
        "hasError": false,
        "error": null,
        "data": {
            "message": "Successfully updated existing feature access (ID: 5)"
        }
    }
    ```

3. Query feature access of a user with `/feature` endpoint (method: GET):
    ```bash
    ## Query paramaters: email, featureName
    curl --location 'localhost:8080/feature?email=abc%40gmail.com&featureName=edit-profile'
    ```
    Response example:
    ```
    {
        "statusCode": "OK",
        "hasError": false,
        "error": null,
        "data": {
            "canAccess": true
        }
    }
    ```

