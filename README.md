# Menu System Application

This application provides a menu-based interface to interact with client data, including balance, internet traffic, and special offers. The menu structure and behavior are defined in a JSON configuration file. The application reads the configuration, processes user input, and provides appropriate responses based on the client's data.
## Features
App is able to have tree of menu items, meaning we can have deep tree.
Clients are defined in data/clients.json 

Use the package manager [mvn](https://maven.apache.org/run.html) to install.

```bash
mvn clean package
```

## Json file - menu tree example

```json
{
      "code": "3",
      "name": "Специальные предложения",
      "submenu": [
        {
          "code": "1",
          "name": "Безлимитный интернет",
          "message": "Успейте подключить до 1 июня и получите безлимитный интернет.",
          "submenu": [
            {
              "code": "1",
              "name": "Абсолютный Безлимитный интернет",
              "message": "Абсолютный Безлимитный интернет всего за 4999 тг",
              "submenu": [
                {
                  "code": "1",
                  "name": "Для наших",
                  "message": "Для текущих клиентов мы дарим 2 месяца Абсолютного Безлимитного интернета всего за 499 тг"
                }
              ]
            }
          ]
        }
```
