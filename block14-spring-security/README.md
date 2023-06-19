## Project about security access (Spring security)

### Using tokens with JWT and SpringBoot

** 1 **

User call login endpoint in class 'security.LoginController' with a username and a password.
If there's a person with that credentials in database (H2), then he/she can access and client.

Client get a token.


** 2 **

With that token, if user ('Person') has 'admin' credentials (admin = true), he/she can access whatever endpoints.

If not, has access only to GET endpoints.