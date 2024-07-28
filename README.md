# Speeks

The main goal of the system is to exchange messages and other types of data between users. There is a login service that aims to authenticate users.
Additionally, there is a system for the registration of new users and services to check if a user is registered in the system. 
After successful authentication, the user can see all other online users and exchange messages and data with them.
Users can also send files and location coordinates to other users. The server will send notifications to the client for new messages and will show the actual list of users that are online. 
Users will also be able to set up a profile picture as an avatar and change their password. Additionally, the application has a service to send a new password via email in case of a forgotten password. 
The app is based on the REST architecture and developed with the Java programming language. Server-Sent Events (SSE) technology is used as well with the Jersey library. ORM (Object-Relational Mapping) is used to map Java classes (entities) with tables in the database. 
This approach provides an easy way for CRUD operations. A MySQL database is used to store the database tables.  HTML5 Geolocation is used for sending location data to users. AJAX (Asynchronous JavaScript and XML) connects the Front-End and the back end, allowing information to be received and loaded without reloading the entire page.
