
# Global Assessment (peglrepo-assessment01)

This is the assessment for the Data Engineer position at Globant. It consists of creating an API to upload CSV files and save the data within them into a SQL database.


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file or Intellij IDEA configuration project.

`DB_PASSWORD=<your_password>`

`DB_URL=
jdbc:postgresql://<hostname>:<port>/<database_name>`

`DB_USERNAME=<your_database_user>`

`DB_SCHEMA=<your_schema`


## Deployment

To deploy this project run

```bash
  mvn clean package
```
If you want to deploy it into an ECR cointainter in AWS, you can create an Code Build service and execute the `buildspec.yml` file

If you want to modify tables structure, you can modify the `db.changelog-master.yaml` using liquidbase notation.
## Run Locally

Clone the project

```bash
  git clone https://github.com/luispar90/peglrepo-assessment01.git
```

Go to the project directory

```bash
  cd peglrepo-assessment01
```

Clean the Maven project

```bash
  mvn clean
```

Complies the Maven project

```bash
  mvn compiler:compile
```

Create the JAR files

```bash
mvn install
```

Deploy Maven project

```bash
mvn deploy
```