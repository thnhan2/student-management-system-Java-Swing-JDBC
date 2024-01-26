# Student management system Java Swing

# Role

- Admin: Management user account, and have function of teacher Role
- Teacher: import/export student and certificate data
- Employee: View data

# How to use package

## Using IDE: open project src folder run Main class

## Using maven

`mvn clean package`
then using `.jar` file in target folder to run by java command `java -jar <file-build>.jar`
make sure that you open mysql service, and username=root, password="", dbname=mysql (default) or config in source code before build jar.

# Other function

- import, export data using `.csv`
- update user profile photo
- view login history
- ban/unban account
  ...
