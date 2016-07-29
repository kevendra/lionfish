
Build the project by invoking
$ mvn clean install

Test the application locally in the development server as follows:
$ mvn appengine:devserver

Use your browser to visit http://localhost:8080/ to access your app.

Shut down the app and the development server by pressing Control+C in the Windows/Linux terminal window where you started it, or CMD+C on the Mac.


Uploading your app to production App Engine
$ mvn appengine:update

You will be prompted for an authorization code in the terminal window and your web browser will launch with a consent screen which you must accept in order to be authorized. Follow the prompts to copy any codes from the browser to the command line.


To see App Engine-specific Maven goals 
$ mvn help:describe -Dplugin=appengine

 
# grunt and gulp to optimize resources at build time