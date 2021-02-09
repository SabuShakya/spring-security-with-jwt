# Spring Security #

*Authentication* refers to the process of verifying the identity of a user, based on provided credentials. A common
example is entering a username and a password when you log in to a website. You can think of it as an answer to 
the question Who are you?.

*Authorization* is about what you are allowed to do in the context of application. It refers to the process of 
determining if a user has proper permission to perform a particular action or read particular data, assuming that 
the user is successfully authenticated. You can think of it as an answer to the question Can a user do/read this?.

*Principal* contains information about logged in user(recently authenticated user).

*Granted Authority* permissions of authenticated user.

*Role* group of permissions to the authenticated user.

Spring Security Dependency :
```xml
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
Adding the dependency will have an immediate effect. When we re-run our application after adding dependency, it will
generate a security password in the console. When we try to access any api, it will redirect us to  
`http://localhost:8080/login`. This is default behavior because the Spring Security framework requires authentication
out of the box for all URLs. We can use default username `'user'` and the auto-generated password to login.

![Alt text](./spring-login.jpg?raw=true "Title")

![Alt text](./spring-password.jpg?raw=true "Title")

We can also set password in application.properties, if we donot want new password each time we re-run app.
```properties
spring.security.user.password=Test12345_
```
If we want to log out, we can access the following URL: http://localhost:8080/logout .


## Spring Security Architecture Overview ##

![Alt text](./Auth.png?raw=true "Title")

References:

- https://www.youtube.com/watch?v=caCJAJC41Rk&ab_channel=JavaBrains
- [Spring Security with JWT for REST API](https://www.toptal.com/spring/spring-security-tutorial)
- https://amigoscode.com/courses/enrolled/728126