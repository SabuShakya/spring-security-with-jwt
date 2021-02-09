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

## JWT ##

JSON Web Tokens is a standard used for securing REST apis. It is a standard that defines a compact and self-contained 
way for securely transmitting information between parties as a JSON object. JWTs can be signed using a secret 
(with the HMAC algorithm) or a public/private key pair using RSA or ECDSA

### JSON Web Token Structure ###

JSON Web Tokens consists of three parts separated by dots(.), which are 

- header
- payload
- signature

#### Header ####

It consists of two parts: type of token, which is JWT and the signing algorithm being used, like HMAC, SHA256 or RSA.

eg:
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```
This header is the Base64Url encoded to form the first part of JWT.

#### Payload ####

It is the second part which contains the claims. Claims are statements about an entity(user) 
and additional data. There are three types of claims:

- *Registered Claims* : Set of predefined claims. Examples: iss(issuer),exp(expiration time), sub(subject),sud(audience) etc.

- *Public claims* : Public claims are like public API that defined for public consumption. They should be well documented. 
They should be defined in the IANA JSON Web Token Registry or be defined as a URI that contains a collision resistant namespace.

- *Private claims* : Custom claims created to share information between parties that agree on using 
them and are neither registered nor public claims.

```json
{
  "sub": "1234567890",
  "name": "Sabu",
  "admin": true
}
```
The payload is then Base64Url encoded to form the second part of the JSON Web Token.

#### Signature ####
It is used to verify the message wan't changed and in case of tokens signed with a private key, it can verify that the 
sender of JWT is who it says it is.
We have to take the encoded header, encoded payload, a secret, the algorithm specified in the header and sign that.
For example, if we are using HMAC SHA256 algorithm:

```
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  secret)
```

The output is three Base64-URL strings separated by dots. 
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```
### PROCEDURE ###

![Alt text](./jwt.jpg?raw=true "Title")

