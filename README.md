# ArticleManagementSystem

1) Recommended to use Google Chrome for runnig this application. 
2) The default credentials for admin will be pre-populated in database tables using AdminConfig class. Role will be 'ADMIN' for this entry.
3) Credential for admin login is
                  username: admin 
                  password: admin
4) Schema and all the tables will be created when application starts. Application URL: http://localhost:8080/
5) New account can be created from login page and role will be 'USER'.
6) The button 'ManageArticles' in main page is only for admin access and normal user will get Access Denied popup. Using this link admin can delete any articles.
7) API's used as below:
                1) Spring boot with web security.
                2) Validation: jQuery validation, Spring Validator, Hibernate Validator.
                3) Multipart request for file uploads, with unique filename logic
                4) CSS: bootstrap for log in and registration page.
                5) JPA: Hibernate 5
